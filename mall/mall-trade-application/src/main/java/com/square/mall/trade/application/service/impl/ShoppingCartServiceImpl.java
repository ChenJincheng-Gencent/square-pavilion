package com.square.mall.trade.application.service.impl;

import com.square.mall.common.dto.RspDto;
import com.square.mall.trade.application.api.ShoppingCartApi;
import com.square.mall.trade.application.service.ShoppingCartService;

import com.square.mall.trade.center.api.dto.ShoppingCartDto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    @Override
    public RspDto updateShoppingCart(ShoppingCartDto shoppingCartDto) {
        return shoppingCartApi.updateShoppingCart(shoppingCartDto);
    }

    @Override
    public RspDto<List<ShoppingCartDto>> getShoppingCartList(Long memberId) {
        return shoppingCartApi.getShoppingCartList(memberId);
    }

    @Override
    public RspDto deleteShoppingCart(Long memberId, Long itemId) {
        return shoppingCartApi.deleteShoppingCart(memberId, itemId);
    }

    @Override
    public RspDto batchDeleteShoppingCartList(Long memberId, Long[] itemIds) {
        return shoppingCartApi.batchDeleteShoppingCartList(memberId, itemIds);
    }

    @Override
    public RspDto deleteAllShoppingCartList(Long memberId) {
        return shoppingCartApi.deleteAllShoppingCartList(memberId);
    }


}
