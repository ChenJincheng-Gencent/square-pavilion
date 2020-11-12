package com.square.mall.trade.center.service.controller;

import com.square.mall.common.dto.RspDto;
import com.square.mall.common.util.DatabaseUtil;
import com.square.mall.common.util.ModuleConstant;
import com.square.mall.trade.center.api.dto.ShoppingCartDto;
import com.square.mall.trade.center.service.service.ShoppingCartService;
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

        if (null == shoppingCartDto || null == shoppingCartDto.getItemId() || null == shoppingCartDto.getMemberId()) {
            return RspDto.FAILED;
        }
        Integer itemNum = shoppingCartService.selectItemNumByMemberAndItemId(shoppingCartDto.getMemberId(), shoppingCartDto
            .getItemId());
        if (itemNum > 0) {
            shoppingCartDto.setItemNum(shoppingCartDto.getItemNum() + itemNum);
            shoppingCartService.updateShoppingCart(shoppingCartDto);
        }else {
            shoppingCartService.insertShoppingCart(shoppingCartDto);
        }
        return DatabaseUtil.getResult(0, ModuleConstant.TRADE_CENTER);
    }

    /**
     * 更新购物车
     *
     * @param shoppingCartDto 购物车
     * @return 响应
     */
    @PutMapping("")
    public RspDto updateShoppingCart(@RequestBody ShoppingCartDto shoppingCartDto) {
        ShoppingCartDto oldShoppingCartDto = shoppingCartService.selectShoppingCart(shoppingCartDto.getMemberId(), shoppingCartDto
            .getItemId());
        if (null == oldShoppingCartDto) {
            log.error("oldShoppingCartDto is null. memberId: {}, itemId: {}", shoppingCartDto.getMemberId(), shoppingCartDto
                .getItemId());
            return RspDto.FAILED;
        }
        shoppingCartService.updateShoppingCart(shoppingCartDto);
        return RspDto.SUCCESS;
    }

    /**
     * 根据会员ID查询购物车列表
     *
     * @param memberId 会员ID
     * @return 购物车列表
     */
    @GetMapping("/list/member-id")
    public RspDto<List<ShoppingCartDto>> getShoppingCartList(@RequestParam("memberId") Long memberId) {

        List<ShoppingCartDto> shoppingCartDtoList = shoppingCartService.selectShoppingCartList(memberId);
        return new RspDto<>(shoppingCartDtoList);
    }

    /**
     * 根据会员ID和商品ID删除购物车
     *
     * @param memberId 会员ID
     * @param itemId 商品ID
     * @return 响应
     */
    @DeleteMapping("")
    public RspDto deleteShoppingCart(@RequestParam("memberId") Long memberId, @RequestParam("itemId") Long itemId) {
        shoppingCartService.deleteShoppingCart(memberId, itemId);
        return RspDto.SUCCESS;
    }

    /**
     * 根据会员ID和商品ID列表批量删除购物车
     *
     * @param memberId 会员ID
     * @param itemIds 商品ID列表
     * @return 响应
     */
    @DeleteMapping("/batch")
    public RspDto batchDeleteShoppingCartList(@RequestParam("memberId") Long memberId, @RequestParam("itemIds") Long[] itemIds) {
        shoppingCartService.batchDeleteShoppingCartList(memberId, itemIds);
        return RspDto.SUCCESS;
    }

    /**
     * 根据会员ID和商品ID列表批量删除购物车
     *
     * @param memberId 会员ID
     * @return 响应
     */
    @DeleteMapping("/all")
    public RspDto deleteAllShoppingCartList(@RequestParam("memberId") Long memberId) {
        shoppingCartService.deleteAllShoppingCartList(memberId);
        return RspDto.SUCCESS;
    }


}
