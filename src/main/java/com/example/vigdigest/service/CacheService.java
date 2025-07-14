package com.example.vigdigest.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Simple cache combining in-memory store and Redis.
 */
@Service
public class CacheService {
    private final Map<String, String> memory = new ConcurrentHashMap<>();
    private final StringRedisTemplate redis;

    public CacheService(StringRedisTemplate redis) {
        this.redis = redis;
    }

    public void putMemory(String key, String value, Duration ttl) {
        memory.put(key, value);
        // TTL management omitted for brevity
    }

    public String getMemory(String key) {
        return memory.get(key);
    }

    public void putRedis(String key, String value, Duration ttl) {
        redis.opsForValue().set(key, value, ttl);
    }

    public String getRedis(String key) {
        return redis.opsForValue().get(key);
    }
}
