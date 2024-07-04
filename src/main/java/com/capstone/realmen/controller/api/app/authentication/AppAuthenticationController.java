package com.capstone.realmen.controller.api.app.authentication;

import org.springframework.web.bind.annotation.RestController;

import com.capstone.realmen.common.response.ValueResponse;
import com.capstone.realmen.controller.api.app.authentication.models.AppAccessTokenForStaffRequest;
import com.capstone.realmen.controller.api.app.authentication.models.AppAccessTokenRequest;
import com.capstone.realmen.controller.api.app.authentication.models.AppAccessTokenResponse;
import com.capstone.realmen.controller.api.app.authentication.models.IAppAccessTokenModelMapper;
import com.capstone.realmen.service.authentication.AuthenticationUseCaseService;
import com.capstone.realmen.service.authentication.data.CreateRequire;
import com.capstone.realmen.service.authentication.data.SendOtpRequire;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AppAuthenticationController implements IAppAuthenticationAPI {
        @NonNull
        AuthenticationUseCaseService authenticationUseCaseService;
        @NonNull
        IAppAccessTokenModelMapper modelMapper;

        @Override
        public void sendOtp(String phone) {
                authenticationUseCaseService.appSendOtp(
                                SendOtpRequire.builder()
                                                .phone(phone)
                                                .build());
        }

        @Override
        public ValueResponse<AppAccessTokenResponse> getAccessTokenForCustomer(
                        @Valid AppAccessTokenRequest accessTokenRequest) {
                AppAccessTokenResponse accessToken = modelMapper.toModel(
                                authenticationUseCaseService.appCreateAccessTokenForCustomer(
                                                CreateRequire.builder()
                                                                .identify(accessTokenRequest.phone())
                                                                .password(accessTokenRequest.otp())
                                                                .build()));
                return new ValueResponse<AppAccessTokenResponse>(accessToken);
        }

        @Override
        public ValueResponse<AppAccessTokenResponse> getAccessTokenForStaff(
                        @Valid AppAccessTokenForStaffRequest accessTokenRequest) {
                AppAccessTokenResponse accessToken = modelMapper.toModel(
                                authenticationUseCaseService.appCreateAccessTokenForStaff(
                                                CreateRequire.builder()
                                                                .identify(accessTokenRequest.staffCode())
                                                                .password(accessTokenRequest.password())
                                                                .build()));
                return new ValueResponse<AppAccessTokenResponse>(accessToken);
        }

}
