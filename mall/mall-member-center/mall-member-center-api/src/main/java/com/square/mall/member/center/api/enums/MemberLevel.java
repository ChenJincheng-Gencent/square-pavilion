package com.square.mall.member.center.api.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 会员等级枚举类
 *
 * @author Gencent
 * @date 2019/8/26
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum MemberLevel {

    /**
     * 青铜
     */
    BRONZE(1, "倔强青铜"),

    /**
     * 白银
     */
    SILVER(2, "秩序白银"),

    /**
     * 黄金
     */
    GOLD(3, "荣耀黄金"),

    /**
     * 铂金
     */
    PLATINUM(4, "尊贵铂金"),

    /**
     * 钻石
     */
    DIAMOND(5, "永恒钻石"),

    /**
     * 星耀
     */
    STAR(6, "至尊星耀"),

    /**
     * 王者
     */
    KING(7, "最强王者");

    /**
     * 枚举值
     */
    private Integer value;

    /**
     * 描述
     */
    private String desc;

}
