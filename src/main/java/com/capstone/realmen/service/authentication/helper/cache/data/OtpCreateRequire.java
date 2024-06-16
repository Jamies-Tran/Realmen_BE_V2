package com.capstone.realmen.service.authentication.helper.cache.data;

import com.capstone.realmen.repository.redis.token.TokenRedis;
import lombok.Builder;
import lombok.With;

@With
@Builder
public record OtpCreateRequire(
                TokenRedis token) {
        public static OtpCreateRequire of(TokenRedis token) {
                return OtpCreateRequire.builder().token(token).build();
        }
}
