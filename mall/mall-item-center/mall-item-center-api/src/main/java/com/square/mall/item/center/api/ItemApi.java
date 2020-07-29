package com.square.mall.item.center.api;

import com.square.mall.common.dto.RspDto;
import com.square.mall.item.center.api.dto.ItemDto;

/**
 * 商品API
 *
 * @author Gencent
 * @date 2020/7/24
 */
public interface ItemApi {

    /**
     * 插入商品
     *
     * @param itemDto 商品
     * @return 数据库ID
     */
    RspDto<Long> insertItem(ItemDto itemDto);

    /**
     * 更新商品
     *
     * @param itemDto 商品
     * @return 响应
     */
    RspDto updateItem(ItemDto itemDto);

    /**
     * 删除商品
     *
     * @param id 数据库ID
     * @return 响应
     */
    RspDto deleteItem(Long id);

}
