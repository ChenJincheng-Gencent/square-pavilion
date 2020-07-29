package com.square.mall.trade.center.service.eo;

import com.square.mall.common.eo.BaseEo;

import java.math.BigDecimal;

public class OrderItemEo extends BaseEo {

    private static final long serialVersionUID = 4148307072826386254L;

    private Long itemId;

    private Long orderId;

    private String sku;

    private BigDecimal price;

    private Integer num;

    private BigDecimal totalFee;

    private String title;

    private String imagePath;
}
