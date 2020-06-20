package org.scholat.common.utils;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.scholat.common.annotation.LogFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author yrk
 * @date 2020/6/17 - 16:45
 */
@Aspect
@Component
public class LogAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogAspect.class);

    /**
     * 切入点表达式：@annotation(自定义注解)自定义注解标注在方法上的方法执行aop方法
     */
    @Pointcut("@annotation(com.Test.aop.LogFilter)")
    public void logPointCut() {
    }

    @Around("logPointCut()")
    public Object aroungLog(ProceedingJoinPoint point) throws Throwable {
        Object result = null;

        try {
            //执行方法
            result = point.proceed();
            //保存请求日志
            saveRequestLog(point);
        } catch (Exception e) {
            //保存异常日志
            saveExcetionLog(point, e.getMessage());
        }
        return result;
    }

    /**
     * 保存请求日志
     * @param point
     */
    private void  saveRequestLog (ProceedingJoinPoint point) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        LOGGER.info("请求路径" + request.getRequestURL());
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        LOGGER.info("请求方法" + method.getName());
        //获取方法上的LogFilter注解
        LogFilter logFilter = method.getAnnotation(LogFilter.class);
        String value = logFilter.value();
        LOGGER.info("模块描述：" + value);
        Object[] args = point.getArgs();
        LOGGER.info("请求参数：" + JSONObject.toJSONString(args));
    }

    /**
     * 保存异常日志
     * @param point
     * @param exeMsg
     */
    private void saveExcetionLog (ProceedingJoinPoint point, String exeMsg){
        LOGGER.info("捕获异常：" + exeMsg);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        LOGGER.info("请求路径：" + request.getRequestURL());
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        LOGGER.info("请求方法" + method.getName());
        //获取方法上的LogFilter注解
        LogFilter logFilter = method.getAnnotation(LogFilter.class);
        String value = logFilter.value();
        LOGGER.info("模块描述：" + value);
        Object[] args = point.getArgs();
        LOGGER.info("请求参数：" + JSONObject.toJSONString(args));
    }
}
