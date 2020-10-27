package com.square.mall.trade.center.service.service;

import com.square.mall.trade.center.api.dto.ShoppingCartDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 购物车Service
 *
 * @author Gencent
 * @date 2020/10/26
 */
public interface ShoppingCartService {

    /**
     * 插入购物车
     *
     * @param shoppingCartDto 商品
     * @return 是否成功，1成功，0失败
     */
    int insertShoppingCart(ShoppingCartDto shoppingCartDto);

    /**
     * 更新购物车
     *
     * @param shoppingCartDto 购物车
     * @return 否成功，1成功，0失败
     */
    int updateShoppingCart(ShoppingCartDto shoppingCartDto);

    /**
     * 删除购物车
     *
     * @param id 数据库ID
     * @return 是否成功，1成功，0失败
     */
    int deleteShoppingCart(Long id);

    /**
     * 根据会员ID和商品ID查询商品数量
     *
     * @param memberId 会员ID
     * @param itemId 商品I
     */
    Integer selectItemNumByMemberAndItemId(Long memberId, Long itemId);

    /**
     * 根据会员ID查询购物车列表
     *
     * @param memberId 会员ID
     * @return 购物车列表
     */
    List<ShoppingCartDto> selectShoppingCartList(Long memberId);

}
