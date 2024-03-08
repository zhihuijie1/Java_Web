package com.chengcheng.zhihuixiaoyuan.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 这个类主要是对登录界面的信息进行封装  封装在一个对象里面
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginForm {
    private String userName;
    private String passWord;
    private String verifiCode;
    private Integer userType;
}
