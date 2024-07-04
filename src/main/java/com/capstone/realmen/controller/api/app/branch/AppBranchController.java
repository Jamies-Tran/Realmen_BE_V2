package com.capstone.realmen.controller.api.app.branch;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.realmen.common.request.PageRequestCustom;
import com.capstone.realmen.common.response.PageImplResponse;
import com.capstone.realmen.controller.api.app.branch.models.AppBranchResponse;
import com.capstone.realmen.controller.api.app.branch.models.IAppBranchModelMapper;
import com.capstone.realmen.service.branch.BranchUseCaseService;
import com.capstone.realmen.service.branch.data.BranchSearchCriteria;

import lombok.experimental.FieldDefaults;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AppBranchController implements IAppBranchAPI {
    @NonNull
    BranchUseCaseService branchUseCaseService;

    @NonNull
    IAppBranchModelMapper modelMapper;

    @Override
    public PageImplResponse<AppBranchResponse> findAll(
            String search,
            Double latitude,
            Double longitude,
            List<String> branchStatusCodes,
            Integer current,
            Integer pageSize) {
        BranchSearchCriteria searchCriteria = BranchSearchCriteria.builder()
                .search(search)
                .latitude(latitude)
                .longitude(longitude)
                .branchStatusCodes(branchStatusCodes)
                .build();
        PageRequestCustom pageRequestCustom = PageRequestCustom.of(current, pageSize);
        Page<AppBranchResponse> responses = branchUseCaseService
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
