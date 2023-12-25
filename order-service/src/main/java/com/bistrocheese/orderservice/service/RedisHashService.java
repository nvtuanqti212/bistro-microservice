package com.bistrocheese.orderservice.service;

import java.util.List;

public interface RedisHashService {
    void hSet(String key, Object hashKey, Object value) ;

    List<Object> hValues(String key);

    Object hGet(String key, Object hashKey);
}
