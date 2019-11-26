package com.zhouyi.business.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.gson.JsonArray;
import com.zhouyi.business.core.utils.JWTUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 李秸康
 * @ClassNmae: LogAspect
 * @Description: TODO 日志记录切面
 * @date 2019/9/2 8:58
 * @Version 1.0
 **/
@Component
@Aspect
public class LogAspect {

    private static final Logger log= LoggerFactory.getLogger(LogAspect.class);
    @Autowired
    private JWTUtil jwtUtil;

    @Pointcut("execution(* com.zhouyi.business.controller.*.*(..))||execution(* com.zhouyi.business.component.*.*(..))")
    public void ponitCut(){}

    @Before("ponitCut()")
    public void before(JoinPoint joinpoint){
        Object[] args = joinpoint.getArgs();
        Object[] inputArgs=new Object[args.length];

        for (int i=0;i<args.length;i++) {
            if(args[i] instanceof HttpServletRequest||args[i] instanceof HttpServletResponse)
                continue;
            else
                inputArgs[i]=args[i];
        }
        log.info(joinpoint.getTarget().getClass().getName()+".params:"+JSONArray.toJSONString(inputArgs));
    }


    /**
     * 后置处理
     */
    @AfterReturning(value = "ponitCut()",returning = "object")
    public void after(JoinPoint joinPoint,Object object){
//       log.info(joinPoint.getTarget().getClass().getName()+".return:"+ JSON.toJSONString(object));
    }
}
