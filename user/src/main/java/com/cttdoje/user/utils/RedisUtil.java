package com.cttdoje.user.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 判断key是否存在
     *
     * @param key
     * @return
     */
    public Boolean stringIsExists(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 通过键值自增
     *
     * @param key
     * @return
     */
    public Long stringIncr(String key) {
        return redisTemplate.opsForValue().increment(key);
    }

    /**
     * 设置值，并设置过期时间
     *
     * @param key
     * @param timeOut
     * @return
     */
    public boolean set(String key, Object value, long timeOut) {
        try {
            if (timeOut > 0) {
                redisTemplate.opsForValue().set(key, value, timeOut, TimeUnit.SECONDS);
            } else {
                redisTemplate.opsForValue().set(key, value);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
