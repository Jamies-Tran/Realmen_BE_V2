package com.capstone.realmen.controller.api.admin.account;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.capstone.realmen.common.response.ValueResponse;
import com.capstone.realmen.controller.api.admin.account.models.AccountCreatedResponse;
import com.capstone.realmen.controller.api.admin.account.models.AccountRequest;

import jakarta.validation.Valid;

@RequestMapping("/web/accounts")
public interface IAdminAccountAPI {
    @PostMapping
    @PreAuthorize("hasAnyRole({'ROLE_SHOPOWNER', 'ROLE_BRANCHMANAGER'})")
    ValueResponse<AccountCreatedResponse> createStaff(@RequestBody @Valid AccountRequest request);

}
