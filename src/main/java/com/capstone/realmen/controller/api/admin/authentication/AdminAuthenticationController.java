package com.capstone.realmen.controller.api.admin.authentication;

import org.springframework.web.bind.annotation.RestController;

import com.capstone.realmen.common.response.ValueResponse;
import com.capstone.realmen.controller.api.admin.authentication.models.AdminAccessTokenMapper;
import com.capstone.realmen.controller.api.admin.authentication.models.AdminAccessTokenRequest;
import com.capstone.realmen.controller.api.admin.authentication.models.AdminAccessTokenResponse;
import com.capstone.realmen.service.authentication.AuthenticationUseCaseService;
import com.capstone.realmen.service.authentication.data.CreateRequire;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdminAuthenticationController implements IAdminAuthenticationAPI{
    @NonNull
    AuthenticationUseCaseService authenticationUseCaseService;
    @NonNull
    AdminAccessTokenMapper adminAccessTokenMapper;
    
    @Override
    public ValueResponse<AdminAccessTokenResponse> getAccessToken(@Valid AdminAccessTokenRequest accessTokenRequest) {
        AdminAccessTokenResponse response = adminAccessTokenMapper.toModel(
            authenticationUseCaseService
                .adminCreateAccessToken(CreateRequire
                    .byStaffCode(accessTokenRequest.staffCode(), accessTokenRequest.password()))
        );
        return new ValueResponse<AdminAccessTokenResponse>(response);
    }
    
}
