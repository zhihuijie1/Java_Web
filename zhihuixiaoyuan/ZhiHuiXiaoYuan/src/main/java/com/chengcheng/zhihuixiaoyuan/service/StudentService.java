package com.chengcheng.zhihuixiaoyuan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chengcheng.zhihuixiaoyuan.pojo.LoginForm;
import com.chengcheng.zhihuixiaoyuan.pojo.Student;

public interface StudentService extends IService<Student> {
    static Student login(LoginForm loginForm) {
        return null;
    }
}
