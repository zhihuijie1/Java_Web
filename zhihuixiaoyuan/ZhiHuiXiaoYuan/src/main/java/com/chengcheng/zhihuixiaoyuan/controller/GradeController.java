package com.chengcheng.zhihuixiaoyuan.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chengcheng.zhihuixiaoyuan.pojo.Grade;
import com.chengcheng.zhihuixiaoyuan.service.GradeService;
import com.chengcheng.zhihuixiaoyuan.util.Result;
import com.sun.org.apache.regexp.internal.RE;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sms/gradeController")
public class GradeController {
    @Autowired
    GradeService gradeService;

    //localhost:9001/sms/gradeController/getGrades/1/3?gradeName=%E5%9C%B0%E6%96%B9
    //@PathVariable -- 用于从URL的路径部分提取变量,提取变量的形式 -> /users/{userId}
    //@RequestParam -- 用于从URL的查询参数中提取变量。查询参数是附加在URL末尾，并以?开始的一系列键=值对。
    @GetMapping("/getGrades/{pageNo}/{pageSize}")
    public Result getGrades(@PathVariable(value = "pageNo") Integer pageNo, @PathVariable(value = "pageSize") Integer pageSize, @RequestParam(value = "gradeName") String gradeName) {
        //设置分页信息
        //分页查询是一种数据查询策略，用于一次只检索部分数据库记录，这对于处理大量数据非常有用，因为它可以减少内存的使用并提高应用性能。
        //这一行创建了一个Page对象，它是MyBatis Plus库提供的，用于封装分页请求的详细信息，如当前页码pageNo和每页显示的记录数pageSize。
        Page<Grade> page = new Page<>(pageNo, pageSize);
        //调用服务层方法，传入分页信息和查询条件
        //服务层使用这些信息从数据库查询相应的记录，并将结果封装回IPage<Grade>对象。
        //IPage对象不仅包含当前页的数据列表，还包含总记录数、总页数等分页相关的信息，这对于在客户端实现分页显示非常有用。
        IPage<Grade> pageRs = gradeService.getGradeByOpr(page, gradeName);
        return Result.ok(pageRs);
    }

    @PostMapping("/saveOrUpdateGrade")
    public Result saveOrUpdateGrade(@RequestBody Grade grade) {
        gradeService.saveOrUpdate(grade);//系统中自带的
        return Result.ok();
    }

    @DeleteMapping("/deleteGrade")
    public Result deleteGrade(@RequestBody List<Integer> ids) {
        gradeService.removeByIds(ids);
        return Result.ok();
    }
}
