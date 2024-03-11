package com.chengcheng.zhihuixiaoyuan.controller;

import com.chengcheng.zhihuixiaoyuan.pojo.Admin;
import com.chengcheng.zhihuixiaoyuan.pojo.LoginForm;
import com.chengcheng.zhihuixiaoyuan.pojo.Student;
import com.chengcheng.zhihuixiaoyuan.pojo.Teacher;
import com.chengcheng.zhihuixiaoyuan.service.AdminService;
import com.chengcheng.zhihuixiaoyuan.service.StudentService;
import com.chengcheng.zhihuixiaoyuan.service.TeacherService;
import com.chengcheng.zhihuixiaoyuan.util.CreateVerifiCodeImage;
import com.chengcheng.zhihuixiaoyuan.util.JwtHelper;
import com.chengcheng.zhihuixiaoyuan.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 前端公共的请求放在这里处理
 */
@RestController
@RequestMapping("/sms/system")
public class SystemController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;

    /**
     * 生成验证码
     */
    @GetMapping("/getVerifiCodeImage")
    public void getVerifiCodeImage(HttpServletRequest request, HttpServletResponse response) {
        //1：拿到验证码图片
        BufferedImage verifiCodeImage = CreateVerifiCodeImage.getVerifiCodeImage();
        //2：拿到验证码数字
        char[] vCode = CreateVerifiCodeImage.getVerifiCode();
        String verifiCode = String.valueOf(vCode);
        //3：将验证码数字存放在session域中
        HttpSession session = request.getSession();
        session.setAttribute("verifiCode", verifiCode);
        System.out.println("1session:" + verifiCode);
        //4：将验证码图片响应回浏览器中
        try {
            ImageIO.write(verifiCodeImage, "JPEG", response.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 登录
     */
    //1 先校验一下验证码是否正确
    //2 选择的是哪个角色
    //3 在对应的角色下进行用户名与密码的比对
    //  比对成功的话就以token的形式返回
    @PostMapping("/login")
    public Result login(@RequestBody LoginForm loginForm, HttpServletRequest request) {
        //1 先校验一下验证码是否正确
        //前端输入的验证码与session中的验证码进行比对
        String loginFormVerifiCode = loginForm.getVerifiCode();
        String sessionVerfiCode = (String) request.getSession().getAttribute("verifiCode");
        //sessiion中的验证码失效
        if (sessionVerfiCode.equals("") || sessionVerfiCode.equals(null)) {
            return Result.fail().message("验证码失效，请刷新后重试");
        }
        //验证码错误
        if (!sessionVerfiCode.equalsIgnoreCase(loginFormVerifiCode)) {
            //System.out.println("2session" + sessionVerfiCode + "--" + "login" + loginFormVerifiCode);
            return Result.fail().message("验证码输入错误，请重新输入---");
        }
        //验证码输入正确，要移除当前session中的验证码，为了安全问题。
        request.getSession().removeAttribute("verifiCode");

        //2 选择的是哪个角色
        //3 在对应的角色下进行用户名与密码的比对
        //4 比对成功的话就以token的形式返回
        Map<String, Object> map = new HashMap<>();
        switch (loginForm.getUserType()) {
            case 1:
                try {
                    //调用服务层的方法，根据loginForm来查询用户是否存在
                    Admin login = adminService.login(loginForm);
                    //用户存在
                    if (login != null) {
                        map.put("token", JwtHelper.createToken(login.getId().longValue(), 1));
                    } else {
                        throw new RuntimeException("用户名或者密码有误!");
                    }
                    return Result.ok(map);
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    return Result.fail().message(e.getMessage());
                }
            case 2:
                try {
                    Student login = studentService.login(loginForm);
                    if (login != null) {
                        String token = JwtHelper.createToken(Long.valueOf(login.getId()), 2);
                        map.put("token", token);
                    } else {
                        throw new RuntimeException("用户名或者密码有误！");
                    }
                    return Result.ok(map);
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    return Result.fail().message(e.getMessage());
                }
            case 3:
                try {
                    Teacher login = teacherService.login(loginForm);
                    if (login != null) {
                        String token = JwtHelper.createToken(Long.valueOf(login.getId()), 3);
                        map.put("token", token);
                    } else {
                        throw new RuntimeException("用户名或者密码有误！");
                    }
                    return Result.ok(map);
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    return Result.fail().message(e.getMessage());
                }
        }
        return Result.fail().message("无此用户");
    }

}

//1 为什么没有new Result就可以直接使用Result对象 -- 里面的方法是静态方法
//