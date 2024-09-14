package com.capstone.realmen.service.branch.others.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.capstone.realmen.common.request.PageRequestCustom;
import com.capstone.realmen.controller.handler.exceptions.NotFoundException;
import com.capstone.realmen.data.dao.branch.service.BranchServiceDAO;
import com.capstone.realmen.data.dto.branch.service.BranchService;
import com.capstone.realmen.data.dto.branch.service.IBranchServiceMapper;
import com.capstone.realmen.data.dto.shop.service.display.ServiceDisplay;
import com.capstone.realmen.repository.database.branch.service.IBranchServiceRepository;
import com.capstone.realmen.service.branch.others.services.data.BranchServiceSearchByField;
import com.capstone.realmen.service.branch.others.services.data.BranchServiceSearchCriteria;
import com.capstone.realmen.service.shop.service.others.display.ServiceDisplayQueryService;
import com.capstone.realmen.service.shop.service.others.display.data.ServiceDisplaySearchByField;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BranchServiceQueryService {
    @NonNull
    IBranchServiceRepository repository;

    @NonNull
    IBranchServiceMapper mapper;

    @NonNull
    ServiceDisplayQueryService sdQuery;

    public Page<BranchService> findAll(BranchServiceSearchCriteria searchCriteria,
            PageRequestCustom pageRequestCustom) {
        return repository.findAll(searchCriteria, pageRequestCustom.pageRequest())
                .map(mapper::toDto);
    }

    public BranchService findById(BranchServiceSearchByField searchByField) {
        BranchServiceDAO foundBranchService = repository.findByBranchIdAndServiceId(searchByField.branchId(), searchByField.serviceId())
                .orElseThrow(NotFoundException::new);
        ServiceDisplaySearchByField sdSearchByField = ServiceDisplaySearchByField
                .ofShopServiceId(foundBranchService.getShopServiceId());
        List<ServiceDisplay> serviceDisplays = sdQuery.findByShopServiceId(sdSearchByField);

        return mapper.toDto(foundBranchService)
                .withServiceDisplays(serviceDisplays);
    }

    public List<BranchService> findAllById(BranchServiceSearchByField searchByField) {
        return repository.findByBranchIdAndServiceIds(searchByField.branchId(), searchByField.serviceIds())
                .stream()
                .map(mapper::toDto)
                .toList();

    }
}
