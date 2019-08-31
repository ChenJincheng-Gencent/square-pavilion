package com.square.mall.common.exception;

/**
 * 业务异常
 *
 * @author Gencent
 * @date 2019/8/31
 */
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

}
