package com.capstone.realmen.repository.redis.token;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenCache extends CrudRepository<TokenRedis, Long> {
    
}
