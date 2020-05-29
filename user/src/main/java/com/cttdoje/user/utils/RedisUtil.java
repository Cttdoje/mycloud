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

    /**
     * 获取列表长度
     *
     * @param key
     * @return
     */
    public long getListLen(String key) {
        long size = -1;
        try {
            size = redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            //todo 处理
        }
        return size;
    }

    public long listLeftPush(String key, Object value) {
        return redisTemplate.opsForList().leftPush(key, value);
    }

    public Object listRightPop(String key) {
        return redisTemplate.opsForList().rightPop(key);
    }

    /**
     * 获取列表中指定位置元素
     *
     * @param key
     * @param index
     * @return
     */
    public Object getListObjectByIndex(String key, long index) {
        return redisTemplate.opsForList().index(key, index);
    }

    public boolean lTrim(String key, long start, long end) {
        try {
            redisTemplate.opsForList().trim(key, start, end);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    public void sortObject(String key){
    }

}
