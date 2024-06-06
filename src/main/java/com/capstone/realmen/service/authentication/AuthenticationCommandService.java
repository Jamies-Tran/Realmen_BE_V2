package com.capstone.realmen.service.authentication;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.capstone.realmen.controller.handler.exceptions.LoginException;
import com.capstone.realmen.controller.security.token.AccessTokenService;
import com.capstone.realmen.data.dto.access.token.AccessToken;
import com.capstone.realmen.data.dto.account.Account;
import com.capstone.realmen.service.account.AccountQueryService;
import com.capstone.realmen.service.account.data.SearchByFieldPhoneOrStaffCode;
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

    public AccessToken create(CreateRequire createRequire) {
        Account foundAccount = accountQueryService.find(
            SearchByFieldPhoneOrStaffCode.of(createRequire.staffCode())
        );
        if(passwordEncoder.matches(createRequire.password(), foundAccount.password())) {
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
}
