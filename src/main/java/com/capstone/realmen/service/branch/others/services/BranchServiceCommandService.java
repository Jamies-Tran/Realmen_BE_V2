package com.capstone.realmen.service.branch.others.services;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.capstone.realmen.common.enums.EBranchServiceStatus;
import com.capstone.realmen.data.dto.account.Account;
import com.capstone.realmen.data.dto.branch.service.IBranchServiceMapper;
import com.capstone.realmen.data.dto.shop.service.ShopService;
import com.capstone.realmen.repository.database.branch.service.BranchServiceEntity;
import com.capstone.realmen.repository.database.branch.service.IBranchServiceRepository;
import com.capstone.realmen.service.branch.data.BranchServiceRequire;
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
        Map<Long, Long> serviceRequire = createRequire.branchServices()
                .stream()
                .collect(Collectors.toMap(BranchServiceRequire::shopServiceId, BranchServiceRequire::price));
        List<BranchServiceEntity> newBranchServices = services.stream()
                .map(service -> {
                    EBranchServiceStatus status = getBranchServiceStatus(service, staffs);
                    Long price = serviceRequire.computeIfAbsent(service.shopServiceId(), p -> 0L);
                    return BranchServiceEntity.builder()
                            .branchId(createRequire.branchId())
                            .shopServiceId(service.shopServiceId())
                            .branchServicePrice(price)
                            .branchServiceStatusCode(status.getCode())
                            .branchServiceStatusName(status.getName())
                            .build();
                }).toList();

        branchServiceRepository.saveAll(newBranchServices);
    }
}
