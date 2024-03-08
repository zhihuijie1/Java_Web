package com.chengcheng.zhihuixiaoyuan.controller;

import com.chengcheng.zhihuixiaoyuan.util.CreateVerifiCodeImage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 前端公共的请求放在这里处理
 */
@RestController
@RequestMapping("/sms/system")
public class SystemController {
    /**生成验证码*/
    @GetMapping("/getVerifiCodeImage")
    public void getVerifiCodeImage(HttpServletRequest request, HttpServletResponse response) {
        //1：拿到验证码图片
        BufferedImage verifiCodeImage = CreateVerifiCodeImage.getVerifiCodeImage();
        //2：拿到验证码数字
        char[] vCode = CreateVerifiCodeImage.getVerifiCode();
        String verifiCode = vCode.toString();
        //3：将验证码数字存放在session域中
        HttpSession session = request.getSession();
        session.setAttribute("verifiCode", verifiCode);
        //4：将验证码图片响应回浏览器中
        try {
            ImageIO.write(verifiCodeImage,"JPEG",response.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
