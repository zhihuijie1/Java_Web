package com.chengcheng.zhihuixiaoyuan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chengcheng.zhihuixiaoyuan.mapper.AdminMapper;
import com.chengcheng.zhihuixiaoyuan.pojo.Admin;
import com.chengcheng.zhihuixiaoyuan.pojo.LoginForm;
import com.chengcheng.zhihuixiaoyuan.service.AdminService;
import com.chengcheng.zhihuixiaoyuan.util.MD5;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLOutput;

@Service("AdminServcieImpl") //spring提供的注解，用于将类声明为spring容器中bean "AdminServcieImpl" - 指定bean的名称
@Transactional //spring提供的注解，用于声明事务，声明类中的方法是事务性的，保证操作的一致性。
//继承ServiceImpl的原因 - ServiceImpl已经实现了IService接口，所以无需自己再实现一遍。直接调用里面的CRUD方法即可。
//ServiceImpl<AdminMapper, Admin> - 传入这两个泛型的目的 - 通过AdminMapper对Admin进行CRUD操作
public class AdminServcieImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
    /**
     * 登录
     */
    @Override
    public Admin login(LoginForm loginForm) {
        //QueryWrapper对象 -> 用于封装查询条件
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        //eq方法 -> 是一个合并方法，这里表示查询名字与密码都满足的对象
        //相当于 -> select * from database where name = "" and password = "";
        queryWrapper.eq("name", loginForm.getUsername());
        queryWrapper.eq("password", MD5.encrypt(loginForm.getPassword()));
        //selectOne用于查询一条语句，查询的是queryWrapper中封装的语句，返回值封装成Admin对象
        Admin admin = baseMapper.selectOne(queryWrapper);
        return admin;
    }

    @Override
    public Admin findUserByID(int userId) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",userId);
        Admin admin = baseMapper.selectOne(queryWrapper);
        return admin;
    }
}
