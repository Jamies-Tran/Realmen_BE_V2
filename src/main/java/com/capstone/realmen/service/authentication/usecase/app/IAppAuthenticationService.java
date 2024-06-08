package com.capstone.realmen.service.authentication.usecase.app;

import com.capstone.realmen.data.dto.access.token.AccessToken;
import com.capstone.realmen.service.authentication.data.CreateRequire;

public interface IAppAuthenticationService {
    AccessToken appCreateAccessToken(CreateRequire createRequire);
}
