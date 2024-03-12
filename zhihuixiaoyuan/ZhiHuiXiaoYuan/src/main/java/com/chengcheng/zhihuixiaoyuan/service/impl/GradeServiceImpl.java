package com.chengcheng.zhihuixiaoyuan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chengcheng.zhihuixiaoyuan.mapper.GradeMapper;
import com.chengcheng.zhihuixiaoyuan.pojo.Grade;
import com.chengcheng.zhihuixiaoyuan.service.GradeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import java.util.LinkedList;
import java.util.List;


@Service("GradeServiceImpl")
@Transactional
public class GradeServiceImpl extends ServiceImpl<GradeMapper, Grade> implements GradeService {
    @Override
    public IPage<Grade> getGradeByOpr(Page<Grade> page, String gradeName) {
        //设置查询条件
        QueryWrapper<Grade> queryWrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(gradeName)){
            queryWrapper.like("name", gradeName);
        }
        //设置排序规则
        queryWrapper.orderByDesc("id");
        queryWrapper.orderByAsc("name");
        //分页查询数据
        Page<Grade> gradePage = baseMapper.selectPage(page, queryWrapper);
        return gradePage;
    }

    @Override
    public List<Grade> getGrade() {
        //是使用 MyBatis Plus 的 selectList 方法从数据库中查询所有的 Grade 实体对象。
        List<Grade> grades = baseMapper.selectList(null);
        return grades;
    }
}
