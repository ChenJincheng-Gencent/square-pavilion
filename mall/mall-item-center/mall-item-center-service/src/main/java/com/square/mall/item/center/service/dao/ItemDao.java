package com.square.mall.item.center.service.dao;

import com.square.mall.item.center.service.eo.ItemEo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品Dao
 *
 * @author Gencent
 * @date 2020/7/28
 */
public interface ItemDao {

    /**
     * 插入商品
     *
     * @param itemEo 商品
     * @return 是否成功，1成功，0失败
     */
    int insertItem(ItemEo itemEo);

    /**
     * 更新商品
     *
     * @param itemEo 商品
     * @return 是否成功，1成功，0失败
     */
    int updateItem(ItemEo itemEo);

    /**
     * 删除商品
     *
     * @param id 数据库ID
     * @return 是否成功，1成功，0失败
     */
    int deleteItem(@Param("id") Long id);

    /**
     * 条件查询商品列表
     *
     * @param itemEo 查询条件
     * @return 商品列表
     */
    List<ItemEo> selectItemByCondition(ItemEo itemEo);

    /**
     * 根据名称查询商品
     *
     * @param name 名称
     * @return 商品
     */
    ItemEo selectItemByName(@Param("name") String name);

    /**
     * 根据ID查询商品
     *
     * @param id ID
     * @return 商品
     */
    ItemEo selectItemById(@Param("id") Long id);

}
