package com.chengcheng.zhihuixiaoyuan.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chengcheng.zhihuixiaoyuan.pojo.Clazz;
import com.chengcheng.zhihuixiaoyuan.service.ClazzService;
import com.chengcheng.zhihuixiaoyuan.util.Result;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sms/clazzController")
public class ClazzController {
    @Autowired
    ClazzService clazzService;

    @GetMapping("/getClazzsByOpr/{pageNo}/{pageSize}")
    public Result getClazzsByOpr(@PathVariable(value = "pageNo") Integer pageNo, @PathVariable(value = "pageSize") Integer pageSize, @RequestParam(value = "gradeName", required = false) String gradeName, @RequestParam(value = "name", required = false) String name) {
        Page<Clazz> page = new Page<>(pageNo, pageSize);
        IPage<Clazz> iPage = clazzService.getClazz(page, gradeName, name);
        return Result.ok(iPage);
    }

    @PostMapping("/saveOrUpdateClazz")
    public Result saveOrUpdateClazz(@RequestBody Clazz clazz) {
        clazzService.saveOrUpdate(clazz);
        return Result.ok();
    }

    ///localhost:9001/sms/clazzController/deleteClazz
    @DeleteMapping("/deleteClazz")
    public Result deleteClazz(@RequestBody List<Integer> ids) {
        clazzService.removeByIds(ids);
        return Result.ok();
    }

    @GetMapping("/getClazzs")
    public Result getClazzs() {
        List<Clazz> list = clazzService.getClazzs();
        return Result.ok(list);
    }
}
