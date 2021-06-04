package com.square.mall.manager.application.controller;

import com.square.mall.common.dto.CommonPageRes;
import com.square.mall.common.dto.CommonRes;
import com.square.mall.common.util.ListUtil;
import com.square.mall.item.center.api.dto.BrandDto;
import com.square.mall.manager.application.service.BrandService;
import com.square.mall.manager.application.vo.ModBrandVo;
import com.square.mall.manager.application.vo.Select2Vo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * 品牌Controller
 *
 * @author Gencent
 * @date 2020/7/24
 */
@Controller
@EnableAutoConfiguration
@RequestMapping(value = "/brand")
@Slf4j
@Validated
@Api(tags = "品牌")
public class BrandController {

    @Resource
    private BrandService brandService;

    /**
     * 分页条件查询品牌列表
     *
     * @param brandDto 查询条件
     * @param pageNum 当前页
     * @param pageSize 分页大小
     * @return 品牌列表
     */
    @PostMapping("/selectPageBrandByCondition")
    @ResponseBody
    @ApiOperation(value = "分页条件查询品牌列表")
    public CommonPageRes<List<BrandDto>> selectPageBrandByCondition(@RequestBody BrandDto brandDto,
                                                                    @RequestParam("pageNum") Integer pageNum,
                                                                    @RequestParam("pageSize") Integer pageSize)  {

        CommonPageRes<List<BrandDto>> brandDtoList = brandService.selectPageBrandByCondition(brandDto, pageNum, pageSize);
        log.info("brandDtoList: {}, brandDto: {}, pageNum: {}, pageSize: {}", brandDtoList, brandDto, pageNum, pageSize);

        return brandDtoList;

    }

    /**
     * 插入品牌
     *
     * @param brandDto 品牌
     * @return 数据库ID
     */
    @PostMapping("/insertBrand")
    @ResponseBody
    @ApiOperation(value = "插入品牌")
    public CommonRes<Long> insertBrand(@RequestBody @Valid BrandDto brandDto) {
        CommonRes<Long> id = brandService.insertBrand(brandDto);
        log.info("id: {}, brandDto: {}", id.getData(), brandDto);
        return id;
    }

    /**
     * 更新品牌
     *
     * @param modBrandVo 品牌
     * @return 响应
     */
    @PutMapping("/updateBrand")
    @ResponseBody
    @ApiOperation(value = "更新品牌")
    public CommonRes<Void> updateBrand(@RequestBody @Valid ModBrandVo modBrandVo) {
        BrandDto brandDto = new BrandDto();
        BeanUtils.copyProperties(modBrandVo, brandDto);
        return brandService.updateBrand(brandDto);
    }

    /**
     * 批量删除品牌
     *
     * @param ids ID数组
     * @return 响应
     */
    @DeleteMapping("/deleteBrand")
    @ResponseBody
    @ApiOperation(value = "批量删除品牌")
    public CommonRes<Void> deleteBrand(Long[] ids) {
        return brandService.batchDeleteBrand(ids);
    }

    /**
     * 根据ID查询品牌
     *
     * @param id ID
     * @return 品牌
     */
    @GetMapping("/selectBrandById")
    @ResponseBody
    @ApiOperation(value = "根据ID查询品牌")
    public CommonRes<BrandDto> selectBrandById(@RequestParam("id") @NotNull(message = "ID不能为空") Long id)  {

        CommonRes<BrandDto> brandDto = brandService.selectBrandById(id);
        log.info("brandDto: {}, id: {}", brandDto, id);

        return brandDto;

    }

    /**
     * 查询所有品牌列表
     *
     * @return 品牌列表
     */
    @GetMapping("/selectBrandAll")
    @ResponseBody
    @ApiOperation(value = "查询所有品牌列表")
    public CommonRes<List<Select2Vo>> selectBrandAll()  {

        List<BrandDto> brandDtoList = brandService.selectBrandAll().getData();
        if (ListUtil.isBlank(brandDtoList)) {
            return new CommonRes<>(null);
        }
        List<Select2Vo> select2VoList = new ArrayList<>();
        brandDtoList.forEach( x -> {
            Select2Vo select2Vo = new Select2Vo();
            select2Vo.setId(x.getId());
            select2Vo.setText(x.getName());
            select2VoList.add(select2Vo);
        });

        return new CommonRes<>(select2VoList);

    }

}
