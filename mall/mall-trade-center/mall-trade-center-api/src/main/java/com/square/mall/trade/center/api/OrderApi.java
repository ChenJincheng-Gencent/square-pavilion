package com.square.mall.trade.center.api;

import com.square.mall.common.dto.RspDto;
import com.square.mall.trade.center.api.dto.OrderDto;
import com.square.mall.trade.center.api.dto.PaymentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.annotation.Order;

/**
 * 订单API
 *
 * @author Gencent
 * @date 2020/12/31
 */
@FeignClient(name="mall-trade-center")
public interface OrderApi {

    /**
     * 创建订单
     *
     * @param orderDto 订单信息
     * @return 响应
     */
    RspDto createOrder(OrderDto orderDto);

    /**
     * 取消订单
     *
     * @param orderDto 订单信息
     * @return 响应
     */
    RspDto cancelOrder(OrderDto orderDto);

    /**
     * 支付回调通知
     *
     * @param paymentDto 支付信息
     * @return 响应
     */
    RspDto afterPayNotify(PaymentDto paymentDto);
}
