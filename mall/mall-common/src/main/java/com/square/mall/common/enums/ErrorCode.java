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
     * 参数为空
     */
    PARA_IS_NULL(900000, "参数为空"),

    /**
     * 参数非法
     */
    PARA_ILLEGAL(900001, "参数非法"),

    /**
     * 重复请求
     */
    REPEAT_QUEST(900002, "系统正在为您处理上一次提交的内容，请稍候再提交"),

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
