package com.square.mall.trade.center.api;

import com.square.mall.common.dto.CommonRes;
import com.square.mall.trade.center.api.dto.PaymentDto;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 支付API
 *
 * @author Gencent
 * @date 2020/12/31
 */
@FeignClient(name="mall-trade-center")
public interface PayApi {

    /**
     * 支付回调通知
     *
     * @param paymentDto 支付信息
     * @return 响应
     */
    CommonRes afterPayNotify(PaymentDto paymentDto);

}
