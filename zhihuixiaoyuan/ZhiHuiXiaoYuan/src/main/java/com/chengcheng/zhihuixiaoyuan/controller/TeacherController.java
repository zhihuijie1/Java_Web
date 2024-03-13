package com.chengcheng.zhihuixiaoyuan.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chengcheng.zhihuixiaoyuan.pojo.Teacher;
import com.chengcheng.zhihuixiaoyuan.service.TeacherService;
import com.chengcheng.zhihuixiaoyuan.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sms/teacherController")
public class TeacherController {
    @Autowired
    TeacherService teacherService;

    //http://localhost:9001/sms/teacherController/getTeachers/1/3
    @GetMapping("/getTeachers/{pageNo}/{pageSize}")
    public Result getTeachers(@PathVariable(value = "pageNo") Integer pageNo, @PathVariable(value = "pageSize") Integer pageSize, @RequestParam(value = "name", required = false) String name, @RequestParam(value = "clazzName", required = false) String clazzName) {
        Page<Teacher> page = new Page<>(pageNo, pageSize);
        IPage<Teacher> iPage = teacherService.getTeachers(page, name, clazzName);
        return Result.ok(iPage);
    }
}
