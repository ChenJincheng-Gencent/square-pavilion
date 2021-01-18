package com.square.mall.common.exception;

import com.square.mall.common.enums.ErrorCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 业务异常
 *
 * @author Gencent
 * @date 2020/1/4
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class BizException extends RuntimeException {

    private static final long serialVersionUID = 6147256750452161408L;

    private String code;

    private String msg;


    public BizException(Throwable cause) {
        super(cause);
    }

    public BizException(String message) {
        super(message);
        this.msg = message;
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
        this.msg = message;
    }

    public BizException(ErrorCode errorCode) {
        super(errorCode.getMsg());
        this.msg = errorCode.getMsg();
        this.code = errorCode.getCode();
    }

    public BizException(String code, String msg) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

}

