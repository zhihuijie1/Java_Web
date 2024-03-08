package com.chengcheng.zhihuixiaoyuan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chengcheng.zhihuixiaoyuan.pojo.Student;
import org.springframework.stereotype.Repository;

@Repository //spring提供的接口，用于将接口声明为spring容器中的bean
public interface StudentMapper extends BaseMapper<Student> {}
