package com.zhouyi.business.core.exception;

import com.zhouyi.business.core.model.Response;


/**
 * 异常抛出类
 * */
public class ExceptionCast {

    public static void cast(Response response){
        throw new CollectionException(response);
    }





}
