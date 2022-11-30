package com.ottAll.ottAll.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.time.Duration;

@Repository
@RequiredArgsConstructor
public class RedisTokenRepository {

    private final RedisTemplate redisTemplate;

    public void save(String userId, String refreshToken){
        ValueOperations<String, String> keyValue = redisTemplate.opsForValue();
        keyValue.set(userId, refreshToken, Duration.ofMillis(1 * 60 * 1000L * 60 * 24 * 14));
    }

    public String getTokenValue(String userId){
        ValueOperations<String, String> keyValue = redisTemplate.opsForValue();
        return keyValue.get(userId);
    }

    public void delValues(String token){
        redisTemplate.delete(token);
    }
}
