package com.chengcheng.springbootredis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class SpringbootRedisApplicationTests {

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    void redisSet() {
        redisTemplate.boundValueOps("girlName").set("壮壮");
    }

    @Test
    void redisGet() {
        Object str = redisTemplate.boundValueOps("girlName").get();
        System.out.println(str);
    }
}
