package com.capstone.realmen.controller.api.admin.shop.category;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.realmen.common.request.PageRequestCustom;
import com.capstone.realmen.common.response.PageImplResponse;
import com.capstone.realmen.controller.api.admin.shop.category.models.AdminShopCategoryRequest;
import com.capstone.realmen.controller.api.admin.shop.category.models.AdminShopCategoryResponse;
import com.capstone.realmen.controller.api.admin.shop.category.models.IAdminShopCategoryModelMapper;
import com.capstone.realmen.service.shop.category.ShopCategoryUseCaseService;
import com.capstone.realmen.service.shop.category.data.ShopCategoryCreateRequire;
import com.capstone.realmen.service.shop.category.data.ShopCategorySearchCriteria;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdminShopCategoryController implements IAdminShopCategoryAPI {
    @NonNull
    ShopCategoryUseCaseService shopCategoryUseCaseService;
    @NonNull
    IAdminShopCategoryModelMapper modelMapper;

    @Override
    public void save(AdminShopCategoryRequest shopCategoryRequest) {
        shopCategoryUseCaseService.adminCreate(
                ShopCategoryCreateRequire.builder()
                        .shopCategory(modelMapper.toDto(shopCategoryRequest))
                        .build());
    }

    @Override
    public PageImplResponse<AdminShopCategoryResponse> findAll(String search, 
        Integer serviceNumber, Integer current, Integer pageSize) {
        ShopCategorySearchCriteria searchCriteria = ShopCategorySearchCriteria.builder()
            .search(search)
            .serviceNumber(serviceNumber)
            .build();
        PageRequestCustom pageRequestCustom = PageRequestCustom.of(current, pageSize);
        Page<AdminShopCategoryResponse> responses = shopCategoryUseCaseService
            .adminFindAll(searchCriteria, pageRequestCustom)
            .map(modelMapper::toModel);
        return new PageImplResponse<>(
            responses.getContent(), 
            responses.getTotalElements(), 
            responses.getTotalPages(), 
            pageRequestCustom.current(), 
            pageSize);
    }

}
