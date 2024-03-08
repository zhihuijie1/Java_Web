package com.chengcheng.zhihuixiaoyuan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chengcheng.zhihuixiaoyuan.pojo.Admin;
import org.springframework.stereotype.Repository;

@Repository //spring提供的接口，用于将接口声明为spring容器中的bean
public interface AdminMapper extends BaseMapper<Admin> {}
//BaseMapper提供了基本的对数据库进行CRUD的接口，可以简化Mapper接口的开发，无需手动编写CRUD操作方法。

//BaseMapper<Admin> 泛型传入的是Admin实体类的原因是：1：Mapper接口中方法都以Admin作为参数或返回值 2：Mapper只对Admin进行CRUD
//使Mapper的操作更加的安全
//总之将Mapper操作聚焦在Admin实体类上
