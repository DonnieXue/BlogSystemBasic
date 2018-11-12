package com.blog.common.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by BRL on 2018/11/10.
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    @Pointcut("@annotation(com.blog.common.annotion.Log)")
    public void pointCut() {

    }

    @Around("pointCut()")
    public Object logHandle(ProceedingJoinPoint point) {
        long startTime = System.currentTimeMillis();
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Method method = methodSignature.getMethod();
        String className = method.getDeclaringClass().getName();
        String methodName = method.getName();

        Object[] args = point.getArgs();
        StringBuilder params = new StringBuilder();
        for (Object param : args) {
            params.append(param.toString());
            params.append(";");
        }
        Object result = null;
        long costTime = 0;
        try {
            result = point.proceed();
        } catch (Throwable throwable) {
            String exception = throwable.getClass() + ":" + throwable.getMessage();
            costTime = System.currentTimeMillis() - startTime;
            log.error("请求时间：{}，请求耗时：{}，请求类名：{}，请求方法：{}，请求参数:{}，请求结果：{}", startTime, costTime, className, methodName, params.toString(), exception);
            return null;
        }
        costTime = System.currentTimeMillis() - startTime;
        log.info("请求时间：{}，请求耗时：{}，请求类名：{}，请求方法：{}，请求参数:{}，请求结果：{}", startTime, costTime, className, methodName, params.toString(), result);
        return result;
    }
}
