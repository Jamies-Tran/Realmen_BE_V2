package com.capstone.realmen.service.cache.token.data;

import com.capstone.realmen.repository.redis.token.TokenRedis;
import lombok.Builder;
import lombok.With;

@With
@Builder
public record CreateRequire(
        TokenRedis token) {
}
