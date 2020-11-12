package com.square.mall.trade.application.service;

import com.square.mall.common.dto.RspDto;
import com.square.mall.trade.center.api.dto.ShoppingCartDto;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 购物车Service
 *
 * @author Gencent
 * @date 2020/11/11
 */
public interface ShoppingCartService {

    /**
     * 加入购物车
     *
     * @param shoppingCartDto 购物车
     * @return 响应
     */
    RspDto addShoppingCart(@RequestBody ShoppingCartDto shoppingCartDto);

}
