package com.square.mall.item.center.api;

import com.square.mall.common.dto.CommonPageRes;
import com.square.mall.common.dto.CommonRes;
import com.square.mall.item.center.api.dto.BrandDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 品牌API
 *
 * @author Gencent
 * @date 2020/7/24
 */

@FeignClient(contextId = "item-brand", name="mall-item-center")
public interface BrandApi {

    /**
     * 插入品牌
     *
     * @param brandDto 品牌
     * @return 数据库ID
     */
    @PostMapping("/brand")
    CommonRes<Long> insertBrand(@RequestBody BrandDto brandDto);

    /**
     * 更新品牌
     *
     * @param brandDto 品牌
     * @return 响应
     */
    @PutMapping("/brand")
    CommonRes<Void> updateBrand(@RequestBody BrandDto brandDto);

    /**
     * 删除品牌
     *
     * @param id 数据库ID
     * @return 响应
     */
    @DeleteMapping("/brand")
    CommonRes<Void> deleteBrand(@RequestParam("id") Long id);

    /**
     * 批量删除品牌
     *
     * @param ids ID数组
     * @return 响应
     */
    @DeleteMapping("/brand/batch")
    CommonRes<Void> batchDeleteBrand(@RequestParam("ids") Long[] ids);

    /**
     * 条件查询品牌列表
     *
     * @param brandDto 查询条件
     * @return 品牌数据列表
     */
    @PostMapping("/brand/list/condition")
    CommonRes<List<BrandDto>> selectBrandByCondition(@RequestBody BrandDto brandDto);

    /**
     * 分页条件查询品牌列表
     *
     * @param brandDto 查询条件
     * @param pageNum 当前页
     * @param pageSize 分页大小
     * @return 品牌列表
     */
    @PostMapping("/brand/list/page/condition")
    CommonPageRes<List<BrandDto>> selectPageBrandByCondition(@RequestBody BrandDto brandDto, @RequestParam("pageNum") Integer pageNum,
                                                             @RequestParam("pageSize") Integer pageSize);

    /**
     * 根据ID查询品牌
     * @param id ID
     * @return 品牌
     */
    @GetMapping("/brand/id")
    CommonRes<BrandDto> selectBrandById(@RequestParam("id") Long id);

    /**
     * 查询所有品牌列表
     *
     * @return 品牌列表
     */
    @GetMapping("/brand/all")
    CommonRes<List<BrandDto>> selectBrandAll();

}
