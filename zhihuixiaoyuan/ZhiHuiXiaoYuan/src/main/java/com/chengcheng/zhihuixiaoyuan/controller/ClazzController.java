package com.chengcheng.zhihuixiaoyuan.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chengcheng.zhihuixiaoyuan.pojo.Clazz;
import com.chengcheng.zhihuixiaoyuan.service.ClazzService;
import com.chengcheng.zhihuixiaoyuan.util.Result;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sms/clazzController")
public class ClazzController {
    @Autowired
    ClazzService clazzService;

    //sms/clazzController/getClazzsByOpr/1/3?name=asd+
    @GetMapping("/getClazzsByOpr/{pageNo}/{pageSize}")
    public Result getClazzsByOpr(@PathVariable(value = "pageNo") Integer pageNo, @PathVariable(value = "pageSize") Integer pageSize, @RequestParam(value = "name") String name) {
        Page<Clazz> page = new Page<>(pageNo, pageSize);
        IPage<Clazz> iPage = clazzService.getClazz(page, name);
        return Result.ok(iPage);
    }

}
