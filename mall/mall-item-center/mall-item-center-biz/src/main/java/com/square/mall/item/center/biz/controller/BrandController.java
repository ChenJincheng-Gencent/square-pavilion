package com.square.mall.item.center.biz.controller;

import com.square.mall.common.dto.CommonPageRes;
import com.square.mall.common.dto.CommonRes;
import com.square.mall.item.center.api.BrandApi;
import com.square.mall.item.center.api.dto.BrandDto;
import com.square.mall.item.center.biz.service.BrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(tags = "品牌")
public class BrandController implements BrandApi {

    @Resource
    private BrandService brandService;

    /**
     * 条件查询品牌列表
     *
     * @param brandDto 查询条件
     * @return 品牌数据列表
     */
    @ApiOperation("条件查询品牌列表")
    @PostMapping("/selectBrandByCondition")
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
    @ApiOperation("分页条件查询品牌列表")
    @PostMapping("/selectPageBrandByCondition")
    public CommonPageRes<List<BrandDto>> selectPageBrandByCondition(@RequestBody BrandDto brandDto,
                      @ApiParam(value="当前页") @RequestParam(name = "pageNum", required = false) Integer pageNum,
                      @ApiParam(value="分页大小") @RequestParam(name = "pageSize", required = false) Integer pageSize) {
        return brandService.selectPageBrandByCondition(brandDto, pageNum, pageSize);
    }


    /**
     * 根据ID查询品牌
     * @param id ID
     * @return 品牌
     */
    @ApiOperation("根据ID查询品牌")
    @GetMapping("/selectBrandById")
    public CommonRes<BrandDto> selectBrandById(@ApiParam(value="数据库ID",required = true) @RequestParam("id") Long id) {
        return new CommonRes<>(brandService.selectBrandById(id));
    }


    /**
     * 查询所有品牌列表
     *
     * @return 品牌列表
     */
    @ApiOperation("查询所有品牌列表")
    @GetMapping("/selectBrandAll")
    public CommonRes<List<BrandDto>> selectBrandAll() {
        return new CommonRes<>(brandService.selectBrandAll());
    }

    /**
     * 插入品牌
     *
     * @param brandDto 品牌
     * @return 数据库ID
     */
    @ApiOperation("插入品牌")
    @PostMapping("/insertBrand")
    public CommonRes<Long> insertBrand(@RequestBody BrandDto brandDto) {
        brandService.insertBrand(brandDto);
        return new CommonRes<>(brandDto.getId());
    }


    /**
     * 更新品牌
     *
     * @param brandDto 品牌
     * @return 操作结果
     */
    @ApiOperation("更新品牌")
    @PutMapping("/updateBrand")
    public CommonRes<Void> updateBrand(@RequestBody BrandDto brandDto) {
        brandService.updateBrand(brandDto);
        return CommonRes.SUCCESS;
    }


    /**
     * 删除品牌
     *
     * @param id 数据库ID
     * @return 操作结果
     */
    @ApiOperation("删除品牌")
    @DeleteMapping("/deleteBrand")
    public CommonRes<Void> deleteBrand(@ApiParam(value="数据库ID",required = true) @RequestParam("id") Long id) {
        brandService.deleteBrand(id);
        return CommonRes.SUCCESS;
    }


    /**
     * 批量删除品牌
     *
     * @param ids ID数组
     * @return 操作结果
     */
    @ApiOperation("批量删除品牌")
    @DeleteMapping("/batchDeleteBrand")
    public CommonRes<Void> batchDeleteBrand(@ApiParam(value="ID列表，以逗号隔开",required = true) @RequestParam(value = "ids") Long[] ids) {
        brandService.batchDeleteBrand(ids);
        return CommonRes.SUCCESS;
    }

}
