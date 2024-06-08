package com.capstone.realmen.service.authentication.usecase.admin;

import com.capstone.realmen.data.dto.access.token.AccessToken;
import com.capstone.realmen.service.authentication.data.CreateRequire;

public interface IAdminAuthenticationService {
    AccessToken adminCreateAccessToken(CreateRequire createRequire);
}
