package com.capstone.realmen.controller.api.admin.shop.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.realmen.common.request.PageRequestCustom;
import com.capstone.realmen.common.response.PageImplResponse;
import com.capstone.realmen.controller.api.admin.shop.service.displays.models.ServiceDisplayModelMapper;
import com.capstone.realmen.controller.api.admin.shop.service.models.IAdminShopServiceModelMapper;
import com.capstone.realmen.controller.api.admin.shop.service.models.AdminShopServiceRequest;
import com.capstone.realmen.controller.api.admin.shop.service.models.AdminShopServiceResponse;
import com.capstone.realmen.service.shop.service.ShopServiceUseCaseService;
import com.capstone.realmen.service.shop.service.data.ShopServiceCreateRequire;
import com.capstone.realmen.service.shop.service.data.ShopServiceSearchCriteria;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ShopServiceController implements IShopServiceAPI {
        @NonNull
        ShopServiceUseCaseService shopServiceUseCaseService;
        @NonNull
        IAdminShopServiceModelMapper shopServiceModelMapper;
        @NonNull
        ServiceDisplayModelMapper serviceDisplayModelMapper;

        @Override
        public void save(@Valid AdminShopServiceRequest serviceRequest) {
                shopServiceUseCaseService.adminCreateShopService(
                                ShopServiceCreateRequire.builder()
                                                .shopService(shopServiceModelMapper.toDto(serviceRequest))
                                                .serviceDisplays(serviceRequest
                                                                .serviceDisplays().stream()
                                                                .map(serviceDisplayModelMapper::toDto)
                                                                .toList())
                                                .build());
        }

        @Override
        public PageImplResponse<AdminShopServiceResponse> findAll(String search, Long shopCategoryId,
                        List<Long> shopServicePriceRange, Integer current, Integer pageSize) {
                ShopServiceSearchCriteria searchCriteria = ShopServiceSearchCriteria.builder()
                                .search(search)
                                .shopCategoryId(shopCategoryId)
                                .shopServicePriceRange(shopServicePriceRange)
                                .build();
                PageRequestCustom pageRequestCustom = PageRequestCustom.of(current, pageSize);
                Page<AdminShopServiceResponse> responses = shopServiceUseCaseService
                                .adminFindAll(searchCriteria, pageRequestCustom)
                                .map(shopServiceModelMapper::toModel);
                return new PageImplResponse<>(
                        responses.getContent(), 
                        responses.getTotalElements(), 
                        responses.getTotalPages(), 
                        pageRequestCustom.current(), 
                        pageSize);
        }
}
