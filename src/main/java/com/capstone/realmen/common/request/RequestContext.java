package com.capstone.realmen.common.request;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.capstone.realmen.controller.security.token.AccessTokenService;
import com.capstone.realmen.data.dto.account.Account;
import com.capstone.realmen.repository.database.audit.Auditable;
import com.capstone.realmen.service.account.AccountQueryService;
import com.capstone.realmen.service.account.data.AccountSearchByField;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RequestContext {
    @NonNull
    AccessTokenService accessTokenService;
    @NonNull
    AccountQueryService accountQueryService;

    private HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
    }

    public Account getAccount() {
        String identify = accessTokenService.getIdentifyFromHeader(getRequest());
        return accountQueryService.find(AccountSearchByField.of(identify));
    }

    public Auditable auditCreate() {
        return Auditable.ofCreated(getAccount());
    }

    public Auditable auditUpdate() {
        return Auditable.ofUpdated(getAccount());
    }
}
