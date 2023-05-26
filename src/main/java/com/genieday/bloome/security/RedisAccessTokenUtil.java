package com.genieday.bloome.security;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class RedisAccessTokenUtil {

    private final String PREFIX = "accesstoken:";
    private final String BLOCK_PREFIX = "block:";

    private final int LIMIT_TIME = 60 * 60; // 1h
    private final StringRedisTemplate stringRedisTemplate;

    public void saveAccessToken(String idName, String jwt) {
        stringRedisTemplate.opsForValue().set(PREFIX + idName, jwt, Duration.ofSeconds(LIMIT_TIME));
    }

    public void saveBlockAccessToken(String jwt) {
        stringRedisTemplate.opsForValue().set(BLOCK_PREFIX + jwt, "block", Duration.ofSeconds(LIMIT_TIME));
    }

    public String getAccessToken(String idName) {
        return stringRedisTemplate.opsForValue().get(PREFIX + idName);
    }

    public void deleteAccessToken(String idName) {
        stringRedisTemplate.delete(PREFIX + idName);
    }

    public boolean hasAccessToken(String idName) {
        return stringRedisTemplate.hasKey(PREFIX + idName);
    }

    public boolean hasBlockAccessToken(String jwt) {
        return stringRedisTemplate.hasKey(BLOCK_PREFIX + jwt);
    }
}
