package com.jy.controller;

import com.jy.pojo.OperateLog;
import com.jy.pojo.PageResult;
import com.jy.pojo.Result;
import com.jy.service.OperateLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/log")
@RestController
public class OperateLogController {
    @Autowired
    private OperateLogService operateLogService;
    @GetMapping("/page")
    public Result page(Integer page,Integer pageSize){
        log.info("查询所有操作日志");
        PageResult<OperateLog> pageResult=operateLogService.page(page,pageSize);
        return Result.success(pageResult);
    }
}
