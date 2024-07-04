package com.capstone.realmen.controller.api.app.account;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.capstone.realmen.common.response.PageImplResponse;
import com.capstone.realmen.common.response.ValueResponse;
import com.capstone.realmen.controller.api.app.account.models.AccountCreatedResponse;
import com.capstone.realmen.controller.api.app.account.models.AccountRequest;
import com.capstone.realmen.controller.api.app.account.models.AccountResponse;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@RequestMapping("/mobile/accounts")
public interface IAppAccountAPI {
    @PostMapping
    ValueResponse<AccountCreatedResponse> createCustomer(@RequestBody @Valid AccountRequest request);

    @GetMapping
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    PageImplResponse<AccountResponse> findAll(
            @RequestParam(required = false, value = "search", defaultValue = "") String search,
            @RequestParam(required = false, value = "branchId", defaultValue = "") Long branchId,
            @RequestParam(required = false, value = "roles", defaultValue = "") List<String> roles,
            @RequestParam(required = false, value = "accountStatusCodes", defaultValue = "") List<String> accountStatusCodes,
            @RequestParam(required = false, value = "current", defaultValue = "1") @Min(1) Integer current,
            @RequestParam(required = false, value = "pageSize", defaultValue = "20") Integer pageSize);

}
