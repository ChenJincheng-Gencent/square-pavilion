package com.square.mall.item.center.service.service;

import com.square.mall.common.dto.PageRspDto;
import com.square.mall.item.center.api.dto.ItemDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品Service
 *
 * @author Gencent
 * @date 2020/7/29
 */
public interface ItemService {

    /**
     * 插入商品
     *
     * @param itemDto 商品
     * @return 是否成功，1成功，0失败
     */
    int insertItem(ItemDto itemDto);

    /**
     * 更新商品
     *
     * @param itemDto 商品
     * @return 否成功，1成功，0失败
     */
    int updateItem(ItemDto itemDto);

    /**
     * 删除商品
     *
     * @param id 数据库ID
     * @return 是否成功，1成功，0失败
     */
    int deleteItem(Long id);

    /**
     * 条件查询商品列表
     *
     * @param itemDto 查询条件
     * @return 商品列表
     */
    List<ItemDto> selectItemByCondition(ItemDto itemDto);

    /**
     * 分页条件查询商品列表
     *
     * @param itemDto 查询条件
     * @param pageNum 当前页
     * @param pageSize 分页大小
     * @return 商品列表
     */
    PageRspDto<List<ItemDto>> selectPageItemByCondition(ItemDto itemDto, Integer pageNum, Integer pageSize);

    /**
     * 根据名称查询商品
     *
     * @param name 名称
     * @return 商品
     */
    ItemDto selectItemByName(@Param("name") String name);

    /**
     * 根据ID查询商品
     *
     * @param id ID
     * @return 商品
     */
    ItemDto selectItemById(@Param("id") Long id);

}
