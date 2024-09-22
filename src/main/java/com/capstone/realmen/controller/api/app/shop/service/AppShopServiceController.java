package com.capstone.realmen.controller.api.app.shop.service;

import java.util.List;
import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.realmen.common.request.PageRequestCustom;
import com.capstone.realmen.common.response.PageImplResponse;
import com.capstone.realmen.controller.api.app.shop.service.models.AppShopServiceResponse;
import com.capstone.realmen.controller.api.app.shop.service.models.IAppShopServiceModelMapper;
import com.capstone.realmen.service.branch.others.services.BranchServiceUseCaseService;
import com.capstone.realmen.service.branch.others.services.data.BranchServiceSearchCriteria;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AppShopServiceController implements IAppShopServiceAPI {
        @NonNull
        BranchServiceUseCaseService useCase;

        @NonNull
        IAppShopServiceModelMapper shopServiceModelMapper;

        @Override
        public PageImplResponse<AppShopServiceResponse> findAll(
                        String search,
                        Long branchId,
                        String assignmentTypeCode,
                        List<Long> shopServicePriceRange,
                        Integer current,
                        Integer pageSize) {
                BranchServiceSearchCriteria searchCriteria = BranchServiceSearchCriteria.builder()
                                .search(search)
                                .branchId(branchId)
                                .priceRange(shopServicePriceRange)
                                .assignmentTypeCode(assignmentTypeCode)
                                .build();
                PageRequestCustom pageRequestCustom = PageRequestCustom.of(current, pageSize);
                Page<AppShopServiceResponse> responses = useCase
                                .appFindAll(searchCriteria, pageRequestCustom)
                                .map(shopServiceModelMapper::toModel);
                List<AppShopServiceResponse> verifyResponse = responses.getContent()
                                .stream()
                                .filter(res -> Objects.nonNull(res.branchId()))
                                .toList();
                return new PageImplResponse<>(
                                verifyResponse,
                                Long.valueOf(verifyResponse.size()),
                                responses.getTotalPages(),
                                pageRequestCustom.current(),
                                pageSize);
        }
}
