package com.example.redisdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redisTestController")
public class redisTestController {
    @Autowired
    RedisTemplate redisTemplate;

    @GetMapping("/getRdis")
    public String testRedis() {
        System.out.println("我进来了");
        redisTemplate.opsForValue().set("somewords", "chengcheng && pengpeng && forever");
        String jj = (String) redisTemplate.opsForValue().get("somewords");
        System.out.println(jj);
        return jj;
    }
}
