package com.square.mall.trade.application.service;

import com.square.mall.common.dto.RspDto;
import com.square.mall.trade.center.api.dto.ShoppingCartDto;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
    RspDto addShoppingCart(ShoppingCartDto shoppingCartDto);

    /**
     * 更新购物车
     *
     * @param shoppingCartDto 购物车
     * @return 响应
     */
    RspDto updateShoppingCart(ShoppingCartDto shoppingCartDto);

    /**
     * 根据会员ID查询购物车列表
     *
     * @param memberId 会员ID
     * @return 购物车列表
     */
    RspDto<List<ShoppingCartDto>> getShoppingCartList(Long memberId);

    /**
     * 根据会员ID和商品ID删除购物车
     *
     * @param memberId 会员ID
     * @param itemId 商品ID
     * @return 响应
     */
    RspDto deleteShoppingCart(Long memberId, Long itemId);

    /**
     * 根据会员ID和商品ID列表批量删除购物车
     *
     * @param memberId 会员ID
     * @param itemIds 商品ID列表
     * @return 响应
     */
    RspDto batchDeleteShoppingCartList(Long memberId, Long[] itemIds);

    /**
     * 根据会员ID和商品ID列表批量删除购物车
     *
     * @param memberId 会员ID
     * @return 响应
     */
    RspDto deleteAllShoppingCartList(Long memberId);

}
