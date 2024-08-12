package com.capstone.realmen.controller.api.admin.branch;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.realmen.common.request.PageRequestCustom;
import com.capstone.realmen.common.response.PageImplResponse;
import com.capstone.realmen.controller.api.admin.branch.models.AdminBranchRequest;
import com.capstone.realmen.controller.api.admin.branch.models.AdminBranchResponse;
import com.capstone.realmen.controller.api.admin.branch.models.IAdminBranchModelMapper;
import com.capstone.realmen.controller.api.admin.branch.models.display.IAdminBranchDisplayModelMapper;
import com.capstone.realmen.service.branch.BranchUseCaseService;
import com.capstone.realmen.service.branch.data.BranchCreateRequire;
import com.capstone.realmen.service.branch.data.BranchSearchCriteria;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdminBranchController implements IAdminBranchAPI {
        @NonNull
        BranchUseCaseService branchUseCaseService;

        @NonNull
        IAdminBranchModelMapper modelMapper;

        @NonNull
        IAdminBranchDisplayModelMapper branchDisplayModelMapper;

        @Override
        public void save(@Valid AdminBranchRequest adminBranchRequest) {
                branchUseCaseService.adminCreate(
                                BranchCreateRequire.builder()
                                                .branch(
                                                                modelMapper.toDto(adminBranchRequest)
                                                                                .withOpen(adminBranchRequest.open()
                                                                                                .toLocalTime())
                                                                                .withClose(adminBranchRequest.close()
                                                                                                .toLocalTime()))
                                                .branchDisplays(adminBranchRequest.branchDisplays().stream()
                                                                .map(branchDisplayModelMapper::toDto)
                                                                .toList())
                                                .build());
        }

        @Override
        public PageImplResponse<AdminBranchResponse> findAll(
                        String search,
                        List<String> branchStatusCodes,
                        Integer current,
                        Integer pageSize) {
                BranchSearchCriteria searchCriteria = BranchSearchCriteria.builder()
                                .search(search)
                                .branchStatusCodes(branchStatusCodes)
                                .build();
                PageRequestCustom pageRequestCustom = PageRequestCustom.of(current, pageSize);
                Page<AdminBranchResponse> responses = branchUseCaseService
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
