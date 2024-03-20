package com.chengcheng.zhihuixiaoyuan.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chengcheng.zhihuixiaoyuan.pojo.Admin;
import com.chengcheng.zhihuixiaoyuan.pojo.LoginForm;

//继承IService<Admin>的原因是：Iservice中有很多的CRUD方法 可以直接拿来使用。
//将Admin作为泛型传入-可以精准的确定CRUD的对象是Admin实体类
public interface AdminService extends IService<Admin> {
    Admin login(LoginForm loginForm);

    Admin findUserByID(int userId);

    IPage<Admin> getAdmins(Page<Admin> page, String adminName);

}

