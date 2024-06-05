package com.capstone.realmen.repository.redis.account;

import org.springframework.data.redis.core.RedisHash;

import jakarta.persistence.Id;
import lombok.Builder;

@Builder
@RedisHash(timeToLive = 300, value = "account")
public record AccountRedis(
    @Id
    Long accountId,
    String accessToken
) {
}
