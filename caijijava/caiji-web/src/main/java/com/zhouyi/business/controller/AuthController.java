package com.zhouyi.business.controller;

import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.exception.BusinessException;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.model.SysUser;
import com.zhouyi.business.core.service.SysUserService;
import com.zhouyi.business.core.utils.JWTUtil;
import com.zhouyi.business.core.utils.ResponseUtil;
import com.zhouyi.business.core.utils.TokenUtil;
import com.zhouyi.business.core.vo.SysUserVo;
import com.zhouyi.business.dto.SysUserDto;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author 李秸康
 * @ClassNmae: AuthController
 * @Description: 认证控制器
 * @date 2019/6/22 13:43
 * @Version 1.0
 **/
@RestController
@RequestMapping(value="/auth")
public class AuthController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private JWTUtil jwtUtil;


    @Autowired
    private static final Logger logger= LoggerFactory.getLogger(AuthController.class);
    /**
     * 用户登陆控制器
     * @param sysUserDto
     * @return
     */
    @RequestMapping(value="/login",method = RequestMethod.POST)
    public Response<Object> login(@RequestBody SysUserDto sysUserDto, HttpServletResponse reponse){
        try {
           SysUser sysUser=sysUserService.login
                   (sysUserDto.getUserAccount(),sysUserDto.getUserPassword(),reponse,sysUserDto.getEquipmentMac());
            return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS,sysUser);
        } catch (BusinessException e) {
            return ResponseUtil.returnError(e.getReturnCode());
        }
    }







    /**
     * 置换token接口
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/reToken")
    public Response<Object> reToken(HttpServletRequest request,HttpServletResponse response){
        String token=request.getHeader("token");
        try {
            Map<String,Object> infos=jwtUtil.validateToken(token);
            String userAccount=(String)infos.get("useraccount");
            SysUser sysUser=sysUserService.searchSysUser(userAccount);
            //生成信息的token并且写回客户端
            Object object[]=jwtUtil.createJwtToken(sysUser);
            String newToken=object[1].toString();
//            Cookie cookie=new Cookie("token",newToken);
//            response.addCookie(cookie);
            TokenUtil.setCookie("token",newToken,response);
            return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS,object[0]);
        } catch (ExpiredJwtException|UnsupportedJwtException|MalformedJwtException
                |SignatureException|IllegalArgumentException e) {
            //解析失败，表示token无效或者错误
            return ResponseUtil.returnError(ReturnCode.ERROR_01);
        }
    }

}
