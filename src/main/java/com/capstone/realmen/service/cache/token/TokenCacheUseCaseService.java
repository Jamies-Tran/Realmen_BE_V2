package com.capstone.realmen.service.cache.token;

import org.springframework.stereotype.Service;

import com.capstone.realmen.repository.redis.token.TokenRedis;
import com.capstone.realmen.service.cache.token.data.CreateRequire;
import com.capstone.realmen.service.cache.token.data.SearchByField;
import com.capstone.realmen.service.cache.token.usecase.app.IAppCacheTokenService;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TokenCacheUseCaseService implements IAppCacheTokenService {
    @NonNull
    TokenCacheCommandService tokenCacheCommandService;
    @NonNull
    TokenCacheQueryService tokenCacheQueryService;

    @Override
    public TokenRedis appCacheToken(CreateRequire createRequire) {
        return tokenCacheCommandService.cachToken(createRequire);
    }

    @Override
    public TokenRedis appFindCacheToken(SearchByField searchByField) {
        return tokenCacheQueryService.findById(searchByField);
    }

    @Override
    public void appDeleteCacheToken(SearchByField searchByField) {
        tokenCacheCommandService.deleteCache(searchByField);
    }

}
