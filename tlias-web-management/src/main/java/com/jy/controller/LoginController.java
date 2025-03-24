package com.jy.controller;

import com.jy.pojo.Emp;
import com.jy.pojo.LogInfo;
import com.jy.pojo.Result;
import com.jy.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/login")
@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;
    @PostMapping
    public Result login(@RequestBody Emp emp) {
       log.info("登录请求:{}",emp);
        LogInfo logInfo=loginService.login(emp);
        if (logInfo!=null){
            return Result.success(logInfo);
        }
        return Result.error("用户名或密码错误");
    }
}
