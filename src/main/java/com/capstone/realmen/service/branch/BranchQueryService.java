package com.capstone.realmen.service.branch;

import java.util.List;
import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.capstone.realmen.common.enums.ERole;
import com.capstone.realmen.common.request.PageRequestCustom;
import com.capstone.realmen.common.request.RequestContext;
import com.capstone.realmen.controller.handler.exceptions.NotFoundException;
import com.capstone.realmen.data.dto.account.Account;
import com.capstone.realmen.data.dto.branch.Branch;
import com.capstone.realmen.data.dto.branch.IBranchMapper;
import com.capstone.realmen.data.dto.branch.distance.DistanceInKm;
import com.capstone.realmen.data.dto.branch.distance.LatLng;
import com.capstone.realmen.data.dto.shop.service.ShopService;
import com.capstone.realmen.repository.database.branch.BranchEntity;
import com.capstone.realmen.repository.database.branch.IBranchRepository;
import com.capstone.realmen.service.account.AccountQueryService;
import com.capstone.realmen.service.account.data.AccountSearchCriteria;
import com.capstone.realmen.service.branch.data.BranchSearchByField;
import com.capstone.realmen.service.branch.data.BranchSearchCriteria;
import com.capstone.realmen.service.shop.service.ShopServiceQueryService;
import com.capstone.realmen.service.shop.service.data.ShopServiceSearchCriteria;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BranchQueryService {
    @NonNull
    IBranchRepository branchRepository;

    @NonNull
    AccountQueryService accountQueryService;

    @NonNull
    ShopServiceQueryService shopServiceQueryService;

    @NonNull
    IBranchMapper branchMapper;

    @NonNull
    RequestContext requestContext;

    public Page<Branch> findAll(BranchSearchCriteria searchCriteria,
            PageRequestCustom pageRequestCustom) {
        return branchRepository.findAll(searchCriteria, pageRequestCustom.pageRequest())
                .map(branchMapper::toDto)
                .map(branch -> {
                    if(Objects.equals(requestContext.getAccount().roleCode(), 
                        ERole.CUSTOMER.getCode())) {
                            LatLng from = LatLng.builder()
                                .latitude(searchCriteria.latitude())
                                .longitude(searchCriteria.longitude())
                                .build();
                            LatLng to = LatLng.builder()
                                .latitude(branch.latitude())
                                .longitude(branch.longitude())
                                .build();
                        return branch.withDistanceInKm(DistanceInKm.of(from, to));
                    }
                    return branch.withDistanceInKm(DistanceInKm.ofDefault());
                });
    }

    public Branch findById(BranchSearchByField searchByField) {
        Long branchId = searchByField.branchId();
        BranchEntity foundBranch = branchRepository.findById(branchId)
            .orElseThrow(NotFoundException::new);
        List<Account> accounts = accountQueryService
            .findAll(
                AccountSearchCriteria.filterStaffOnBranch(branchId), 
                PageRequestCustom.unPaged())
            .getContent(); 
        List<ShopService> services = shopServiceQueryService
            .findAll(
                ShopServiceSearchCriteria.filterBranch(branchId), 
                PageRequestCustom.unPaged())
            .getContent();
        return branchMapper.toDto(foundBranch)
            .withStaffs(accounts)
            .withShopServices(services);   
    }
}
