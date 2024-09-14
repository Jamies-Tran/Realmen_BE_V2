package com.capstone.realmen.controller.api.app.shop.service;

import org.springframework.web.bind.annotation.RestController;

import com.capstone.realmen.common.response.ValueResponse;
import com.capstone.realmen.controller.api.app.shop.service.models.AppShopServiceResponse;
import com.capstone.realmen.controller.api.app.shop.service.models.IAppShopServiceModelMapper;
import com.capstone.realmen.data.dto.branch.service.BranchService;
import com.capstone.realmen.service.branch.others.services.BranchServiceUseCaseService;
import com.capstone.realmen.service.branch.others.services.data.BranchServiceSearchByField;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AppShopServicePathController implements IAppShopServicePathAPI{
    @NonNull
    BranchServiceUseCaseService useCase;

    @NonNull
    IAppShopServiceModelMapper modelMapper;

    @Override
    public ValueResponse<AppShopServiceResponse> findById(Long shopServiceId, Long branchId) {
        BranchServiceSearchByField searchByField = BranchServiceSearchByField.of(branchId, shopServiceId);
        BranchService foundShopService = useCase.appFindById(searchByField);

        return new ValueResponse<AppShopServiceResponse>(modelMapper.toModel(foundShopService));
    }
}
