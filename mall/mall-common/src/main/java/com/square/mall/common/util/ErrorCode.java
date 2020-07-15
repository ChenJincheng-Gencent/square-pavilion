package com.square.mall.common.util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 错误码枚举类
 * 格式为应用标识-功能域-错误类型-错误码
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
    SUCCESS("0", "成功"),

    /**
     * 失败
     */
    FAILED("-1", "请求处理失败"),


    /*----------------------------------------------会员应用----------------------------------------------------------*/

    /**
     * 参数不合法
     */
    ME_APP_ME_PARA_ILLEGAL("01-01-P-00001","参数不合法");


    /**
     * 错误码
     */
    private String code;

    /**
     * 错误描述
     */
    private String msg;


}
