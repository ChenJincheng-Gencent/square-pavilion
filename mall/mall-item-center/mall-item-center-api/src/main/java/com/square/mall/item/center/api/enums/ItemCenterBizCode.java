package com.square.mall.item.center.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


/**
 * 商品中心业务错误码
 *
 * @author Gencent
 * @date 2021/2/9
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ItemCenterBizCode {

    /**
     * 品牌已存在
     */
    BRAND_IS_EXISTED(11000000,"该品牌已存在"),

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
