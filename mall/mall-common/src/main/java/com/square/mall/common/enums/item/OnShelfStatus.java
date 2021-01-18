package com.square.mall.common.enums.item;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 上下架状态
 *
 * @author Gencent
 * @date 2020/10/30
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum OnShelfStatus {

    /**
     * 下架
     */
    OFF_SHELF(0, "下架"),

    /**
     * 上架
     */
    ON_SHELF(1, "上架"),

    /**
     * 隐藏
     */
    HIDE(2, "隐藏");

    /**
     * 枚举值
     */
    private Integer value;

    /**
     * 描述
     */
    private String desc;

}
