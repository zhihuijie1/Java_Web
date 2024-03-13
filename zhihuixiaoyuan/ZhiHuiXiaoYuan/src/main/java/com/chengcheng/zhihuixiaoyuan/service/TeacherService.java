package com.chengcheng.zhihuixiaoyuan.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chengcheng.zhihuixiaoyuan.pojo.LoginForm;
import com.chengcheng.zhihuixiaoyuan.pojo.Teacher;

public interface TeacherService extends IService<Teacher> {
    Teacher login(LoginForm loginForm);

    Teacher findUserByID(int userId);

    IPage<Teacher> getTeachers(Page<Teacher> page, String name,String clazzName);
}
