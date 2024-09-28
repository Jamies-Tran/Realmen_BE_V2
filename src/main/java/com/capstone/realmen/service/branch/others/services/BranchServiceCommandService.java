package com.capstone.realmen.service.branch.others.services;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.capstone.realmen.common.enums.EBranchServiceStatus;
import com.capstone.realmen.data.dao.branch.service.BranchServiceDAO;
import com.capstone.realmen.data.dto.account.Account;
import com.capstone.realmen.data.dto.branch.service.IBranchServiceMapper;
import com.capstone.realmen.data.dto.shop.service.ShopService;
import com.capstone.realmen.repository.database.branch.service.BranchServiceEntity;
import com.capstone.realmen.repository.database.branch.service.IBranchServiceRepository;
import com.capstone.realmen.service.branch.data.BranchServiceRequire;
import com.capstone.realmen.service.branch.others.services.data.BranchServiceActiveRequire;
import com.capstone.realmen.service.branch.others.services.data.BranchServiceCreateRequire;
import com.capstone.realmen.service.branch.others.services.helpers.BranchServiceHelper;
import com.capstone.realmen.service.shop.service.ShopServiceQueryService;
import com.capstone.realmen.service.shop.service.data.ShopServiceSearchByField;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BranchServiceCommandService extends BranchServiceHelper {
        @NonNull
        IBranchServiceRepository branchServiceRepository;

        @NonNull
        ShopServiceQueryService shopServiceQueryService;

        @NonNull
        IBranchServiceMapper branchServiceMapper;

        public void createList(BranchServiceCreateRequire createRequire) {
                List<Long> serviceIds = createRequire.branchServices()
                                .stream()
                                .map(BranchServiceRequire::shopServiceId)
                                .toList();
                ShopServiceSearchByField searchByField = ShopServiceSearchByField.of(serviceIds);
                List<ShopService> services = shopServiceQueryService.findAllByIds(searchByField);

                List<Account> staffs = createRequire.staffs();
                Map<Long, BranchServiceRequire> serviceRequire = createRequire.branchServices()
                                .stream()
                                .collect(Collectors.toMap(BranchServiceRequire::shopServiceId, brs -> brs));
                List<BranchServiceEntity> newBranchServices = services.stream()
                                .map(service -> {
                                        EBranchServiceStatus status = getBranchServiceStatus(service, staffs);
                                        BranchServiceRequire brs = serviceRequire.get(service.shopServiceId());
                                        return BranchServiceEntity.builder()
                                                        .branchId(createRequire.branchId())
                                                        .shopServiceId(service.shopServiceId())
                                                        .branchServicePrice(brs.price())
                                                        .estimateDuration(brs.estimateDuration())
                                                        .durationUnitCode(brs.durationUnitCode())
                                                        .durationUnitName(brs.durationUnitName())
                                                        .branchServiceStatusCode(status.getCode())
                                                        .branchServiceStatusName(status.getName())
                                                        .build();
                                }).toList();

                branchServiceRepository.saveAll(newBranchServices);
        }

        public void activeBranchService(BranchServiceActiveRequire activeRequire) {
                List<BranchServiceDAO> branchServices = branchServiceRepository
                                .findAllByBranchIdAndStatusCode(activeRequire.branchId(),
                                                EBranchServiceStatus.INACTIVE.getCode());
                List<BranchServiceEntity> activeBranchServices = branchServices.stream()
                                .map(branchService -> {
                                        if (Objects.equals(branchService.getServiceAssignmentCode(),
                                                        activeRequire.professionalTypeCode())) {
                                                return branchServiceMapper.toEntity(branchService)
                                                                .withBranchServiceStatusCode(
                                                                                EBranchServiceStatus.ACTIVE.getCode())
                                                                .withBranchServiceStatusName(
                                                                                EBranchServiceStatus.ACTIVE.getName());
                                        }
                                        return branchServiceMapper.toEntity(branchService);
                                }).toList();
                branchServiceRepository.saveAll(activeBranchServices);
        }
}
