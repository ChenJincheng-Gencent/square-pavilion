package com.square.mall.item.center.api;

import com.square.mall.common.dto.CommonPageRes;
import com.square.mall.common.dto.CommonRes;
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
    CommonRes<Long> insertItem(@RequestBody ItemDto itemDto);

    /**
     * 更新商品
     *
     * @param itemDto 商品
     * @return 响应
     */
    @PutMapping("/item")
    CommonRes<Void> updateItem(@RequestBody ItemDto itemDto);

    /**
     * 删除商品
     *
     * @param id 数据库ID
     * @return 响应
     */
    @DeleteMapping("/item")
    CommonRes<Void> deleteItem(@RequestParam("id") Long id);

    /**
     * 条件查询商品列表
     *
     * @param itemDto 查询条件
     * @return 商品列表
     */
    @PostMapping("/item/list/condition")
    CommonRes<List<ItemDto>> selectItemByCondition(@RequestBody ItemDto itemDto);

    /**
     * 分页条件查询商品列表
     *
     * @param itemDto 查询条件
     * @param pageNum 当前页
     * @param pageSize 分页大小
     * @return 商品列表
     */
    @PostMapping("/item/list/page/condition")
    CommonPageRes<List<ItemDto>> selectPageItemByCondition(@RequestBody ItemDto itemDto, @RequestParam("pageNum") Integer pageNum,
                                                           @RequestParam("pageSize") Integer pageSize);


}
