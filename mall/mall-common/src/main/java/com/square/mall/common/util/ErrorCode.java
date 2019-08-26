package com.square.mall.common.util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 错误码枚举类
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
    SUCCESS("0", "success"),

    /**
     * 失败
     */
    FAILED("-1", "failed");


    /**
     * 错误码
     */
    private String code;

    /**
     * 错误描述
     */
    private String message;


}
