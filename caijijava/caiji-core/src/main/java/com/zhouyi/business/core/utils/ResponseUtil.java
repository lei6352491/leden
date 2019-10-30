package com.zhouyi.business.core.utils;

import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.model.Response;

/**
 * @author 李秸康
 * @ClassNmae: ResponseUtil
 * @Description: 返回结果工具类
 * @date 2019/6/21 9:26
 * @Version 1.0
 **/
public class ResponseUtil {


    /**
     * 根据flag获取响应信息
     * @param flag
     * @return
     */
    public static Response<Object> getResponseInfo(boolean flag){
        Response<Object> response=new Response<Object>();
        if(flag){
            response.setMsg(ReturnCode.SUCCESS.getMsg());
            response.setCode(ReturnCode.SUCCESS.getCode());
        }else{
            response.setMsg(ReturnCode.ERROR_01.getMsg());
            response.setCode(ReturnCode.ERROR_01.getCode());
        }
        return response;
    }

    /**
     * 返回固定错误
     * @param returnCode
     * @return
     */
    public static Response<Object> returnError(ReturnCode returnCode){
        Response<Object> response=new Response<Object>();
        response.setCode(returnCode.getCode());
        response.setMsg(returnCode.getMsg());
        return response;
    }


    /**
     * 返回带数据/编码/信息的返回
     * @param returnCode
     * @param data
     * @return
     */
    public static Response getResponseInfo(ReturnCode returnCode,Object data){
        Response<Object> objectResponse=new Response<Object>();
        objectResponse.setData(data);
        objectResponse.setCode(returnCode.getCode());
        objectResponse.setMsg(returnCode.getMsg());
        return objectResponse;
    }

    /**
     * 返回自定义错误信息
     * @param exceptionMsg
     * @return
     */
    public static Response ntrError(String exceptionMsg){
        return new Response(500,exceptionMsg);
    }
}
