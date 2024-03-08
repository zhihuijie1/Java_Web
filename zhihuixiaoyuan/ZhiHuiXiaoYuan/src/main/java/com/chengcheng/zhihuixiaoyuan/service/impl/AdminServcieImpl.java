package com.chengcheng.zhihuixiaoyuan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chengcheng.zhihuixiaoyuan.mapper.AdminMapper;
import com.chengcheng.zhihuixiaoyuan.pojo.Admin;
import com.chengcheng.zhihuixiaoyuan.service.AdminService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("AdminServcieImpl") //spring提供的注解，用于将类声明为spring容器中bean "AdminServcieImpl" - 指定bean的名称
@Transactional //spring提供的注解，用于声明事务，声明类中的方法是事务性的，保证操作的一致性。
//继承ServiceImpl的原因 - ServiceImpl已经实现了IService接口，所以无需自己再实现一遍。直接调用里面的CRUD方法即可。
//ServiceImpl<AdminMapper, Admin> - 传入这两个泛型的目的 - 通过AdminMapper对Admin进行CRUD操作
public class AdminServcieImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
}
