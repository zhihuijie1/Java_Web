package com.chengcheng.seckill.controller;

import com.chengcheng.seckill.utils.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "toLogin";
    }

    @PostMapping("/doLogin")
    public Result doLogin() {
        return null;
    }
}
