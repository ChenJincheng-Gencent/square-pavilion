package com.square.mall.trade.center.service.dao;

import com.square.mall.trade.center.api.dto.ShoppingCartDto;
import com.square.mall.trade.center.service.eo.ShoppingCartEo;
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
     * @param id 数据库ID
     * @return 是否成功，1成功，0失败
     */
    int deleteShoppingCart(@Param("id") Long id);

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


}
