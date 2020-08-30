package com.cttdoje.user;

import com.cttdoje.user.service.UserService;
import com.cttdoje.user.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RedisTest {

    @Autowired
    private UserService userService;


    @Test
    public void test1() {
        boolean setResult = RedisUtil.set("spring", "bootDown", 60);
        System.out.println("redis set " + setResult);
    }

    @Test
    public void preTest1(){
        userService.repeatSubmit();
    }

    @Test
    public void preTest2(){
        userService.repeatSubmit();
    }

}
