package com.jy.controller;

import com.jy.pojo.Clazz;
import com.jy.pojo.ClazzQueryParam;
import com.jy.pojo.PageResult;
import com.jy.pojo.Result;
import com.jy.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/clazzs")
@RestController
public class ClazzController {
    @Autowired
    private ClazzService clazzService;
    @GetMapping
    public Result page(ClazzQueryParam clazzQueryParam) {
        log.info("查询所有班级信息,{}", clazzQueryParam);
        PageResult<Clazz> pageResult = clazzService.page(clazzQueryParam);
        return Result.success(pageResult);
    }
    @DeleteMapping({"/{id}"})
    public Result delete(@PathVariable Integer id) {
        log.info("删除班级信息,{}", id);
        clazzService.deleteById(id);
        return Result.success();
    }

    @PostMapping
    public Result save(@RequestBody Clazz clazz) {
        log.info("添加班级信息,{}", clazz);
        clazzService.save(clazz);
        return Result.success();
    }
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {
        log.info("获取班级信息,{}", id);
        Clazz clazz = clazzService.getInfo(id);
        return Result.success(clazz);
    }
    @PutMapping
    public Result update(@RequestBody Clazz clazz) {
        log.info("修改班级信息,{}", clazz);
        clazzService.update(clazz);
        return Result.success();
    }
    @GetMapping("/list")
    public Result list() {
        log.info("获取班级列表");
        return Result.success(clazzService.list());
    }

}
