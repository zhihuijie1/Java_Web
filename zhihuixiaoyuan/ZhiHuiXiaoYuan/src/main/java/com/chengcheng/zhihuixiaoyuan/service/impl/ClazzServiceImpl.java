package com.chengcheng.zhihuixiaoyuan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chengcheng.zhihuixiaoyuan.mapper.ClazzMapper;
import com.chengcheng.zhihuixiaoyuan.pojo.Clazz;
import com.chengcheng.zhihuixiaoyuan.service.ClazzService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import java.util.List;

@Service("ClazzServiceImpl")
@Transactional
public class ClazzServiceImpl extends ServiceImpl<ClazzMapper, Clazz> implements ClazzService {
    @Override
    public IPage<Clazz> getClazz(Page<Clazz> page, String gradeName, String name) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(gradeName)) {
            queryWrapper.eq("grade_name", gradeName);
        }
        queryWrapper.orderByDesc("id");
        queryWrapper.orderByAsc("name");
        Page page1 = baseMapper.selectPage(page, queryWrapper);
        return page1;
    }

    @Override
    public List<Clazz> getClazzs() {
        List<Clazz> clazzes = baseMapper.selectList(null);
        return clazzes;
    }
}