package com.capstone.realmen.service.authentication;

import org.springframework.stereotype.Service;

import com.capstone.realmen.data.dto.access.token.AccessToken;
import com.capstone.realmen.usecase.authentication.admin.IAuthenticationAdminService;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationUseCaseService implements IAuthenticationAdminService{
    @NonNull
    AuthenticationCommandService authenticationCommandService;
    
    @Override
    public AccessToken createAccessToken(String staffCode, String password) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createAccessToken'");
    }
    
}
