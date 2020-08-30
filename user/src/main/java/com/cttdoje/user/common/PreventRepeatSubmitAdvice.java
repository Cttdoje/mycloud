package com.cttdoje.user.common;

import com.cttdoje.user.service.BloomFilterHelper;
import com.cttdoje.user.service.BloomFilterServer;
import com.cttdoje.user.utils.RedisLock;
import org.apache.shardingsphere.sharding.route.spi.SPITimeService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

@Aspect
@Component
@Order(1)
public class PreventRepeatSubmitAdvice {

    @Around("@annotation(com.cttdoje.user.common.PreventRepeatSubmit)")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Object[] args = point.getArgs();

        // 1. 获取注解上的值，进行 redis 锁参数设置
        PreventRepeatSubmit preventRepeatSubmit = method.getAnnotation(PreventRepeatSubmit.class);
        String lockName = preventRepeatSubmit.lockName();
        int expire = preventRepeatSubmit.expire();
        int timeOut = preventRepeatSubmit.timeOut();
        if (StringUtils.isEmpty(lockName)) {
            lockName = method.getDeclaringClass().getName() + "." + method.getName();
        }

        // 2. 尝试进行加锁
        RedisLock redisLock = new RedisLock(lockName, expire);
        boolean lockResult;
        if (timeOut > 0) {
            lockResult = redisLock.tryLock(timeOut);
        } else {
            lockResult = redisLock.lock();
        }

        // 3. 判断是否加锁成功
        if (lockResult) {
            try {
                // 4. 进行方法执行
                return point.proceed();
            } finally {
                try {
                    // 5. 释放锁
                    redisLock.unLock();
                } catch (Exception e) {
                    System.out.println("unlock error ...");
                }

            }
        } else {
            System.out.println("获取锁失败....");
            throw new RuntimeException("redis lock error...");
        }

        // todo 思考如果当前 Redis 实例宕机如何处理
    }


    public static void main(String[] args) throws NoSuchMethodException {
        Class clazz = BloomFilterServer.class;
        Method method = clazz.getMethod("put", String.class);
        System.out.println(method.getDeclaringClass().getName());
        System.out.println(method.getName());
    }
}
