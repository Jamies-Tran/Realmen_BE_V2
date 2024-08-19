package com.capstone.realmen.service.branch;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.capstone.realmen.common.enums.EBranchStatus;
import com.capstone.realmen.common.enums.ERole;
import com.capstone.realmen.common.request.PageRequestCustom;
import com.capstone.realmen.common.request.RequestContext;
import com.capstone.realmen.controller.handler.exceptions.InvalidRequest;
import com.capstone.realmen.controller.handler.exceptions.NotFoundException;
import com.capstone.realmen.data.dto.account.Account;
import com.capstone.realmen.data.dto.branch.IBranchMapper;
import com.capstone.realmen.data.dto.branch.address.Address;
import com.capstone.realmen.repository.database.audit.Auditable;
import com.capstone.realmen.repository.database.branch.BranchEntity;
import com.capstone.realmen.repository.database.branch.IBranchRepository;
import com.capstone.realmen.service.account.AccountCommandService;
import com.capstone.realmen.service.account.AccountQueryService;
import com.capstone.realmen.service.account.data.AccountSearchByField;
import com.capstone.realmen.service.account.data.AccountSearchCriteria;
import com.capstone.realmen.service.account.others.branch.AccountBranchCommandService;
import com.capstone.realmen.service.account.others.branch.data.AccountBranchCreateRequire;
import com.capstone.realmen.service.branch.data.BranchAction;
import com.capstone.realmen.service.branch.data.BranchActiveRequire;
import com.capstone.realmen.service.branch.data.BranchAddServiceRequire;
import com.capstone.realmen.service.branch.data.BranchAddStaffRequire;
import com.capstone.realmen.service.branch.data.BranchCreateRequire;
import com.capstone.realmen.service.branch.data.EBranchAction;
import com.capstone.realmen.service.branch.helpers.BranchHelpers;
import com.capstone.realmen.service.branch.others.address.AddressQueryService;
import com.capstone.realmen.service.branch.others.address.data.AddressSearchByField;
import com.capstone.realmen.service.branch.others.displays.BranchDisplayCommandService;
import com.capstone.realmen.service.branch.others.displays.data.BranchDisplayCreateRequire;
import com.capstone.realmen.service.branch.others.services.BranchServiceCommandService;
import com.capstone.realmen.service.branch.others.services.data.BranchServiceCreateRequire;
import com.capstone.realmen.service.shop.service.ShopServiceQueryService;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BranchCommandService extends BranchHelpers {
        @NonFinal
        @Value("${goong.api.key}")
        String goongApiKey;

        @NonNull
        IBranchRepository branchRepository;

        @NonNull
        AddressQueryService addressQueryService;

        @NonNull
        AccountCommandService accountCommandService;

        @NonNull
        ShopServiceQueryService shopServiceQueryService;

        @NonNull
        AccountQueryService accountQueryService;

        @NonNull
        AccountBranchCommandService accountBranchCommandService;

        @NonNull
        BranchDisplayCommandService branchDisplayCommandService;

        @NonNull
        BranchServiceCommandService branchServiceCommandService;

        @NonNull
        IBranchMapper branchMapper;

        @NonNull
        RequestContext requestContext;

        public void create(BranchCreateRequire createRequire) {
                Address branchAddress = addressQueryService.getAddress(
                                AddressSearchByField.builder()
                                                .apiKey(goongApiKey)
                                                .address(createRequire.branch().branchAddress())
                                                .build());
                BranchEntity newBranch = branchMapper.toEntity(createRequire.branch());
                BranchEntity saveBranch = branchRepository.save(
                                newBranch
                                                .withAddress(branchAddress)
                                                .withBranchStatusCode(EBranchStatus.INACTIVE.getCode())
                                                .withBranchStatusName(EBranchStatus.INACTIVE.getName())
                                                .setAudit(Auditable.ofCreated(requestContext.getAccount())));
                branchDisplayCommandService.create(
                                BranchDisplayCreateRequire.builder()
                                                .branchId(saveBranch.getBranchId())
                                                .branchDisplays(createRequire.branchDisplays())
                                                .build());
        }

        public void active(BranchActiveRequire activeRequire) {
                BranchEntity foundBranch = branchRepository.findById(activeRequire.branchId())
                                .orElseThrow(NotFoundException::new);
                List<BranchAction> branchActions = getAllowableAction(foundBranch.getBranchStatusCode());
                BranchAction activeAction = branchActions.stream()
                                .filter(action -> Objects.equals(action.actionCode(), EBranchAction.ACTIVATE.getCode()))
                                .findAny()
                                .orElse(null);
                if (!activeAction.isAllow()) {
                        throw new InvalidRequest("Không thể kích hoạt chi nhánh");
                }
                AccountSearchByField accountSearch = AccountSearchByField
                                .builder()
                                .accountIds(activeRequire.staffIdList())
                                .build();
                List<Account> staffList = accountQueryService
                        .findAllByIds(accountSearch);
                List<Account> getManager = staffList.stream()
                                .filter(account -> Objects.equals(account.roleCode(), ERole.BRANCH_MANAGER.getCode()))
                                .toList();

                if (getManager.isEmpty()) {
                        throw new InvalidRequest("Chi nhánh cần quản lý");
                } else if (getManager.size() > 1) {
                        throw new InvalidRequest("Chi nhánh chỉ cần một quản lý");
                }

                AccountBranchCreateRequire accountBranchCreate = AccountBranchCreateRequire
                                .of(foundBranch.getBranchId(), staffList);
                List<Long> staffIds = accountBranchCommandService.createList(accountBranchCreate);
                BranchServiceCreateRequire branchServiceCreate = BranchServiceCreateRequire
                                .of(foundBranch.getBranchId(), activeRequire.serviceList(), staffList);
                branchServiceCommandService.createList(branchServiceCreate);
                BranchEntity newBranchService = foundBranch
                                .withBranchStatusCode(EBranchStatus.ACTIVE.getCode())
                                .withBranchStatusName(EBranchStatus.ACTIVE.getName());

                accountCommandService.active(staffIds);
                branchRepository.save(newBranchService);
        }

        public void addService(BranchAddServiceRequire addServiceRequire) {
                Long branchId = addServiceRequire.branchId();
                if (Objects.isNull(branchId)) {
                        Long managerBranchId = requestContext.getAccount().branchId();
                        addServiceRequire = addServiceRequire.withBranchId(managerBranchId);
                }
                AccountSearchCriteria searchCriteria = AccountSearchCriteria.filterStaffOnBranch(branchId);
                List<Account> branchStaffs = accountQueryService
                                .findAll(searchCriteria, PageRequestCustom.unPaged())
                                .getContent();
                BranchServiceCreateRequire createRequire = BranchServiceCreateRequire
                                .of(branchId, addServiceRequire.services(), branchStaffs);
                branchServiceCommandService.createList(createRequire);
        }

        public void addStaff(BranchAddStaffRequire addStaffRequire) {

        }
}
