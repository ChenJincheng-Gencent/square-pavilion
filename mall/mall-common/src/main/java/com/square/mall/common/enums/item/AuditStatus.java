package com.square.mall.common.enums.item;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 审核状态
 *
 * @author Gencent
 * @date 2020/10/30
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum AuditStatus {

    /**
     * 草稿
     */
    DRAFT(0, "草稿"),

    /**
     * 待审核
     */
    UNAUDITED(1, "待审核"),

    /**
     * 审核通过
     */
    PASS(2, "审核通过"),

    /**
     * 审核不通过
     */
    NO_PASS(3, "审核不通过");

    /**
     * 枚举值
     */
    private Integer value;

    /**
     * 描述
     */
    private String desc;

}
