package com.chengcheng.springbootconfig.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hellocontroller {
    // 资源访问路径
    @RequestMapping("/hello")
    public String hello() {
        return "hello world";
    }
}
