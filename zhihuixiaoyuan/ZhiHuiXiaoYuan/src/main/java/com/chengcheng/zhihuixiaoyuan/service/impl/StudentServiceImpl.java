package com.chengcheng.zhihuixiaoyuan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chengcheng.zhihuixiaoyuan.mapper.StudentMapper;
import com.chengcheng.zhihuixiaoyuan.pojo.Admin;
import com.chengcheng.zhihuixiaoyuan.pojo.LoginForm;
import com.chengcheng.zhihuixiaoyuan.pojo.Student;
import com.chengcheng.zhihuixiaoyuan.service.StudentService;
import com.chengcheng.zhihuixiaoyuan.util.MD5;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("StudentServiceImpl")
@Transactional
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
    @Override
    public Student login(LoginForm loginForm) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", loginForm.getUsername());
        queryWrapper.eq("password", MD5.encrypt(loginForm.getPassword()));
        Student student = baseMapper.selectOne(queryWrapper);
        return student;
    }

    @Override
    public Student findUserByID(int userId) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", userId);
        Student student = baseMapper.selectOne(queryWrapper);
        return student;
    }
}
