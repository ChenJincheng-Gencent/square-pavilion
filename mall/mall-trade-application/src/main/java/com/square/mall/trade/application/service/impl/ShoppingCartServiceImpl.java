package com.square.mall.trade.application.service.impl;

import com.square.mall.common.dto.RspDto;
import com.square.mall.trade.application.service.ShoppingCartService;
import com.square.mall.trade.center.api.ShoppingCartApi;
import com.square.mall.trade.center.api.dto.ShoppingCartDto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 购物车Service实现类
 *
 * @author Gencent
 * @date 2020/11/11
 */
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Resource
    private ShoppingCartApi shoppingCartApi;

    @Override
    public RspDto addShoppingCart(ShoppingCartDto shoppingCartDto) {
        return shoppingCartApi.addShoppingCart(shoppingCartDto);
    }
}
