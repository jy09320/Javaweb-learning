package com.jy.controller;

import com.jy.anno.Log;
import com.jy.pojo.PageResult;
import com.jy.pojo.Result;
import com.jy.pojo.Student;
import com.jy.pojo.StudentQueryParam;
import com.jy.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/students")
@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;
    @GetMapping
    public Result page(StudentQueryParam studentQueryParam) {
        log.info("分页查询，参数：{}", studentQueryParam);
        PageResult<Student> pageResult =studentService.page(studentQueryParam);
        return Result.success(pageResult);
    }
    @Log
    @DeleteMapping("/{ids}")
    public Result delete(@RequestParam List<Integer> ids) {
        log.info("批量删除，id集合：{}", ids);
        studentService.delete(ids);
        return Result.success();
    }
    @Log
    @PostMapping
    public Result save(@RequestBody Student student) {
        log.info("新增学员，学员信息：{}", student);
        studentService.save(student);
        return Result.success();
    }
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {
        log.info("查询学员详情，id：{}", id);
        Student student = studentService.getInfo(id);
        return Result.success(student);
    }
    @Log
    @PutMapping
    public Result update(@RequestBody Student student) {
        log.info("更新学员，学员信息：{}", student);
        studentService.update(student);
        return Result.success();
    }
    @Log
    @PutMapping("violation/{id}/{score}")
    public Result updateViolation(@PathVariable Integer id,@PathVariable short score) {
        log.info("更新学员违纪，学员信息：{}", id);
        studentService.updateViolation(id,score);
        return Result.success();
    }

}
