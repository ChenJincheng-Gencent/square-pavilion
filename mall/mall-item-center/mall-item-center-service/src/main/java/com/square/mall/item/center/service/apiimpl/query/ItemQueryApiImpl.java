package com.square.mall.item.center.service.apiimpl.query;

import com.square.mall.common.dto.PageRspDto;
import com.square.mall.common.dto.RspDto;
import com.square.mall.item.center.api.dto.ItemDto;
import com.square.mall.item.center.api.query.ItemQueryApi;
import com.square.mall.item.center.service.service.ItemService;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品查询API实现类
 *
 * @author Gencent
 * @date 2020/7/23
 */
@Slf4j
public class ItemQueryApiImpl implements ItemQueryApi {

    @Resource
    private ItemService itemService;

    @Override
    public RspDto<List<ItemDto>> selectItemByCondition(ItemDto itemDto) {
        return new RspDto<>(itemService.selectItemByCondition(itemDto));
    }

    @Override
    public PageRspDto<List<ItemDto>> selectPageItemByCondition(ItemDto itemDto, Integer pageNum, Integer pageSize) {
        return itemService.selectPageItemByCondition(itemDto, pageNum, pageSize);
    }
}
