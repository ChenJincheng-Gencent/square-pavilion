package com.square.mall.item.center.service.controller;

import com.square.mall.common.dto.PageRspDto;
import com.square.mall.common.dto.RspDto;
import com.square.mall.common.util.DatabaseUtil;
import com.square.mall.common.util.ModuleConstant;
import com.square.mall.item.center.api.dto.ItemDto;
import com.square.mall.item.center.service.service.ItemService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品Controller
 *
 * @author Gencent
 * @date 2020/10/26
 */
@RestController
@RequestMapping("/item")
public class ItemController {

    @Resource
    private ItemService itemService;

    /**
     * 插入商品
     *
     * @param itemDto 商品
     * @return 数据库ID
     */
    @PostMapping("")
    public RspDto<Long> insertItem(@RequestBody ItemDto itemDto) {
        int success = itemService.insertItem(itemDto);
        return DatabaseUtil.getResult(success, itemDto.getId(), ModuleConstant.ITEM_CENTER);
    }

    /**
     * 更新商品
     *
     * @param itemDto 商品
     * @return 响应
     */
    @PutMapping("")
    public RspDto updateItem(ItemDto itemDto) {
        int success = itemService.updateItem(itemDto);
        return DatabaseUtil.getResult(success, ModuleConstant.ITEM_CENTER);
    }


    /**
     * 删除商品
     *
     * @param id 数据库ID
     * @return 响应
     */
    @DeleteMapping("")
    public RspDto deleteItem(Long id) {
        int success = itemService.deleteItem(id);
        return DatabaseUtil.getResult(success, ModuleConstant.ITEM_CENTER);
    }

    /**
     * 条件查询商品列表
     *
     * @param itemDto 查询条件
     * @return 商品列表
     */
    @GetMapping("list/condition")
    public RspDto<List<ItemDto>> selectItemByCondition(@RequestBody ItemDto itemDto) {
        return new RspDto<>(itemService.selectItemByCondition(itemDto));
    }

    /**
     * 分页条件查询商品列表
     *
     * @param itemDto 查询条件
     * @param pageNum 当前页
     * @param pageSize 分页大小
     * @return 商品列表
     */
    @GetMapping("/list/page/condition")
    public PageRspDto<List<ItemDto>> selectPageItemByCondition(@RequestBody ItemDto itemDto, @RequestParam("pageNum") Integer pageNum,
                                                       @RequestParam("pageSize") Integer pageSize) {
        return itemService.selectPageItemByCondition(itemDto, pageNum, pageSize);
    }


}
