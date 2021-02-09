package com.square.mall.common.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 通用错误码枚举类
 *
 * @author Gencent
 * @date 2019/8/20
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ErrorCode {

    /**
     * 成功
     */
    SUCCESS(0, "成功"),

    /**
     * 失败
     */
    FAILURE(-1, "请求处理失败"),


    /**
     * 系统错误
     */
    SYSTEM_ERROR(999999,"系统错误");


    /**
     * 错误码
     */
    private final Integer code;

    /**
     * 错误描述
     */
    private final String msg;

}
