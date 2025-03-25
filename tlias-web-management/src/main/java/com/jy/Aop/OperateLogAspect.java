package com.jy.Aop;

import com.jy.mapper.OperateLogMapper;
import com.jy.pojo.OperateLog;
import com.jy.utils.CurrentHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

@Aspect
@Component
public class OperateLogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(com.jy.anno.Log)")
    public Object logOperate(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取操作人ID，这里假设通过CurrentHolder获取，实际开发中需要根据实际需求获取
        int operateEmpId = getOperateEmpId();

        // 获取操作时间
        LocalDateTime operateTime = LocalDateTime.now();

        // 获取执行方法的全类名和方法名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String className = method.getDeclaringClass().getName();
        String methodName = method.getName();

        // 获取方法运行时参数
        String methodParams = getMethodParams(joinPoint);

        // 记录开始时间
        long startTime = System.currentTimeMillis();

        // 执行目标方法
        Object result = joinPoint.proceed();

        // 记录结束时间
        long endTime = System.currentTimeMillis();

        // 获取返回值
        String returnValue = result == null ? "" : result.toString();

        // 计算方法执行时长
         Long costTime = endTime - startTime;

        // 创建OperateLog对象并保存日志
        OperateLog operateLog = new OperateLog();
        operateLog.setOperateEmpId(operateEmpId);
        operateLog.setOperateTime(operateTime);
        operateLog.setClassName(className);
        operateLog.setMethodName(methodName);
        operateLog.setMethodParams(methodParams);
        operateLog.setReturnValue(returnValue);
        operateLog.setCostTime(costTime);

        operateLogMapper.insert(operateLog);

        return result;
    }

    private int getOperateEmpId() {
        return CurrentHolder.getCurrentId(); // 示例返回值
    }

    private String getMethodParams(ProceedingJoinPoint joinPoint) {
        // 获取方法参数
        Object[] args = joinPoint.getArgs();
        StringBuilder params = new StringBuilder();
        for (Object arg : args) {
            params.append(arg).append(",");
        }
        if (!params.isEmpty()) {
            params.deleteCharAt(params.length() - 1); // 去掉最后一个逗号
        }
        return params.toString();
    }
}
