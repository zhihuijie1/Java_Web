package com.chengcheng.zhihuixiaoyuan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chengcheng.zhihuixiaoyuan.pojo.LoginForm;
import com.chengcheng.zhihuixiaoyuan.pojo.Teacher;

public interface TeacherService extends IService<Teacher> {
    Teacher login(LoginForm loginForm);

    Teacher findUserByID(int userId);
}
