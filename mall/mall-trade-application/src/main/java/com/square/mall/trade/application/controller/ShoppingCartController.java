package com.square.mall.trade.application.controller;

import com.square.mall.common.dto.CommonRes;
import com.square.mall.trade.application.service.ShoppingCartService;
import com.square.mall.trade.center.api.dto.ShoppingCartDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 购物车Controller
 *
 * @author Gencent
 * @date 2020/11/12
 */
@RestController
@RequestMapping("/shopping/cart")
@Slf4j
@Api(tags = "购物车")
public class ShoppingCartController {

    @Resource
    private ShoppingCartService shoppingCartService;

    /**
     * 加入购物车
     *
     * @param shoppingCartDto 购物车
     * @return 响应
     */
    @ApiOperation("加入购物车")
    @PostMapping("/addShoppingCart")
    public CommonRes<Void> addShoppingCart(@RequestBody ShoppingCartDto shoppingCartDto) {
        return shoppingCartService.addShoppingCart(shoppingCartDto);
    }

    /**
     * 更新购物车
     *
     * @param shoppingCartDto 购物车
     * @return 响应
     */
    @ApiOperation("更新购物车")
    @PutMapping("/updateShoppingCart")
    public CommonRes<Void> updateShoppingCart(@RequestBody ShoppingCartDto shoppingCartDto) {

        return shoppingCartService.updateShoppingCart(shoppingCartDto);

    }

    /**
     * 根据会员ID查询购物车列表
     *
     * @param memberId 会员ID
     * @return 购物车列表
     */
    @ApiOperation("根据会员ID查询购物车列表")
    @GetMapping("/getShoppingCartList")
    public CommonRes<List<ShoppingCartDto>> getShoppingCartList(@RequestParam("memberId") Long memberId) {
        return shoppingCartService.getShoppingCartList(memberId);
    }

    /**
     * 根据会员ID和商品ID删除购物车
     *
     * @param memberId 会员ID
     * @param itemId 商品ID
     * @return 响应
     */
    @ApiOperation("根据会员ID和商品ID删除购物车")
    @DeleteMapping("/deleteShoppingCart")
    public CommonRes<Void> deleteShoppingCart(@RequestParam("memberId") Long memberId,
                                              @RequestParam("itemId") Long itemId) {
        return shoppingCartService.deleteShoppingCart(memberId, itemId);
    }

    /**
     * 根据会员ID和商品ID列表批量删除购物车
     *
     * @param memberId 会员ID
     * @param itemIds 商品ID列表
     * @return 响应
     */
    @ApiOperation("根据会员ID和商品ID列表批量删除购物车")
    @DeleteMapping("/batchDeleteShoppingCartList")
    public CommonRes<Void> batchDeleteShoppingCartList(@RequestParam("memberId") Long memberId,
                                                       @RequestParam("itemIds") Long[] itemIds) {
        return shoppingCartService.batchDeleteShoppingCartList(memberId, itemIds);
    }

    /**
     * 根据会员ID删除所有购物车列表
     *
     * @param memberId 会员ID
     * @return 响应
     */
    @ApiOperation("根据会员ID删除所有购物车列表")
    @DeleteMapping("/deleteAllShoppingCartList")
    public CommonRes<Void> deleteAllShoppingCartList(@RequestParam("memberId") Long memberId) {
        return shoppingCartService.deleteAllShoppingCartList(memberId);
    }







}
