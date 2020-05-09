package com.cttdoje.user.controller;

import com.cttdoje.user.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class RedisController {

    @Autowired
    private RedisUtil redisUtil;

    public static final String REDIS_LIMITING_PREFIX = "rate.limiting:";

    @RequestMapping("/userLimiting")
    public String userLimiting(HttpServletRequest request) {
        String remoteAdder = request.getRemoteAddr();
        String currentKey = REDIS_LIMITING_PREFIX + remoteAdder;
        // 使用过期机制访问频率限制
        boolean isExists = redisUtil.stringIsExists(currentKey);
        if (isExists) {
            long currentTime = redisUtil.stringIncr(currentKey);
            if (currentTime > 10) {
                return "访问频率超次数";
            }
        } else {
            redisUtil.set(currentKey, 1, 60);
        }
        return "ok";
    }

}
