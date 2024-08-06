package com.capstone.realmen.controller.api.admin.account;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.realmen.common.request.PageRequestCustom;
import com.capstone.realmen.common.response.PageImplResponse;
import com.capstone.realmen.common.response.ValueResponse;
import com.capstone.realmen.controller.api.admin.account.models.AccountCreatedResponse;
import com.capstone.realmen.controller.api.admin.account.models.AccountRequest;
import com.capstone.realmen.controller.api.admin.account.models.AccountResponse;
import com.capstone.realmen.controller.api.admin.account.models.IAdminAccountModelMapper;
import com.capstone.realmen.data.dto.account.Account;
import com.capstone.realmen.service.account.AccountUseCaseService;
import com.capstone.realmen.service.account.data.AccountCreateRequire;
import com.capstone.realmen.service.account.data.AccountSearchCriteria;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdminAccountController implements IAdminAccountAPI {
    @NonNull
    AccountUseCaseService accountUseCaseService;
    @NonNull
    IAdminAccountModelMapper modelMapper;

    @Override
    public ValueResponse<AccountCreatedResponse> createStaff(@Valid AccountRequest request) {
        Account account = modelMapper.toDto(request);
        return new ValueResponse<AccountCreatedResponse>(
                modelMapper.toModel(
                        accountUseCaseService
                                .adminCreate(AccountCreateRequire.ofStaff(account))));
    }

    @Override
    public PageImplResponse<AccountResponse> findAll(String search, Long branchId, List<String> roles,
            List<String> professionalTypeCodes, List<String> accountStatusCodes, @Min(1) Integer current,
            Integer pageSize) {
        AccountSearchCriteria searchCriteria = AccountSearchCriteria.builder()
                .search(search)
                .branchId(branchId)
                .roles(roles)
                .accountStatusCodes(accountStatusCodes)
                .professionalTypeCodes(professionalTypeCodes)
                .build();
        PageRequestCustom pageRequestCustom = PageRequestCustom.of(current, pageSize);
        Page<AccountResponse> responses = accountUseCaseService.adminFindAll(searchCriteria, pageRequestCustom)
                .map(modelMapper::toModel);
        return new PageImplResponse<>(
                responses.getContent(),
                responses.getTotalElements(),
                responses.getTotalPages(),
                pageRequestCustom.current(),
                pageSize);
    }

}
