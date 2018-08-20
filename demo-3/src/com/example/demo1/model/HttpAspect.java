package com.example.demo1.model;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.example.demo1.util.ExceptionHandle;

/**
 * @author Xing
 *
 */


@Aspect
@Component
public class HttpAspect {
    private static final Logger logger = LoggerFactory.getLogger(HttpAspect.class);
    
    @Autowired
    private ExceptionHandle handle;
    
//    @Pointcut("execution(public * com.example.demo1.controller.*.*(..))")
//    public void logDemo1() {    	
//    }
    @Pointcut("execution(public * com.example.demo1.service.*.*(..))")
    public void logDemo2() {    	
    }
//    @Pointcut("logDemo1() || logDemo2()")
//    public void logDemo() {    	
//    }
    
    @Before("logDemo2()")
    public void doBefore(JoinPoint joinPoint) {
    	ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
        	HttpServletRequest request = attributes.getRequest();
        	//url
            logger.info("url={}",request.getRequestURL());
            //method
            logger.info("method={}",request.getMethod());
            //ip
            logger.info("id={}",request.getRemoteAddr());
            //class_method
            logger.info("class_method={}",joinPoint.getSignature().getDeclaringTypeName() + "," + joinPoint.getSignature().getName());
            //args[]
            logger.info("args={}",joinPoint.getArgs()); 
        }       
    }
    
    @Around("logDemo2()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        BaseResult result = null;
        try {

        } catch (Exception e) {
            return handle.executeException(e);
        }
        if(result == null){
            return proceedingJoinPoint.proceed();
        }else {
            return result;
        }
    }
    
    @AfterReturning(pointcut="logDemo2()",returning="object")
    public void doAfter(JoinPoint joinPoint, Object object) {
    	ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
        	HttpServletRequest request = attributes.getRequest();
            //class_method
            logger.info("调用方法={}",joinPoint.getSignature().getDeclaringTypeName() + "," + joinPoint.getSignature().getName());
            //args[]
            logger.info("请求参数={}",joinPoint.getArgs()); 
        } 
    	if (object != null) {
    		logger.info("返回结果response={}",JSON.toJSONString(object));
    	}
    }
}