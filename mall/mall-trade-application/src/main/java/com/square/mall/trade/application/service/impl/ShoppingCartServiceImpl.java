package com.square.mall.trade.application.service.impl;

import com.square.mall.common.dto.RspDto;
import com.square.mall.trade.application.service.ShoppingCartService;

import com.square.mall.trade.center.api.ShoppingCartApi;
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
    public RspDto<Void> addShoppingCart(ShoppingCartDto shoppingCartDto) {
        return shoppingCartApi.addShoppingCart(shoppingCartDto);
    }

    @Override
    public RspDto<Void> updateShoppingCart(ShoppingCartDto shoppingCartDto) {
        return shoppingCartApi.updateShoppingCart(shoppingCartDto);
    }

    @Override
    public RspDto<List<ShoppingCartDto>> getShoppingCartList(Long memberId) {
        return shoppingCartApi.getShoppingCartList(memberId);
    }

    @Override
    public RspDto<Void> deleteShoppingCart(Long memberId, Long itemId) {
        return shoppingCartApi.deleteShoppingCart(memberId, itemId);
    }

    @Override
    public RspDto<Void> batchDeleteShoppingCartList(Long memberId, Long[] itemIds) {
        return shoppingCartApi.batchDeleteShoppingCartList(memberId, itemIds);
    }

    @Override
    public RspDto<Void> deleteAllShoppingCartList(Long memberId) {
        return shoppingCartApi.deleteAllShoppingCartList(memberId);
    }


}
