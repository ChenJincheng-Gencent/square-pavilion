package com.square.mall.trade.center.api;

import com.square.mall.common.dto.RspDto;
import com.square.mall.trade.center.api.dto.OrderDto;
import org.springframework.cloud.openfeign.FeignClient;

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


}
