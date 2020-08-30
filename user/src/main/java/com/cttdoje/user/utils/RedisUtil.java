package com.cttdoje.user.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static RedisUtil redisUtil;

    @PostConstruct
    public void init() {
        redisUtil = this;
        redisUtil.redisTemplate = this.redisTemplate;
    }

    private RedisUtil() {
    }

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
    public static boolean set(String key, Object value, long timeOut) {
        try {
            if (timeOut > 0) {
                return redisUtil.redisTemplate.opsForValue().setIfAbsent(key, value, timeOut, TimeUnit.SECONDS);
            } else {
                return redisUtil.redisTemplate.opsForValue().setIfAbsent(key, value);
            }

        } catch (Exception e) {
            return false;
        }
    }

    public static Object get(String key) {
        try {
            return redisUtil.redisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            System.out.println("获取异常...");
            return null;
        }
    }

    public static boolean remove(String key) {
        try {
            return redisUtil.redisTemplate.delete(key);
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

    public void sortObject(String key) {
    }

}
