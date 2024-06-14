package com.capstone.realmen.service.cache.token;

import org.springframework.stereotype.Service;

import com.capstone.realmen.repository.redis.token.TokenCache;
import com.capstone.realmen.repository.redis.token.TokenRedis;
import com.capstone.realmen.service.cache.token.data.CreateRequire;
import com.capstone.realmen.service.cache.token.data.SearchByField;

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

    public TokenRedis cachToken(CreateRequire createRequire) {
        return tokenCache.save(createRequire.token());
    }

    public void deleteCache(SearchByField searchByField) {
        tokenCache.deleteById(searchByField.accountId());
    }
}
