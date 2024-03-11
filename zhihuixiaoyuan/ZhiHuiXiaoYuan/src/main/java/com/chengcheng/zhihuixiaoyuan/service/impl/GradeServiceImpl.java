package com.chengcheng.zhihuixiaoyuan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chengcheng.zhihuixiaoyuan.mapper.GradeMapper;
import com.chengcheng.zhihuixiaoyuan.pojo.Grade;
import com.chengcheng.zhihuixiaoyuan.service.GradeService;
import freemarker.template.utility.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

@Service("GradeServiceImpl")
@Transactional
public class GradeServiceImpl extends ServiceImpl<GradeMapper, Grade> implements GradeService {
    @Override
    public IPage<Grade> getGradeByOpr(Page<Grade> page, String gradeName) {
        //设置查询条件
        QueryWrapper<Grade> queryWrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(gradeName)){
            queryWrapper.like("name", gradeName);
            System.out.println(gradeName);
        }
        //设置排序规则
        queryWrapper.orderByDesc("id");
        queryWrapper.orderByAsc("name");
        //分页查询数据
        Page<Grade> gradePage = baseMapper.selectPage(page, queryWrapper);
        return gradePage;
    }
}
