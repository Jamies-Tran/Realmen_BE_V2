package com.capstone.realmen.service.cache.token;

import org.springframework.stereotype.Service;

import com.capstone.realmen.controller.handler.exceptions.NotFoundException;
import com.capstone.realmen.repository.redis.token.TokenCache;
import com.capstone.realmen.repository.redis.token.TokenRedis;
import com.capstone.realmen.service.cache.token.data.SearchByField;

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

    public TokenRedis findById(SearchByField searchByField) {
        return tokenCache.findById(searchByField.accountId())
            .orElseThrow(() -> new NotFoundException("Không tìm thấy thông tin đang nhập"));
    }
}
