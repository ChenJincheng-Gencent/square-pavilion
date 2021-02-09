package com.square.mall.item.center.api;

import com.square.mall.common.dto.PageRspDto;
import com.square.mall.common.dto.RspDto;
import com.square.mall.item.center.api.dto.ItemDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品API
 *
 * @author Gencent
 * @date 2020/7/24
 */
@FeignClient(contextId = "item-item", name="mall-item-center")
public interface ItemApi {

    /**
     * 插入商品
     *
     * @param itemDto 商品
     * @return 数据库ID
     */
    @PostMapping("/item")
    RspDto<Long> insertItem(@RequestBody ItemDto itemDto);

    /**
     * 更新商品
     *
     * @param itemDto 商品
     * @return 响应
     */
    @PutMapping("/item")
    RspDto updateItem(@RequestBody ItemDto itemDto);

    /**
     * 删除商品
     *
     * @param id 数据库ID
     * @return 响应
     */
    @DeleteMapping("/item")
    RspDto deleteItem(@RequestParam("id") Long id);

    /**
     * 条件查询商品列表
     *
     * @param itemDto 查询条件
     * @return 商品列表
     */
    @PostMapping("/item/list/condition")
    RspDto<List<ItemDto>> selectItemByCondition(@RequestBody ItemDto itemDto);

    /**
     * 分页条件查询商品列表
     *
     * @param itemDto 查询条件
     * @param pageNum 当前页
     * @param pageSize 分页大小
     * @return 商品列表
     */
    @PostMapping("/item/list/page/condition")
    PageRspDto<List<ItemDto>> selectPageItemByCondition(@RequestBody ItemDto itemDto, @RequestParam("pageNum") Integer pageNum,
                                                        @RequestParam("pageSize") Integer pageSize);


}
