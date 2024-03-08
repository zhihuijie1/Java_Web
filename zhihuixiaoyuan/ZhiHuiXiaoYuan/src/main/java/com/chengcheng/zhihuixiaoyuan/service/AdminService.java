package com.chengcheng.zhihuixiaoyuan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chengcheng.zhihuixiaoyuan.pojo.Admin;

public interface AdminService extends IService<Admin> {}
//继承IService<Admin>的原因是：Iservice中有很多的CRUD方法 可以直接拿来使用。
//将Admin作为泛型传入-可以精准的确定CRUD的对象是Admin实体类
