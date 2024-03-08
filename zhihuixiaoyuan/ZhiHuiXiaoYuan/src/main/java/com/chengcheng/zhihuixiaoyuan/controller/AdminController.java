package com.chengcheng.zhihuixiaoyuan.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RestController与@Controller都是将类声明为springMVC控制器，将类中的所有方法声明为处理HTTP请求的方法
//@RestController - 适用于所有方法都返回 JSON 数据的控制器。
//@Controller - 适用于既有返回 JSON 数据的方法，也有返回视图的方法的控制器
@RequestMapping("/sms/adminController") //将方法映射到特定的HTTP请求路径。
public class AdminController {

}
