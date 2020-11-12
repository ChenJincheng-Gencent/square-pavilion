package com.square.mall.trade.application.controller;

import com.square.mall.common.dto.RspDto;
import com.square.mall.trade.application.service.ShoppingCartService;
import com.square.mall.trade.center.api.dto.ShoppingCartDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 购物车Controller
 *
 * @author Gencent
 * @date 2020/11/12
 */
@RestController
@RequestMapping("/shopping/cart")
@Slf4j
public class ShoppingCartController {

    @Resource
    private ShoppingCartService shoppingCartService;

    /**
     * 加入购物车
     *
     * @param shoppingCartDto 购物车
     * @return 响应
     */
    @PostMapping("")
    public RspDto addShoppingCart(@RequestBody ShoppingCartDto shoppingCartDto) {
        return shoppingCartService.addShoppingCart(shoppingCartDto);
    }

}
