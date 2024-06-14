package com.capstone.realmen.service.authentication;

import org.springframework.stereotype.Service;

import com.capstone.realmen.data.dto.access.token.AccessToken;
import com.capstone.realmen.repository.redis.token.TokenRedis;
import com.capstone.realmen.service.authentication.data.CreateRequire;
import com.capstone.realmen.service.authentication.usecase.admin.IAdminAuthenticationService;
import com.capstone.realmen.service.authentication.usecase.app.IAppAuthenticationService;
import com.capstone.realmen.service.cache.token.TokenCacheUseCaseService;

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
    @NonNull
    TokenCacheUseCaseService tokenCacheUseCaseService;

    @Override
    public AccessToken adminCreateAccessToken(CreateRequire createRequire) {
        return authenticationCommandService.adminCreate(createRequire);
    }

    @Override
    public AccessToken appCreateAccessToken(CreateRequire createRequire) {
        return authenticationCommandService.appCreate(createRequire);
    }

    @Override
    public TokenRedis appCachAccessToken(com.capstone.realmen.service.cache.token.data.CreateRequire createRequire) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'appCachAccessToken'");
    }

}
