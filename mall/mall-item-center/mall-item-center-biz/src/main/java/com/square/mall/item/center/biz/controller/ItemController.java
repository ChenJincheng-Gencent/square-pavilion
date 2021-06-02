package com.square.mall.item.center.biz.controller;

import com.square.mall.common.dto.CommonPageRes;
import com.square.mall.common.dto.CommonRes;
import com.square.mall.item.center.api.dto.ItemDto;
import com.square.mall.item.center.biz.service.ItemService;
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
    public CommonRes<Long> insertItem(@RequestBody ItemDto itemDto) {
        int success = itemService.insertItem(itemDto);
        return new CommonRes<>(itemDto.getId());
    }

    /**
     * 更新商品
     *
     * @param itemDto 商品
     * @return 响应
     */
    @PutMapping("")
    public CommonRes<Void> updateItem(ItemDto itemDto) {
        int success = itemService.updateItem(itemDto);
        return CommonRes.SUCCESS;
    }


    /**
     * 删除商品
     *
     * @param id 数据库ID
     * @return 响应
     */
    @DeleteMapping("")
    public CommonRes<Void> deleteItem(Long id) {
        int success = itemService.deleteItem(id);
        return CommonRes.SUCCESS;
    }

    /**
     * 条件查询商品列表
     *
     * @param itemDto 查询条件
     * @return 商品列表
     */
    @PostMapping("list/condition")
    public CommonRes<List<ItemDto>> selectItemByCondition(@RequestBody ItemDto itemDto) {
        return new CommonRes<>(itemService.selectItemByCondition(itemDto));
    }

    /**
     * 分页条件查询商品列表
     *
     * @param itemDto 查询条件
     * @param pageNum 当前页
     * @param pageSize 分页大小
     * @return 商品列表
     */
    @PostMapping("/list/page/condition")
    public CommonPageRes<List<ItemDto>> selectPageItemByCondition(@RequestBody ItemDto itemDto, @RequestParam("pageNum") Integer pageNum,
                                                                  @RequestParam("pageSize") Integer pageSize) {
        return itemService.selectPageItemByCondition(itemDto, pageNum, pageSize);
    }


}
