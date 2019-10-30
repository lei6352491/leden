package com.zhouyi.business.core.exception;

import com.zhouyi.business.core.common.ReturnCode;
import lombok.Data;

/**
 * @author 李秸康
 * @ClassNmae: AuthenticationException
 * @Description: TODO
 * @date 2019/7/8 14:31
 * @Version 1.0
 **/
@Data
public class AuthenticationException extends RuntimeException {
    private ReturnCode returnCode;

    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(ReturnCode returnCode) {
        this.returnCode = returnCode;
    }

    public AuthenticationException() {
    }
}
