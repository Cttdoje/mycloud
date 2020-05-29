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

    private static final String REDIS_LIMITING_PREFIX = "rate.limiting:";

    private static final String LIST_LIMITING_PREFIX = "list.limiting:";

    /**
     * 使用过期机制访问频率限制
     *
     * @param request
     * @return
     */
    @RequestMapping("/userLimiting")
    public String userLimiting(HttpServletRequest request) {
        String remoteAdder = request.getRemoteAddr();
        String currentKey = REDIS_LIMITING_PREFIX + remoteAdder;
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

    /**
     * 使用队列实现访问频率限制
     */
    @RequestMapping("/userLimitingByList")
    public String userLimitingByList(HttpServletRequest request) {
        String remoterAddr = request.getRemoteAddr();
        String currentKey = LIST_LIMITING_PREFIX + remoterAddr;

        long length = redisUtil.getListLen(currentKey);
        if(length < 10){
            redisUtil.listLeftPush(currentKey, System.currentTimeMillis());
        }else{
            long beforeTime = (long) redisUtil.getListObjectByIndex(currentKey,-1);
            if(System.currentTimeMillis() - beforeTime > (60 * 1000)){
                return "访问频率超次数";
            }
            redisUtil.listLeftPush(currentKey,System.currentTimeMillis());
            redisUtil.lTrim(currentKey,0,9);
        }
        return "ok";

    }

}
