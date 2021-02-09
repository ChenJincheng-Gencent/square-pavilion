package com.square.mall.member.application.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


/**
 * 会员应用业务错误码
 *
 * @author Gencent
 * @date 2021/2/9
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum MemberApplicationBizCode {

    /**
     * 验证码错误
     */
    AUTH_CODE_ERROR(11000001, "验证码错误"),

    /**
     * 错误码段最大值
     */
    MAX(11999999,"最大值占位，在上方添加新枚举");

    /**
     * 错误码
     */
    Integer code;

    /**
     * 错误描述
     */
    String msg;

}
