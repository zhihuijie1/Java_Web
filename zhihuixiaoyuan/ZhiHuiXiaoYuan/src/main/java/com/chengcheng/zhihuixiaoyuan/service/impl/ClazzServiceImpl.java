package com.chengcheng.zhihuixiaoyuan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chengcheng.zhihuixiaoyuan.mapper.ClazzMapper;
import com.chengcheng.zhihuixiaoyuan.pojo.Clazz;
import com.chengcheng.zhihuixiaoyuan.service.ClazzService;
import com.mysql.cj.util.StringUtils;

public class ClazzServiceImpl extends ServiceImpl<ClazzMapper, Clazz> implements ClazzService {
    @Override
    public IPage<Clazz> getClazz(Page<Clazz> page, String name) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if (!StringUtils.isNullOrEmpty(name)) {
            queryWrapper.like("name", name);
        }
        queryWrapper.orderByDesc("id");
        queryWrapper.orderByAsc("name");
        Page page1 = baseMapper.selectPage(page, queryWrapper);
        return page1;
    }
}
