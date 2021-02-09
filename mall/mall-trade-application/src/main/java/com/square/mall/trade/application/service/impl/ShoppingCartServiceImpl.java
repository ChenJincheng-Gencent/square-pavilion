package com.square.mall.trade.application.service.impl;

import com.square.mall.common.dto.CommonRes;
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
    public CommonRes<Void> addShoppingCart(ShoppingCartDto shoppingCartDto) {
        return shoppingCartApi.addShoppingCart(shoppingCartDto);
    }

    @Override
    public CommonRes<Void> updateShoppingCart(ShoppingCartDto shoppingCartDto) {
        return shoppingCartApi.updateShoppingCart(shoppingCartDto);
    }

    @Override
    public CommonRes<List<ShoppingCartDto>> getShoppingCartList(Long memberId) {
        return shoppingCartApi.getShoppingCartList(memberId);
    }

    @Override
    public CommonRes<Void> deleteShoppingCart(Long memberId, Long itemId) {
        return shoppingCartApi.deleteShoppingCart(memberId, itemId);
    }

    @Override
    public CommonRes<Void> batchDeleteShoppingCartList(Long memberId, Long[] itemIds) {
        return shoppingCartApi.batchDeleteShoppingCartList(memberId, itemIds);
    }

    @Override
    public CommonRes<Void> deleteAllShoppingCartList(Long memberId) {
        return shoppingCartApi.deleteAllShoppingCartList(memberId);
    }


}
