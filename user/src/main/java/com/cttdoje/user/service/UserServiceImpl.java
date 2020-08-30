package com.cttdoje.user.service;


import com.cttdoje.user.common.PreventRepeatSubmit;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService{

    @Override
    @PreventRepeatSubmit(lockName = "spring-boot-repeat",expire = 30,timeOut = 10)
    public void repeatSubmit() {
        try {
            System.out.println("开始调用服务");
            TimeUnit.SECONDS.sleep(25);
            System.out.println("服务调用完成");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
