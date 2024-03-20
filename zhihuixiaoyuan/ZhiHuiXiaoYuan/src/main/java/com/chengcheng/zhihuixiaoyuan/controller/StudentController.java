package com.chengcheng.zhihuixiaoyuan.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chengcheng.zhihuixiaoyuan.pojo.Student;
import com.chengcheng.zhihuixiaoyuan.service.StudentService;
import com.chengcheng.zhihuixiaoyuan.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sms/studentController")
public class StudentController {
    @Autowired
    StudentService studentService;

    //http://localhost:9001/sms/teacherController/getTeachers/1/3
    @GetMapping("/getStudentByOpr/{pageNo}/{pageSize}")
    public Result getStudentByOpr(@PathVariable(value = "pageNo") Integer pageNo, @PathVariable(value = "pageSize") Integer pageSize, @RequestParam(value = "name", required = false) String name, @RequestParam(value = "clazzName", required = false) String clazzName) {
        Page<Student> page = new Page<>(pageNo, pageSize);
        IPage<Student> iPage = studentService.getStudents(page, name, clazzName);
        return Result.ok(iPage);
    }

    @PostMapping("/addOrUpdateStudent")
    public Result addOrUpdateStudent(@RequestBody Student student) {
        studentService.saveOrUpdate(student);
        return Result.ok();
    }

    @DeleteMapping("/delStudentById")
    public Result delStudentById(@RequestBody List<Integer> ids) {
        studentService.removeByIds(ids);
        return Result.ok();
    }
}
