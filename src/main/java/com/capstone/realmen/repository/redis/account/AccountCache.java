package com.capstone.realmen.repository.redis.account;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountCache extends CrudRepository<AccountRedis, Long> {
    
}
