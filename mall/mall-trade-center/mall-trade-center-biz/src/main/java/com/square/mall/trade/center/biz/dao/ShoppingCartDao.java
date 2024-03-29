package com.square.mall.trade.center.biz.dao;

import com.square.mall.trade.center.biz.eo.ShoppingCartEo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 购物车Dao
 *
 * @author Gencent
 * @date 2020/10/26
 */
public interface ShoppingCartDao {

    /**
     * 插入购物车
     *
     * @param shoppingCartEo 购物车
     * @return 是否成功，1成功，0失败
     */
    int insertShoppingCart(ShoppingCartEo shoppingCartEo);

    /**
     * 更新购物车
     *
     * @param shoppingCartEo 购物车
     * @return 是否成功，1成功，0失败
     */
    int updateShoppingCart(ShoppingCartEo shoppingCartEo);

    /**
     * 删除购物车
     *
     * @param memberId 会员ID
     * @param itemId 商品ID
     * @return 是否成功，1成功，0失败
     */
    int deleteShoppingCart(@Param("memberId") Long memberId, @Param("itemId") Long itemId);

    /**
     * 批量删除购物车列表
     *
     * @param memberId 会员ID
     * @param itemIds 商品ID列表
     * @return 是否成功，1成功，0失败
     */
    int batchDeleteShoppingCartList(@Param("memberId") Long memberId, @Param("itemIds") Long[] itemIds);

    /**
     * 根据会员ID删除购物车列表
     *
     * @param memberId 会员ID
     * @return 是否成功，1成功，0失败
     */
    int deleteAllShoppingCartList(@Param("memberId") Long memberId);

    /**
     * 根据会员ID和商品ID查询商品数量
     *
     * @param memberId 会员ID
     * @param itemId 商品ID
     * @return 商品数量
     */
    int selectItemNumByMemberAndItemId(@Param("memberId") Long memberId, @Param("itemId") Long itemId);

    /**
     * 根据会员ID查询购物车列表
     *
     * @param memberId 会员ID
     * @return 购物车列表
     */
    List<ShoppingCartEo> selectShoppingCartList(@Param("memberId") Long memberId);

    /**
     * 根据会员ID和商品ID查询购物车
     *
     * @param memberId 会员ID
     * @param itemId 商品ID
     * @return 购物车
     */
    ShoppingCartEo selectShoppingCart(@Param("memberId") Long memberId, @Param("itemId") Long itemId);


}
