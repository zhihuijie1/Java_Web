package com.chengcheng.zhihuixiaoyuan.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chengcheng.zhihuixiaoyuan.pojo.LoginForm;
import com.chengcheng.zhihuixiaoyuan.pojo.Student;
import com.chengcheng.zhihuixiaoyuan.pojo.Teacher;

public interface StudentService extends IService<Student> {
    Student login(LoginForm loginForm);

    Student findUserByID(int i);

    IPage<Student> getStudents(Page<Student> page, String name, String clazzName);

}
