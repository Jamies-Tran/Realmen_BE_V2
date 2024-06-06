package com.capstone.realmen.controller.api.admin.authentication;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.capstone.realmen.common.response.ValueResponse;
import com.capstone.realmen.controller.api.admin.authentication.models.AccessTokenRequest;
import com.capstone.realmen.controller.api.admin.authentication.models.AccessTokenResponse;

import jakarta.validation.Valid;

@RequestMapping("/web/auth")
public interface IAuthenticationAPI {

    @PostMapping
    public ValueResponse<AccessTokenResponse> getAccessToken(@RequestBody @Valid AccessTokenRequest accessTokenRequest);
}
