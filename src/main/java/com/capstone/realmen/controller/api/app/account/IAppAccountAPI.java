package com.capstone.realmen.controller.api.app.account;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.capstone.realmen.common.response.ValueResponse;
import com.capstone.realmen.controller.api.app.account.models.AccountCreatedResponse;
import com.capstone.realmen.controller.api.app.account.models.AccountRequest;

import jakarta.validation.Valid;

@RequestMapping("/mobile/accounts")
public interface IAppAccountAPI {
    ValueResponse<AccountCreatedResponse> createCustomer(@RequestBody @Valid AccountRequest request);
}
