package com.capstone.realmen.controller.api.app.authentication;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.capstone.realmen.common.response.ValueResponse;
import com.capstone.realmen.controller.api.app.authentication.models.AppAccessTokenForStaffRequest;
import com.capstone.realmen.controller.api.app.authentication.models.AppAccessTokenRequest;
import com.capstone.realmen.controller.api.app.authentication.models.AppAccessTokenResponse;

import jakarta.validation.Valid;

@RequestMapping("/mobile/auth")
public interface IAppAuthenticationAPI {
    @GetMapping("/send-otp")
    void sendOtp(
            @RequestParam String phone);

    @PostMapping("/customer")
    ValueResponse<AppAccessTokenResponse> getAccessTokenForCustomer(@RequestBody @Valid AppAccessTokenRequest accessTokenRequest);

    @PostMapping("/staff")
    ValueResponse<AppAccessTokenResponse> getAccessTokenForStaff(@RequestBody @Valid AppAccessTokenForStaffRequest accessTokenRequest);
}
