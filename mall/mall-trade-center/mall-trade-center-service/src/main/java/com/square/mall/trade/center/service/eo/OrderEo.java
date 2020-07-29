package com.square.mall.trade.center.service.eo;

import com.square.mall.common.eo.BaseEo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderEo extends BaseEo {

    private static final long serialVersionUID = -2865841474489866418L;

    private Long orderId;

    private BigDecimal payment;

    private Integer payType;

    private BigDecimal postFee;

    private Integer status;

    private LocalDateTime payTime;

    private LocalDateTime consignTime;

    private LocalDateTime endTime;

    private LocalDateTime closeTime;

    private String shippingName;

    private String shippingCode;

    private Long memberId;

    private String receiverMobile;

    private String receiver;

    private String receiverAddress;

    private Integer invoiceType;

    private Integer sourceType;

}
