package com.square.mall.trade.center.service.eo;

import com.square.mall.common.eo.BaseEo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 订单商品
 *
 * @author Gencent
 * @date 2020/1/5
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemEo extends BaseEo {

    private static final long serialVersionUID = 4148307072826386254L;

    /**
     * 商品ID
     */
    private Long itemId;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 商品SKU
     */
    private String sku;

    /**
     * 商品价格
     */
    private BigDecimal price;

    /**
     * 商品数量
     */
    private Integer num;

    /**
     * 总价格
     */
    private BigDecimal totalFee;

    /**
     * 标题
     */
    private String title;

    /**
     * 图片路径
     */
    private String imagePath;
}
