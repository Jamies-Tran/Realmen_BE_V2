package com.capstone.realmen.repository.redis.token;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import lombok.Builder;
import lombok.With;

@With
@Builder
@RedisHash(value = "token")
public record TokenRedis(
                @Id Long accountId,
                String phone,
                String accessToken,
                @TimeToLive Long timeToLive) {
}
