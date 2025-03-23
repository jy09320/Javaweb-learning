package com.jy.pojo;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    //处理异常
    // 处理自定义异常
    @ExceptionHandler(ClassHasStudentsException.class)
    public Result handleClassHasStudentsException(ClassHasStudentsException e) {
        e.printStackTrace(); // 打印堆栈中的异常信息
        // 捕获到异常之后，响应一个标准的Result
        return Result.error(e.getMessage());
    }
    @ExceptionHandler(DeptHasEmployeesException.class)
    public Result handleDeptHasEmployeesException(DeptHasEmployeesException e) {
        e.printStackTrace(); // 打印堆栈中的异常信息
        // 捕获到异常之后，响应一个标准的Result
        return Result.error(e.getMessage());
    }

    // 处理其他异常
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        e.printStackTrace(); // 打印堆栈中的异常信息
        // 捕获到异常之后，响应一个标准的Result
        return Result.error("系统错误，请联系管理员");
    }
    
}