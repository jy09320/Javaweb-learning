package com.jy.controller;

import com.jy.pojo.Emp;
import com.jy.pojo.EmpQueryParam;
import com.jy.pojo.PageResult;
import com.jy.pojo.Result;
import com.jy.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {
    @Autowired
    private EmpService empService;
//    @GetMapping()
//    public Result Page(@RequestParam(defaultValue = "1") Integer page,
//                       @RequestParam (defaultValue = "10") Integer pageSize,
//                       String name, Integer gender,
//                       @DateTimeFormat (pattern = "yyyy-MM-dd") LocalDate begin,
//                       @DateTimeFormat (pattern = "yyyy-MM-dd") LocalDate end) {
//        log.info("查询请求参数:{},{},{},{},{},{}",page,pageSize,name,gender,begin,end);
//        PageResult<Emp> pageResult=empService.page(page,pageSize,name,gender,begin,end);
//        return Result.success(pageResult);
//    }
    @GetMapping()
    public Result Page(EmpQueryParam empQueryParam) {
        log.info("查询请求参数:{}",empQueryParam);
        PageResult<Emp> pageResult=empService.page(empQueryParam);
        return Result.success(pageResult);
    }
    @PostMapping
    public Result save(@RequestBody Emp emp){
        log.info("新增员工:{}",emp);
        empService.save(emp);
        return Result.success();
    }
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids){
        log.info("删除员工:{}",ids);
        empService.deleteById(ids);
        return Result.success();
    }
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("查询员工信息:{}",id);
        Emp emp=empService.getInfo(id);
        return Result.success(emp);
    }
    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("修改员工信息:{}",emp);
        empService.update(emp);
        return Result.success();
    }
    @GetMapping("/list")
    public Result list(){
        log.info("查询所有员工信息");
        List<Emp> empList=empService.list();
        return Result.success(empList);
    }

}
