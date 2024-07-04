package com.capstone.realmen.service.branch;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.capstone.realmen.common.enums.EBranchStatus;
import com.capstone.realmen.common.request.RequestContext;
import com.capstone.realmen.controller.handler.exceptions.InvalidRequest;
import com.capstone.realmen.controller.handler.exceptions.NotFoundException;
import com.capstone.realmen.data.dto.account.Account;
import com.capstone.realmen.data.dto.branch.IBranchMapper;
import com.capstone.realmen.data.dto.branch.address.Address;
import com.capstone.realmen.data.dto.shop.service.ShopService;
import com.capstone.realmen.repository.database.audit.Auditable;
import com.capstone.realmen.repository.database.branch.BranchEntity;
import com.capstone.realmen.repository.database.branch.IBranchRepository;
import com.capstone.realmen.service.account.AccountQueryService;
import com.capstone.realmen.service.account.data.AccountSearchByField;
import com.capstone.realmen.service.account.others.branch.AccountBranchCommandService;
import com.capstone.realmen.service.account.others.branch.data.AccountBranchCreateRequire;
import com.capstone.realmen.service.branch.data.BranchActiveRequire;
import com.capstone.realmen.service.branch.data.BranchCreateRequire;
import com.capstone.realmen.service.branch.data.BranchServiceRequire;
import com.capstone.realmen.service.branch.helpers.BranchHelpers;
import com.capstone.realmen.service.branch.others.address.AddressQueryService;
import com.capstone.realmen.service.branch.others.address.data.AddressSearchByField;
import com.capstone.realmen.service.branch.others.displays.BranchDisplayCommandService;
import com.capstone.realmen.service.branch.others.displays.data.BranchDisplayCreateRequire;
import com.capstone.realmen.service.branch.others.services.BranchServiceCommandService;
import com.capstone.realmen.service.branch.others.services.data.BranchServiceCreateRequire;
import com.capstone.realmen.service.shop.service.ShopServiceQueryService;
import com.capstone.realmen.service.shop.service.data.ShopServiceSearchByField;

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
                List<Account> staffList = accountQueryService
                                .findAllByIds(
                                                AccountSearchByField.builder()
                                                                .accountIds(activeRequire.staffIdList())
                                                                .build());
                List<ShopService> serviceList = shopServiceQueryService
                                .findAllByIds(
                                                ShopServiceSearchByField.builder()
                                                                .shopServiceIds(
                                                                                activeRequire.serviceList()
                                                                                                .stream()
                                                                                                .map(BranchServiceRequire::shopServiceId)
                                                                                                .toList())
                                                                .build());
                if (!verifyActive(serviceList, staffList)) {
                        throw new InvalidRequest("Không đủ nhân sự để kích hoạt chi nhánh");
                }
                BranchEntity foundBranch = branchRepository.findById(activeRequire.branchId())
                                .orElseThrow(NotFoundException::new);
                accountBranchCommandService.createList(
                                AccountBranchCreateRequire.of(foundBranch.getBranchId(), staffList));
                branchServiceCommandService.createList(
                        BranchServiceCreateRequire.of(foundBranch.getBranchId(), activeRequire.serviceList()));
                branchRepository.save(
                        foundBranch
                                .withBranchStatusCode(EBranchStatus.ACTIVE.getCode())
                                .withBranchStatusName(EBranchStatus.ACTIVE.getName())
                );
        }
}
