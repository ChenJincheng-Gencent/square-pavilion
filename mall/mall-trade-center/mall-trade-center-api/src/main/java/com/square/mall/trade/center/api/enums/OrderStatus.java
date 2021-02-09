package com.square.mall.trade.center.api.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 订单状态
 *
 * @author Gencent
 * @date 2020/10/30
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum OrderStatus {

    /**
     * 待支付
     */
    UNPAID(0, "待支付"),

    /**
     * 已支付
     */
    PAID(1, "已支付"),

    /**
     * 已失效
     */
    INVALID(2, "已失效"),

    /**
     * 已完成
     */
    COMPLETED(3, "已完成"),

    /**
     * 已取消
     */
    CANCELED(4, "已取消"),

    /**
     * 已删除
     */
    DELETED(5, "已删除");

    /**
     * 枚举值
     */
    private Integer value;

    /**
     * 描述
     */
    private String desc;

}
