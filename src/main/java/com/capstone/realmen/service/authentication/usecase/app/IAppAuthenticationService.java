package com.capstone.realmen.service.authentication.usecase.app;

import com.capstone.realmen.data.dto.access.token.AccessToken;
import com.capstone.realmen.service.authentication.data.CreateRequire;
import com.capstone.realmen.service.authentication.data.SendOtpRequire;

public interface IAppAuthenticationService {
    AccessToken appCreateAccessTokenForCustomer(CreateRequire createRequire);

    AccessToken appCreateAccessTokenForStaff(CreateRequire createRequire);

    void appSendOtp(SendOtpRequire sendOtpRequire);
}
