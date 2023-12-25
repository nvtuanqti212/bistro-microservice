package com.bistrocheese.orderservice.service.impl;

import com.bistrocheese.orderservice.service.RedisHashService;
import com.bistrocheese.orderservice.utils.MapperUtils;
import jakarta.annotation.PostConstruct;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RedisHashServiceImpl implements RedisHashService {
    private final RedisTemplate<Object, Object> redisTemplate;

    public RedisHashServiceImpl(RedisTemplate<Object, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void hSet(String key, Object hashKey, Object value) {
        var ruleHash = MapperUtils.objectMapper(value, Map.class);
        redisTemplate.opsForHash().put(key, hashKey, ruleHash);
    }

    @Override
    public List<Object> hValues(String key) {
        return redisTemplate.opsForHash().values(key);
    }

    @Override
    public Object hGet(String key, Object hashKey) {
        return redisTemplate.opsForHash().get(key, hashKey);
    }
}
