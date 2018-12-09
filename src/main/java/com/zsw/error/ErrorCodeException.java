package com.zsw.error;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zhangshiwei
 * @version 1.0
 * @date 2018-12-09
 */
public class ErrorCodeException extends RuntimeException implements IErrorCode  {
    @Setter
    @Getter
    private Integer code;

    @Setter
    @Getter
    private String message;

    public ErrorCodeException(IErrorCode iErrorCode) {
        this.code = iErrorCode.getCode();
        this.message = iErrorCode.getMessage();
    }

    @Override
    public String toString() {
        return "ErrorCodeException{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
