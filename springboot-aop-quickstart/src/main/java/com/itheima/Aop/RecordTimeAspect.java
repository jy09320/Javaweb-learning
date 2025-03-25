package com.itheima.Aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Aspect
public class RecordTimeAspect {
    @Around("execution(* com.itheima.service.impl.DeptServiceImpl.*(..))")
    public Object recordTime(ProceedingJoinPoint proceedingJoinPoint)throws Throwable{
        Long begin=System.currentTimeMillis();
        Object result= proceedingJoinPoint.proceed();
        Long end=System.currentTimeMillis();
        log.info("耗时：{}ms",end-begin);
        return result;
    }
}
