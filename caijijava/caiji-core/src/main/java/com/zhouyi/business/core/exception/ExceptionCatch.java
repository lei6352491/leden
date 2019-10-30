package com.zhouyi.business.core.exception;


import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.utils.ResponseUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 全局异常处理
 * */
@ControllerAdvice
public class ExceptionCatch {

    @ExceptionHandler(value = {CollectionException.class})
    @ResponseBody
    public Response customException(CollectionException e){
        Response response = e.getResponse();
        return response;
    }


    @ExceptionHandler(value = {BusinessException.class})
    @ResponseBody
    public Response<Object> businessException(BusinessException e){
       return ResponseUtil.returnError(e.getReturnCode());
    }


    @ResponseBody
    @ExceptionHandler(value = {AuthenticationException.class})
    public Response<Object> authenticationException(AuthenticationException e){
       return ResponseUtil.returnError(e.getReturnCode());

    }


    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Response<Object> exception(Exception e){
        e.printStackTrace();
       return ResponseUtil.ntrError(e.getMessage());
    }
}
