package com.zhouyi.business.core.exception;

import com.zhouyi.business.core.model.Response;

/**
 * 自定义运行时异常
 * */
public class CollectionException extends RuntimeException{

    private Response response;

    public CollectionException(Response response){
        this.response = response;
    }

    public Response getResponse(){
        return response;
    }

}
