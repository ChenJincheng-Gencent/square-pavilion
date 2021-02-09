package com.square.mall.trade.center.api.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 支付状态
 *
 * @author Gencent
 * @date 2020/10/30
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum PayStatus {

    /**
     * 未支付
     */
    UNPAID(0, "未支付"),

    /**
     * 支付成功
     */
    SUCCESS(1, "支付成功"),

    /**
     * 支付失败
     */
    FAILED(2, "支付失败");

    /**
     * 枚举值
     */
    private Integer value;

    /**
     * 描述
     */
    private String desc;

}
