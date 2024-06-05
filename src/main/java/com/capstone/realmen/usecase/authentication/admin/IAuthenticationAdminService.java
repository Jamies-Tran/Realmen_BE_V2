package com.capstone.realmen.usecase.authentication.admin;

import com.capstone.realmen.data.dto.access.token.AccessToken;

public interface IAuthenticationAdminService {
    AccessToken createAccessToken(String staffCode, String password);
}
