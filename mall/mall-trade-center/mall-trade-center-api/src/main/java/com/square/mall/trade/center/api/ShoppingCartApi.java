package com.square.mall.trade.center.api;

import com.square.mall.common.dto.CommonRes;
import com.square.mall.trade.center.api.dto.ShoppingCartDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 购物车API
 *
 * @author Gencent
 * @date 2020/10/28
 */
@FeignClient(name="mall-trade-center")
public interface ShoppingCartApi {

    /**
     * 加入购物车
     *
     * @param shoppingCartDto 购物车
     * @return 响应
     */
    @PostMapping("/shopping/cart")
    CommonRes<Void> addShoppingCart(@RequestBody ShoppingCartDto shoppingCartDto);

    /**
     * 更新购物车
     *
     * @param shoppingCartDto 购物车
     * @return 响应
     */
    @PutMapping("/shopping/cart")
    CommonRes<Void> updateShoppingCart(@RequestBody ShoppingCartDto shoppingCartDto);

    /**
     * 根据会员ID查询购物车列表
     *
     * @param memberId 会员ID
     * @return 购物车列表
     */
    @GetMapping("/shopping/cart/list/member-id")
    CommonRes<List<ShoppingCartDto>> getShoppingCartList(@RequestParam("memberId") Long memberId);

    /**
     * 根据会员ID和商品ID删除购物车
     *
     * @param memberId 会员ID
     * @param itemId 商品ID
     * @return 响应
     */
    @DeleteMapping("/shopping/cart")
    CommonRes<Void> deleteShoppingCart(@RequestParam("memberId") Long memberId, @RequestParam("itemId") Long itemId);

    /**
     * 根据会员ID和商品ID列表批量删除购物车
     *
     * @param memberId 会员ID
     * @param itemIds 商品ID列表
     * @return 响应
     */
    @DeleteMapping("/shopping/cart/batch")
    CommonRes<Void> batchDeleteShoppingCartList(@RequestParam("memberId") Long memberId, @RequestParam("itemIds") Long[] itemIds);

    /**
     * 根据会员ID和商品ID列表批量删除购物车
     *
     * @param memberId 会员ID
     * @return 响应
     */
    @DeleteMapping("/shopping/cart/all")
    CommonRes<Void> deleteAllShoppingCartList(@RequestParam("memberId") Long memberId);


}
