package com.zhouyi.business.aop;

import com.zhouyi.business.controller.UserController;
import com.zhouyi.business.core.model.SysLogOperation;
import com.zhouyi.business.core.service.SysLogOperationService;
import com.zhouyi.business.core.utils.JWTUtil;
import com.zhouyi.business.core.utils.TokenUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.UUID;

/**
 * @author 李秸康
 * @ClassNmae: SysOperationLogAspect 日志操作切面
 * @Description: TODO
 * @date 2019/8/23 8:47
 * @Version 1.0
 **/
@Component
@Aspect
public class SysOperationLogAspect {

    @Autowired
    private SysLogOperationService sysLogOperationService;
    @Autowired
    private JWTUtil jwtUtil;
    private static final ThreadLocal<Map<Object,Object>> objectThreadLocal=new InheritableThreadLocal<>();


    @Pointcut("execution(* com.zhouyi.business.controller.*.add*(..))||" +
            "execution(* com.zhouyi.business.controller.*.save*(..))||" +
            "execution(* com.zhouyi.business.controller.*.update*())||" +
            "execution(* com.zhouyi.business.controller.*.modify*())||" +
            "execution(* com.zhouyi.business.controller.*.update*())||" +
            "execution(* com.zhouyi.business.controller.*.del*())||" +
            "execution(* com.zhouyi.business.controller.*.delete*())")
    public void recordPontCut(){}






//    @Before("recordPontCut()")
    public void doBefore(JoinPoint joinPoint){
        System.out.println("====执行前置增强=====");
        //获取request对象
        RequestAttributes requestAttributes= RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes=(ServletRequestAttributes)requestAttributes;
        HttpServletRequest request=servletRequestAttributes.getRequest();

        //从Request中提起用户信息
        Map<String, Object> userInfo = jwtUtil.validateToken(request.getHeader("token"));
        //创建操作日志对象记录信息
        String pkId= UUID.randomUUID().toString().substring(0,32);
        String ip=request.getRemoteAddr();
        SysLogOperation sysLogOperation=new SysLogOperation(pkId,(String)userInfo.get("userCode"),ip);
        String operationMenuCode=null;
        //匹配用户的操作模块
      String operationClass=joinPoint.getTarget().getClass().getSimpleName();
       switch (operationClass){
//           case ""
       }



    }


}
