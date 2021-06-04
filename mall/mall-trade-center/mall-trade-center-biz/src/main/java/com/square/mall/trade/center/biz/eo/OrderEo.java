package com.square.mall.trade.center.biz.eo;

import com.square.mall.common.eo.BaseEo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单
 *
 * @author Gencent
 * @date 2021/1/5
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class OrderEo extends BaseEo {

    private static final long serialVersionUID = -2865841474489866418L;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 费用
     */
    private BigDecimal payment;

    /**
     * 支付类型
     */
    private Integer payType;

    /**
     * 运费
     */
    private BigDecimal postFee;

    /**
     * 订单状态
     */
    private Integer status;

    /**
     * 支付时间
     */
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
