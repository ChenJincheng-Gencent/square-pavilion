package com.square.mall.trade.center.api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentDto {

    private Long orderId;

    private BigDecimal amount;

    private Integer payType;

    private Integer status;

    private LocalDateTime payTime;

    private String transactionId;

}
