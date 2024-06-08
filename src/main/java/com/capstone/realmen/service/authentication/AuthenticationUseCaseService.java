package com.capstone.realmen.service.authentication;

import org.springframework.stereotype.Service;

import com.capstone.realmen.data.dto.access.token.AccessToken;
import com.capstone.realmen.service.authentication.data.CreateRequire;
import com.capstone.realmen.service.authentication.usecase.admin.IAdminAuthenticationService;
import com.capstone.realmen.service.authentication.usecase.app.IAppAuthenticationService;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationUseCaseService implements IAdminAuthenticationService, IAppAuthenticationService {
    @NonNull
    AuthenticationCommandService authenticationCommandService;

    @Override
    public AccessToken adminCreateAccessToken(CreateRequire createRequire) {
        return authenticationCommandService.adminCreate(createRequire);
    }

    @Override
    public AccessToken appCreateAccessToken(CreateRequire createRequire) {
        return authenticationCommandService.appCreate(createRequire);
    }

}
