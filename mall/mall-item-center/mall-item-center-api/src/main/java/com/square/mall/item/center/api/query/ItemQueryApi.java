package com.square.mall.item.center.api.query;

import com.square.mall.common.dto.PageRspDto;
import com.square.mall.common.dto.RspDto;
import com.square.mall.item.center.api.dto.ItemDto;

import java.util.List;

/**
 * 商品查询API
 *
 * @author Gencent
 * @date 2020/7/29
 */
public interface ItemQueryApi {

    /**
     * 条件查询商品列表
     *
     * @param itemDto 查询条件
     * @return 商品列表
     */
    RspDto<List<ItemDto>> selectItemByCondition(ItemDto itemDto);

    /**
     * 分页条件查询商品列表
     *
     * @param itemDto 查询条件
     * @param pageNum 当前页
     * @param pageSize 分页大小
     * @return 商品列表
     */
    PageRspDto<List<ItemDto>> selectPageItemByCondition(ItemDto itemDto, Integer pageNum, Integer pageSize);

}
