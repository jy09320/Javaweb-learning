package com.jy.controller;
import com.itheima.pojo.Dept;
import com.jy.pojo.Result;
import com.jy.service.DeptService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;
//    @RequestMapping(value = "/depts",method = RequestMethod.GET)
    @GetMapping
    public Result list(){
        log.info("查询所有部门信息");
        List<Dept> deptlist=deptService.findAll();
        return Result.success(deptlist);
    }
    //删除部门
//    @DeleteMapping("/depts")
//    public Result delete(HttpServletRequest request) {
//        String idStr = request.getParameter("id");
//        int id=Integer.parseInt(idStr);
//        System.out.println("删除部门"+id);
//        return Result.success();
//    }
    //方式二：RequestParam
    //注意事项：一旦声明了RequestParam注解，参数请求时必须传递，否则会报错因为require默认值是true
//    @DeleteMapping("/depts")
//    public Result delete(@RequestParam(value = "id", required = false) Integer deptId) {
//        System.out.println("删除部门"+deptId);
//        return Result.success();
//    }
    //方式三：前端传入的请求参数名和后端接收的参数名一致，直接使用即可
    @DeleteMapping
    public Result delete(Integer id) {
        log.info("根据id删除部门:{}",id);
        deptService.deleteById(id);
        return Result.success();
    }
    @PostMapping
    public Result save(@RequestBody Dept dept) {
        log.info("新增或修改部门:{}",dept);
        deptService.save(dept);
        return Result.success();
    }
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("根据id查询部门信息:{}",id);
        Dept dept=deptService.findById(id);
        return Result.success(dept);
    }
    @PutMapping
    public Result update(@RequestBody Dept dept) {
        log.info("修改部门信息:{}",dept);
        deptService.update(dept);
        return Result.success();
    }
}
