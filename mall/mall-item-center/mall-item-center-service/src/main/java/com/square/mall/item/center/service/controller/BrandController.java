package com.square.mall.item.center.service.controller;

import com.square.mall.common.dto.CommonPageRes;
import com.square.mall.common.dto.CommonRes;
import com.square.mall.item.center.api.dto.BrandDto;
import com.square.mall.item.center.service.service.BrandService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 品牌Controller
 *
 * @author Gencent
 * @date 2020/10/26
 */
@RestController
@RequestMapping("/brand")
public class BrandController {

    @Resource
    private BrandService brandService;

    /**
     * 条件查询品牌列表
     *
     * @param brandDto 查询条件
     * @return 品牌数据列表
     */
    @PostMapping("/list/condition")
    public CommonRes<List<BrandDto>> selectBrandByCondition(@RequestBody BrandDto brandDto) {
        return new CommonRes<>(brandService.selectBrandByCondition(brandDto));
    }

    /**
     * 分页条件查询品牌列表
     *
     * @param brandDto 查询条件
     * @param pageNum 当前页
     * @param pageSize 分页大小
     * @return 品牌列表
     */
    @PostMapping("/list/page/condition")
    public CommonPageRes<List<BrandDto>> selectPageBrandByCondition(@RequestBody BrandDto brandDto, @RequestParam("pageNum") Integer pageNum,
                                                                    @RequestParam("pageSize") Integer pageSize) {
        return brandService.selectPageBrandByCondition(brandDto, pageNum, pageSize);
    }


    /**
     * 根据ID查询品牌
     * @param id ID
     * @return 品牌
     */
    @GetMapping("/id")
    public CommonRes<BrandDto> selectBrandById(@RequestParam("id") Long id) {
        return new CommonRes<>(brandService.selectBrandById(id));
    }


    /**
     * 查询所有品牌列表
     *
     * @return 品牌列表
     */
    @GetMapping("/all")
    public CommonRes<List<BrandDto>> selectBrandAll() {
        return new CommonRes<>(brandService.selectBrandAll());
    }

    /**
     * 插入品牌
     *
     * @param brandDto 品牌
     * @return 数据库ID
     */
    @PostMapping("")
    public CommonRes<Long> insertBrand(@RequestBody BrandDto brandDto) {
        int success = brandService.insertBrand(brandDto);
        return new CommonRes<>(brandDto.getId());
    }


    /**
     * 更新品牌
     *
     * @param brandDto 品牌
     * @return 响应
     */
    @PutMapping("")
    public CommonRes<Void> updateBrand(@RequestBody BrandDto brandDto) {
        int success = brandService.updateBrand(brandDto);
        return CommonRes.SUCCESS;
    }


    /**
     * 删除品牌
     *
     * @param id 数据库ID
     * @return 响应
     */
    @DeleteMapping("")
    public CommonRes<Void> deleteBrand(@RequestParam("id") Long id) {
        int success = brandService.deleteBrand(id);
        return CommonRes.SUCCESS;
    }


    /**
     * 批量删除品牌
     *
     * @param ids ID数组
     * @return 响应
     */
    @DeleteMapping("/batch")
    public CommonRes<Void> batchDeleteBrand(@RequestParam("ids") Long[] ids) {
        int success = brandService.batchDeleteBrand(ids);
        return CommonRes.SUCCESS;
    }

}
