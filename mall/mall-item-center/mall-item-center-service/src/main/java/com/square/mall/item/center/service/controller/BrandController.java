package com.square.mall.item.center.service.controller;

import com.square.mall.common.dto.RspDto;
import com.square.mall.common.util.DatabaseUtil;
import com.square.mall.common.util.ModuleConstant;
import com.square.mall.item.center.api.dto.BrandDto;
import com.square.mall.item.center.service.service.BrandService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Resource
    private BrandService brandService;

    /**
     * 插入品牌
     *
     * @param brandDto 品牌
     * @return 数据库ID
     */
    @PostMapping("/brand")
    public RspDto<Long> insertBrand(@RequestBody BrandDto brandDto) {
        int success = brandService.insertBrand(brandDto);
        return DatabaseUtil.getResult(success, brandDto.getId(), ModuleConstant.ITEM_CENTER);
    }


    /**
     * 更新品牌
     *
     * @param brandDto 品牌
     * @return 响应
     */
    @PutMapping("/brand")
    public RspDto updateBrand(@RequestBody BrandDto brandDto) {
        int success = brandService.updateBrand(brandDto);
        return DatabaseUtil.getResult(success, ModuleConstant.ITEM_CENTER);
    }


    /**
     * 删除品牌
     *
     * @param id 数据库ID
     * @return 响应
     */
    @DeleteMapping("/brand")
    public RspDto deleteBrand(@RequestParam("id") Long id) {
        int success = brandService.deleteBrand(id);
        return DatabaseUtil.getResult(success, ModuleConstant.ITEM_CENTER);
    }


    /**
     * 批量删除品牌
     *
     * @param ids ID数组
     * @return 响应
     */
    @DeleteMapping("/brand/batch")
    public RspDto batchDeleteBrand(@RequestParam("ids") Long[] ids) {
        int success = brandService.batchDeleteBrand(ids);
        return DatabaseUtil.getResult(success, ModuleConstant.ITEM_CENTER);
    }
}
