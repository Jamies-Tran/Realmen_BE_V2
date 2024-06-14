package com.capstone.realmen.service.cache.token.usecase.app;

import com.capstone.realmen.repository.redis.token.TokenRedis;
import com.capstone.realmen.service.cache.token.data.CreateRequire;
import com.capstone.realmen.service.cache.token.data.SearchByField;

public interface IAppCacheTokenService {
    TokenRedis appCacheToken(CreateRequire createRequire);

    TokenRedis appFindCacheToken(SearchByField searchByField);

    void appDeleteCacheToken(SearchByField searchByField);
}
