package com.zhouyi.business.filter;

import com.alibaba.fastjson.JSON;
import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.utils.JWTUtil;
import com.zhouyi.business.core.utils.ResponseUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @author 李秸康
 * @ClassNmae: SecurityFliter
 * @Description: 资源安全过滤器，过滤/api/*下的所有路径
 * @date 2019/6/22 12:31
 * @Version 1.0
 **/
/*@WebFilter(urlPatterns = {"/api/*"})*/
public class SecurityFliter implements Filter {

    @Autowired
    private JWTUtil jwtUtil;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //在过滤器中对没有登陆的请求进进行过滤
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        String token=request.getHeader("token");
        Response<Object> errorResponse=null;
        try {

            Map<String,Object> infos=jwtUtil.validateToken(token);
            //将解析出来的用户信息存入request
            request.setAttribute("userInfo",infos);
            System.out.println(request.getRequestURI());

//            jwtUtil.replaceToken((HttpServletResponse)servletResponse,request);
            filterChain.doFilter(servletRequest,servletResponse);

            return;
        } catch (ExpiredJwtException|SignatureException e) {
            errorResponse=ResponseUtil.returnError(ReturnCode.ERROR_1004);
        } catch (UnsupportedJwtException | MalformedJwtException e) {
            errorResponse=ResponseUtil.returnError(ReturnCode.ERROR_01);
        }catch (IllegalArgumentException e) {
            errorResponse=ResponseUtil.returnError(ReturnCode.ERROR_1003);
        }
        //返回token失败错误码信息
        PrintWriter out=servletResponse.getWriter();
        out.print(JSON.toJSONString(errorResponse));
        out.close();
    }

    @Override
    public void destroy() {

    }
}
