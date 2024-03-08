package com.chengcheng.zhihuixiaoyuan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chengcheng.zhihuixiaoyuan.pojo.LoginForm;
import com.chengcheng.zhihuixiaoyuan.pojo.Teacher;

public interface TeacherService extends IService<Teacher> {
    static Teacher login(LoginForm loginForm) {
        return null;
    }
}
