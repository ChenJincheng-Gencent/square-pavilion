package com.square.mall.item.center.service.apiimpl;

import com.square.mall.common.dto.RspDto;
import com.square.mall.common.util.DatabaseUtil;
import com.square.mall.common.util.ModuleConstant;
import com.square.mall.item.center.api.ItemApi;
import com.square.mall.item.center.api.dto.ItemDto;
import com.square.mall.item.center.service.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 商品API实现类
 *
 * @author Gencent
 * @date 2020/7/23
 */
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class ItemApiImpl implements ItemApi {

    @Resource
    private ItemService itemService;

    @Override
    public RspDto<Long> insertItem(ItemDto itemDto) {
        int success = itemService.insertItem(itemDto);
        return DatabaseUtil.getResult(success, itemDto.getId(), ModuleConstant.ITEM_CENTER);
    }

    @Override
    public RspDto updateItem(ItemDto itemDto) {
        int success = itemService.updateItem(itemDto);
        return DatabaseUtil.getResult(success, ModuleConstant.ITEM_CENTER);
    }

    @Override
    public RspDto deleteItem(Long id) {
        int success = itemService.deleteItem(id);
        return DatabaseUtil.getResult(success, ModuleConstant.ITEM_CENTER);
    }

}
