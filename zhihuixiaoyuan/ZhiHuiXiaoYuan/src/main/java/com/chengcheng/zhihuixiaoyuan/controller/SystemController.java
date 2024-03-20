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
import com.chengcheng.zhihuixiaoyuan.util.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 前端公共的请求放在这里处理
 */
@RestController
@RequestMapping("/sms/system")
public class SystemController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;

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
     * 登录验证
     */
    //1 先校验一下验证码是否正确
    //2 选择的是哪个角色
    //3 在对应的角色下进行用户名与密码的比对
    //  比对成功的话就以token的形式返回
    @PostMapping("/login")
    public Result login(@RequestBody LoginForm loginForm, HttpServletRequest request) {
        System.out.println(loginForm.getUsername() + loginForm.getPassword() + loginForm.getUserType() + loginForm.getVerifiCode());
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

    @GetMapping("/getInfo")
    public Result getInfo(@RequestHeader("token") String token) {
        //1:根据请求头中的token来找到这个对象
        //2:返回usertype
        //3:返回admin对象

        //1:检查token是否过期
        boolean expiration = JwtHelper.isExpiration(token);
        if (expiration) {
            return Result.build(null, ResultCodeEnum.TOKEN_ERROR);
        }
        //2:根据token中的type来查找对象
        Map<String, Object> map = new HashMap<>();
        switch (JwtHelper.getUserType(token)) {
            case 1:
                //3:找到这个对象，并返回对象与userType
                Admin admin = adminService.findUserByID(JwtHelper.getUserId(token).intValue());
                if (admin == null) {
                    return Result.fail(null);
                }
                map.put("userType", 1);
                map.put("user", admin);
                return Result.ok(map);
            case 2:
                //3:找到这个对象，并返回对象与userType
                Student student = studentService.findUserByID(JwtHelper.getUserId(token).intValue());
                if (student == null) {
                    return Result.fail(null);
                }
                map.put("userType", 2);
                map.put("user", student);
                return Result.ok(map);
            case 3:
                //3:找到这个对象，并返回对象与userType
                Teacher teacher = teacherService.findUserByID(JwtHelper.getUserId(token).intValue());
                if (teacher == null) {
                    return Result.fail(null);
                }
                map.put("userType", 3);
                map.put("user", teacher);
                return Result.ok(map);
        }
        return Result.fail().message("无此用户");
    }

    @PostMapping("/headerImgUpload")
    public Result headerImgUpload(@RequestPart("multipartFile")MultipartFile multipartFile) {
        //使用UUID随机生成文件名
        String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        //生成新的文件名字
        String filename = uuid.concat(multipartFile.getOriginalFilename());
        //生成文件的保存路径(实际生产环境这里会使用真正的文件存储服务器)
        String portraitPath ="E:\\Java_Web\\zhihuixiaoyuan\\ZhiHuiXiaoYuan\\target\\classes\\public\\upload\\".concat(filename);
        //保存文件
        try {
            multipartFile.transferTo(new File(portraitPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String headerImg ="upload/"+filename;
        return Result.ok(headerImg);
    }
}
