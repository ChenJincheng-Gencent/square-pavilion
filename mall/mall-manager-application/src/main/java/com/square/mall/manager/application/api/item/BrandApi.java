package com.square.mall.manager.application.api.item;

import com.square.mall.common.dto.PageRspDto;
import com.square.mall.common.dto.RspDto;
import com.square.mall.item.center.api.dto.BrandDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 品牌API
 *
 * @author Gencent
 * @date 2020/7/24
 */

@FeignClient(name="mall-item-center")
public interface BrandApi {

    /**
     * 插入品牌
     *
     * @param brandDto 品牌
     * @return 数据库ID
     */
    @PostMapping("/brand")
    RspDto<Long> insertBrand(@RequestBody BrandDto brandDto);

    /**
     * 更新品牌
     *
     * @param brandDto 品牌
     * @return 响应
     */
    @PutMapping("/brand")
    RspDto updateBrand(@RequestBody BrandDto brandDto);

    /**
     * 删除品牌
     *
     * @param id 数据库ID
     * @return 响应
     */
    @DeleteMapping("/brand")
    RspDto deleteBrand(@RequestParam("id") Long id);

    /**
     * 批量删除品牌
     *
     * @param ids ID数组
     * @return 响应
     */
    @DeleteMapping("/brand/batch")
    RspDto batchDeleteBrand(@RequestParam("ids") Long[] ids);

    /**
     * 条件查询品牌列表
     *
     * @param brandDto 查询条件
     * @return 品牌数据列表
     */
    @PostMapping("/brand/list/condition")
    RspDto<List<BrandDto>> selectBrandByCondition(@RequestBody BrandDto brandDto);

    /**
     * 分页条件查询品牌列表
     *
     * @param brandDto 查询条件
     * @param pageNum 当前页
     * @param pageSize 分页大小
     * @return 品牌列表
     */
    @PostMapping("/brand/list/page/condition")
    PageRspDto<List<BrandDto>> selectPageBrandByCondition(@RequestBody BrandDto brandDto, @RequestParam("pageNum") Integer pageNum,
                                                          @RequestParam("pageSize") Integer pageSize);

    /**
     * 根据ID查询品牌
     * @param id ID
     * @return 品牌
     */
    @GetMapping("/brand/id")
    RspDto<BrandDto> selectBrandById(@RequestParam("id") Long id);

    /**
     * 查询所有品牌列表
     *
     * @return 品牌列表
     */
    @GetMapping("/brand/all")
    RspDto<List<BrandDto>> selectBrandAll();

}
