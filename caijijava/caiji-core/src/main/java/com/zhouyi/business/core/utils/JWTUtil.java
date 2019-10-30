package com.zhouyi.business.core.utils;


import com.alibaba.fastjson.JSON;
import com.zhouyi.business.core.model.SysUser;
import io.jsonwebtoken.*;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.time.*;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 李秸康
 * @ClassNmae: JWTUtil
 * @Description: TODO
 * @date 2019/6/21 16:49
 * @Version 1.0
 **/
@Service("jwtUtil")
public class JWTUtil {

    private final Logger logger= LoggerFactory.getLogger(this.getClass());


    @Value("${com.jwt.secret}")
    private String secret; //定义加密解密密钥

    @Value("${com.jwt.issuer}")
    private String issuer; //定义签发者



    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    /**
      * @author 李秸康
      * @Description 生成JWT方法
      * @date 2019/6/22
      * @return
     **/
    public Object[] createJwtToken(SysUser sysUser){

        Object[] tokenAndExpTime=new Object[2];
        //将需要保存在载荷里的用户信息存到集合
        Map<String,Object> claims=new HashMap<>();
        claims.put("useraccount",sysUser.getUserAccount());
        claims.put("usertype",sysUser.getUserType());
        claims.put("userCode",sysUser.getUserCode());


        //定义签名算法为HS256
        final SignatureAlgorithm signatureAlgorithm=SignatureAlgorithm.HS256;

        final long nowMillis=System.currentTimeMillis();
        //设置过期时间
        final long ttlMillis=60*1000*5*60;
        final long expMillis=nowMillis+Long.parseLong("4092710536"); //有效期为当前毫秒数+有效期
        System.out.println(expMillis);
        //转换为Date类型
        final Date now=new Date(nowMillis);
        final Date exp=new Date(expMillis);
        tokenAndExpTime[0]=now;

        //创建签名的密钥
        final byte[] apiKeySecretBytes= DatatypeConverter.parseBase64Binary(Base64.getEncoder().encodeToString(secret.getBytes()));
        final Key signingKey=new SecretKeySpec(apiKeySecretBytes,signatureAlgorithm.getJcaName());

        //定义JWT头部信息
        final Map<String,Object> headerMap=new HashMap<>();
        headerMap.put("alg","HS256");
        headerMap.put("typ","JWT");

        final JwtBuilder builder= Jwts.builder()
                .setHeaderParams(headerMap)
                .setIssuedAt(now)
                .setExpiration(exp)
                .setIssuer(issuer)
                .setClaims(claims)
                .signWith(signatureAlgorithm,signingKey); //将载荷信息通过密钥进行HS256进行签名作为尾部

        logger.info("JWT["+builder.compact()+"]");
        tokenAndExpTime[1]=builder.compact();
        return tokenAndExpTime;
    }


    /**
     * 验证token信息是否正确
     * @param token
     * @return
     * @throws Exception
     */
    public Map<String,Object> validateToken(String token) throws
            ExpiredJwtException,UnsupportedJwtException,
            MalformedJwtException,SignatureException,
            IllegalArgumentException{

        //定义集合存储用户信息集合
        Map<String,Object> userInfos=new HashMap<>();
        //获取其中的载荷信息
        Claims claims= Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(Base64.getEncoder().encodeToString(secret.getBytes()))).parseClaimsJws(token).getBody();
            //预留异地登陆情况(DB中最后一次登陆时间大于生成时间及异地登陆情况)
//        Date generaterTime=claims.getIssuedAt();
        userInfos.put("userCode",claims.get("userCode"));
        userInfos.put("useraccount",claims.get("useraccount"));
        userInfos.put("usertype",claims.get("usertype"));
        return userInfos;
    }


    /**
     * 置换token
     * @param response
     * @param request
     */
    public void replaceToken(HttpServletResponse response, HttpServletRequest request){
        //获取用户信息
        Map<String,Object> userInfo=(Map<String,Object>)request.getAttribute("userInfo");
        SysUser user=JSON.parseObject(JSON.toJSONString(userInfo),SysUser.class);
        //构建新的jwt

//        String newToken=createJwtToken(user);
//        TokenUtil.delCookie(request,response,"token");
//        TokenUtil.setCookie("token",newToken,response);
    }

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());

        long a=Long.parseLong("1568113792484");
        long b=System.currentTimeMillis();
        long c=a-b;
        System.out.println(c/1000/60/60);
    }
}
