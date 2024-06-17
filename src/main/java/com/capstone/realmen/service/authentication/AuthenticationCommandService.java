package com.capstone.realmen.service.authentication;

import java.util.Objects;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.capstone.realmen.common.util.AppStorage;
import com.capstone.realmen.controller.handler.exceptions.LoginException;
import com.capstone.realmen.controller.security.token.AccessTokenService;
import com.capstone.realmen.data.dto.access.token.AccessToken;
import com.capstone.realmen.data.dto.account.Account;
import com.capstone.realmen.repository.redis.token.TokenRedis;
import com.capstone.realmen.service.account.AccountQueryService;
import com.capstone.realmen.service.account.data.AccountSearchByField;
import com.capstone.realmen.service.authentication.data.CreateRequire;
import com.capstone.realmen.service.authentication.data.SendOtpRequire;
import com.capstone.realmen.service.authentication.helper.cache.TokenCacheCommandService;
import com.capstone.realmen.service.authentication.helper.cache.TokenCacheQueryService;
import com.capstone.realmen.service.authentication.helper.cache.data.OtpCreateRequire;
import com.capstone.realmen.service.authentication.helper.cache.data.OtpSearchByField;
import com.capstone.realmen.service.authentication.helper.message.MessageCommandService;
import com.capstone.realmen.service.authentication.helper.message.data.SendMessageRequire;

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
        MessageCommandService messageCommandService;
        @NonNull
        TokenCacheCommandService tokenCacheCommandService;
        @NonNull
        TokenCacheQueryService tokenCacheQueryService;
        @NonNull
        PasswordEncoder passwordEncoder;

        public AccessToken adminCreate(CreateRequire createRequire) {
                Account foundAccount = accountQueryService.find(
                                AccountSearchByField.of(createRequire.staffCode()));
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
                                AccountSearchByField.of(createRequire.phone()));
                TokenRedis token = tokenCacheQueryService.findById(
                                OtpSearchByField.of(foundAccount.accountId()));
                String password = Objects.requireNonNullElse(token.accessToken(),
                        foundAccount.password());
                if (!passwordEncoder.matches(createRequire.password(), password)) {
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

        public void appCacheAndSendOtp(SendOtpRequire sendOtpRequire) {
                String otp = AppStorage.otpData().otp();
                String encodeOtp = passwordEncoder.encode(otp);
                Account account = accountQueryService
                                .find(AccountSearchByField.of(sendOtpRequire.phone()));
                TokenRedis token = TokenRedis.builder()
                                .accountId(account.accountId())
                                .accessToken(encodeOtp)
                                .phone(account.phone())
                                .timeToLive(accessTokenService.getJwtDuration())
                                .build();
                OtpCreateRequire createRequire = OtpCreateRequire.of(token);
                TokenRedis cacheToken = tokenCacheCommandService.cachToken(createRequire);
                messageCommandService.sendOtp(
                                SendMessageRequire.builder()
                                                .phone(cacheToken.phone())
                                                .message(String.format("Mã OTP của bạn là: %s", otp))
                                                .build());
        }
}
