package com.cttdoje.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootTest
public class PostTest {

    @Autowired
    private RestTemplate restTemplate;

    private volatile AtomicInteger atomicInteger = new AtomicInteger(0);

    @Test
    public void test1() {
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                long star = System.currentTimeMillis();
                for (int j = 0; j < 350; j++) {
                    String obj = restTemplate.getForObject("http://152.136.141.90/v1/runSingleton",
                            String.class);
                    System.err.println("result:" + obj + " =>  " + atomicInteger.incrementAndGet());
                }
                long end = System.currentTimeMillis();
                System.err.println(Thread.currentThread().getName() + ":" + (end - star) + " s");
            }).start();


        }


        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
