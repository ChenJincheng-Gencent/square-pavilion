package com.square.mall.member.application.exception;

import com.square.mall.common.enums.ErrorCode;
import com.square.mall.common.exception.BizException;
import com.square.mall.member.application.enums.MemberApplicationBizCode;

/**
 * 会员应用业务异常类
 *
 * @author Gencent
 * @date 2021/2/9
 */
public class MemberApplicationBizException extends BizException {

    private static final long serialVersionUID = -3919399518869808736L;

    public MemberApplicationBizException(Throwable cause) {
        super(cause);
    }

    public MemberApplicationBizException(String message) {
        super(message);
    }

    public MemberApplicationBizException(String message, Throwable cause) {
        super(message, cause);
    }

    public MemberApplicationBizException(ErrorCode errorCode) {
        super(errorCode);
    }

    public MemberApplicationBizException(Integer code, String msg) {
        super(code, msg);
    }

    public MemberApplicationBizException(MemberApplicationBizCode memberApplicationBizCode){
        super(memberApplicationBizCode.getCode(), memberApplicationBizCode.getMsg());
    }

}
