package com.square.mall.trade.center.biz.controller;

import com.square.mall.common.dto.CommonRes;
import com.square.mall.common.enums.ErrorCode;
import com.square.mall.trade.center.api.ShoppingCartApi;
import com.square.mall.trade.center.api.dto.ShoppingCartDto;
import com.square.mall.trade.center.biz.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 购物车Controller
 *
 * @author Gencent
 * @date 2020/10/26
 */
@RestController
@RequestMapping("/shopping/cart")
@Slf4j
public class ShoppingCartController implements ShoppingCartApi {

    @Resource
    private ShoppingCartService shoppingCartService;

    /**
     * 加入购物车
     *
     * @param shoppingCartDto 购物车
     * @return 响应
     */
    @PostMapping("/addShoppingCart")
    @Override
    public CommonRes<Void> addShoppingCart(@RequestBody ShoppingCartDto shoppingCartDto) {

        if (null == shoppingCartDto || null == shoppingCartDto.getItemId() || null == shoppingCartDto.getMemberId()) {
            return new CommonRes<>(ErrorCode.PARA_IS_NULL);
        }
        Integer itemNum = shoppingCartService.selectItemNumByMemberAndItemId(shoppingCartDto.getMemberId(), shoppingCartDto
            .getItemId());
        if (itemNum > 0) {
            shoppingCartDto.setItemNum(shoppingCartDto.getItemNum() + itemNum);
            shoppingCartService.updateShoppingCart(shoppingCartDto);
        }else {
            shoppingCartService.insertShoppingCart(shoppingCartDto);
        }
        return CommonRes.SUCCESS;
    }

    /**
     * 更新购物车
     *
     * @param shoppingCartDto 购物车
     * @return 响应
     */
    @PutMapping("/updateShoppingCart")
    @Override
    public CommonRes<Void> updateShoppingCart(@RequestBody ShoppingCartDto shoppingCartDto) {
        ShoppingCartDto oldShoppingCartDto = shoppingCartService.selectShoppingCart(shoppingCartDto.getMemberId(), shoppingCartDto
            .getItemId());
        if (null == oldShoppingCartDto) {
            log.error("oldShoppingCartDto is null. memberId: {}, itemId: {}", shoppingCartDto.getMemberId(), shoppingCartDto
                .getItemId());
            return CommonRes.FAILURE;
        }
        shoppingCartService.updateShoppingCart(shoppingCartDto);
        return CommonRes.SUCCESS;
    }

    /**
     * 根据会员ID查询购物车列表
     *
     * @param memberId 会员ID
     * @return 购物车列表
     */
    @GetMapping("/getShoppingCartList")
    @Override
    public CommonRes<List<ShoppingCartDto>> getShoppingCartList(@RequestParam("memberId") Long memberId) {

        List<ShoppingCartDto> shoppingCartDtoList = shoppingCartService.selectShoppingCartList(memberId);
        return new CommonRes<>(shoppingCartDtoList);
    }

    /**
     * 根据会员ID和商品ID删除购物车
     *
     * @param memberId 会员ID
     * @param itemId 商品ID
     * @return 响应
     */
    @DeleteMapping("/deleteShoppingCart")
    @Override
    public CommonRes<Void> deleteShoppingCart(@RequestParam("memberId") Long memberId, @RequestParam("itemId") Long itemId) {
        shoppingCartService.deleteShoppingCart(memberId, itemId);
        return CommonRes.SUCCESS;
    }

    /**
     * 根据会员ID和商品ID列表批量删除购物车
     *
     * @param memberId 会员ID
     * @param itemIds 商品ID列表
     * @return 响应
     */
    @DeleteMapping("/batchDeleteShoppingCartList")
    @Override
    public CommonRes<Void> batchDeleteShoppingCartList(@RequestParam("memberId") Long memberId, @RequestParam("itemIds") Long[] itemIds) {
        shoppingCartService.batchDeleteShoppingCartList(memberId, itemIds);
        return CommonRes.SUCCESS;
    }

    /**
     * 根据会员ID和商品ID列表批量删除购物车
     *
     * @param memberId 会员ID
     * @return 响应
     */
    @DeleteMapping("/deleteAllShoppingCartList")
    @Override
    public CommonRes<Void> deleteAllShoppingCartList(@RequestParam("memberId") Long memberId) {
        shoppingCartService.deleteAllShoppingCartList(memberId);
        return CommonRes.SUCCESS;
    }


}
