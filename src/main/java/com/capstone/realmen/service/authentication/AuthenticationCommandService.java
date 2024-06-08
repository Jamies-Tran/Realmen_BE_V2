package com.capstone.realmen.service.authentication;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.capstone.realmen.controller.handler.exceptions.LoginException;
import com.capstone.realmen.controller.security.token.AccessTokenService;
import com.capstone.realmen.data.dto.access.token.AccessToken;
import com.capstone.realmen.data.dto.account.Account;
import com.capstone.realmen.service.account.AccountQueryService;
import com.capstone.realmen.service.account.data.SearchByField;
import com.capstone.realmen.service.authentication.data.CreateRequire;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationCommandService {
    @NonNull
    AccessTokenService accessTokenService;
    @NonNull
    AccountQueryService accountQueryService;
    @NonNull
    PasswordEncoder passwordEncoder;

    public AccessToken adminCreate(CreateRequire createRequire) {
        Account foundAccount = accountQueryService.find(
                SearchByField.of(createRequire.staffCode()));
        if (!passwordEncoder.matches(createRequire.password(), foundAccount.password())) {
            throw new LoginException();
        }
        String accessToken = accessTokenService.generateJwtToken(createRequire.staffCode());
        return AccessToken.of(foundAccount.accountId(),
                foundAccount.branchId(),
                foundAccount.staffCode(),
                foundAccount.lastName(),
                foundAccount.firstName(),
                accessToken,
                foundAccount.roleCode(),
                foundAccount.roleName());
    }

    public AccessToken appCreate(CreateRequire createRequire) {
        Account foundAccount = accountQueryService.find(
                SearchByField.of(createRequire.phone()));
        if (!passwordEncoder.matches(createRequire.password(), foundAccount.password())) {
            throw new LoginException();
        }
        String accessToken = accessTokenService.generateJwtToken(createRequire.staffCode());
        return AccessToken.of(
            foundAccount.accountId(),
            foundAccount.lastName(),
            foundAccount.firstName(),
            accessToken,
            foundAccount.roleCode(),
            foundAccount.roleName());
    }
}
