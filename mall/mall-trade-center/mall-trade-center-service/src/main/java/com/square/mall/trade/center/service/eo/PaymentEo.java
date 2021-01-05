package com.square.mall.trade.center.service.eo;

import com.square.mall.common.eo.BaseEo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 支付
 *
 * @author Gencent
 * @date 2020/1/5
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class PaymentEo extends BaseEo {

    private static final long serialVersionUID = -9038719858284693654L;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 实付费用
     */
    private BigDecimal amount;

    /**
     * 支付类型
     */
    private Integer payType;

    /**
     * 支付状态
     */
    private Integer status;

    /**
     * 支付时间
     */
    private LocalDateTime payTime;

    /**
     * 交易流水号
     */
    private String transactionId;
    
}
