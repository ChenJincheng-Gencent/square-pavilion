package com.square.mall.trade.center.service.eo;

import com.square.mall.common.eo.BaseEo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PayLogEo extends BaseEo {


    private static final long serialVersionUID = -9038719858284693654L;

    private Long orderId;

    private BigDecimal payment;

    private Integer payType;

    private Integer status;

    private LocalDateTime payTime;

    private String transactionId;
    
}
