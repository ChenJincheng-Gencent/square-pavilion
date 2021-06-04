package com.square.mall.trade.center.biz.eo;

import com.square.mall.common.eo.BaseEo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 购物车
 *
 * @author Gencent
 * @date 2020/10/26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartEo extends BaseEo {

    private static final long serialVersionUID = -7285439809024809162L;

    /**
     * 会员ID
     */
    private Long memberId;

    /**
     * 商品ID
     */
    private Long itemId;

    /**
     * 商品数量
     */
    private Integer itemNum;

    /**
     * 勾选状态
     */
    private Integer status;

}
