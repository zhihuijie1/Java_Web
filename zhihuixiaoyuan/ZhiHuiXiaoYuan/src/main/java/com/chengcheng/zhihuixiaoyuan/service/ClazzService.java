package com.chengcheng.zhihuixiaoyuan.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chengcheng.zhihuixiaoyuan.pojo.Clazz;

import java.util.List;

public interface ClazzService extends IService<Clazz> {
    IPage<Clazz> getClazz(Page<Clazz> page, String gradeName, String name);

    List<Clazz> getClazzs();

}
