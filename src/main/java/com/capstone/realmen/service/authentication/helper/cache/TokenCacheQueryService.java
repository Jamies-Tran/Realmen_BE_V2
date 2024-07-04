package com.capstone.realmen.service.authentication.helper.cache;

import org.springframework.stereotype.Service;

import com.capstone.realmen.repository.redis.token.TokenCache;
import com.capstone.realmen.repository.redis.token.TokenRedis;
import com.capstone.realmen.service.authentication.helper.cache.data.OtpSearchByField;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TokenCacheQueryService {
    @NonNull
    TokenCache tokenCache;

    public TokenRedis findById(OtpSearchByField searchByField) {
        return tokenCache.findById(searchByField.accountId())
            .orElse(TokenRedis.builder().accessToken(null).build());
    }
}
