package com.capstone.realmen.service.authentication.helper.cache;

import org.springframework.stereotype.Service;

import com.capstone.realmen.repository.redis.token.TokenCache;
import com.capstone.realmen.repository.redis.token.TokenRedis;
import com.capstone.realmen.service.authentication.helper.cache.data.OtpCreateRequire;
import com.capstone.realmen.service.authentication.helper.cache.data.OtpSearchByField;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TokenCacheCommandService {
    @NonNull
    TokenCache tokenCache;

    public TokenRedis cachToken(OtpCreateRequire createRequire) {
        if (tokenCache.existsById(createRequire.token().accountId())) {
            tokenCache.delete(createRequire.token());
        }
        return tokenCache.save(createRequire.token());
    }

    public void deleteCache(OtpSearchByField searchByField) {
        tokenCache.deleteById(searchByField.accountId());
    }
}