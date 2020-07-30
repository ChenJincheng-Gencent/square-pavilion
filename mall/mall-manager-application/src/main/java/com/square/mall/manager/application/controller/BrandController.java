package com.square.mall.manager.application.controller;

import com.square.mall.common.dto.PageRspDto;
import com.square.mall.common.dto.RspDto;
import com.square.mall.item.center.api.dto.BrandDto;
import com.square.mall.manager.application.service.BrandService;
import com.square.mall.manager.application.vo.ModBrandVo;
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
import java.util.List;

/**
 * 品牌Controller
 *
 * @author Gencent
 * @date 2020/7/24
 */
@Controller
@EnableAutoConfiguration
@RequestMapping(value = "/manager/v1")
@Slf4j
@Validated
@Api(tags = "品牌REST API")
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
    @PostMapping("/brand/list/page/condition")
    @ResponseBody
    @ApiOperation(value = "分页条件查询品牌列表")
    public PageRspDto<List<BrandDto>> selectPageBrandByCondition(@RequestBody BrandDto brandDto,
        @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize)  {

        PageRspDto<List<BrandDto>> brandDtoList = brandService.selectPageBrandByCondition(brandDto, pageNum, pageSize);
        log.info("brandDtoList: {}, brandDto: {}, pageNum: {}, pageSize: {}", brandDtoList, brandDto, pageNum, pageSize);

        return brandDtoList;

    }

    /**
     * 插入品牌
     *
     * @param brandDto 品牌
     * @return 数据库ID
     */
    @PostMapping("/brand")
    @ResponseBody
    @ApiOperation(value = "插入品牌")
    public RspDto insertBrand(@RequestBody @Valid BrandDto brandDto) {
        RspDto<Long> id = brandService.insertBrand(brandDto);
        log.info("id: {}, brandDto: {}", id.getData(), brandDto);
        return id;
    }

    /**
     * 更新品牌
     *
     * @param modBrandVo 品牌
     * @return 响应
     */
    @PutMapping("/brand")
    @ResponseBody
    @ApiOperation(value = "更新品牌")
    public RspDto updateBrand(@RequestBody @Valid ModBrandVo modBrandVo) {
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
    @DeleteMapping("/brand/batch")
    @ResponseBody
    @ApiOperation(value = "批量删除品牌")
    public RspDto deleteBrand(Long[] ids) {
        return brandService.batchDeleteBrand(ids);
    }

    /**
     * 根据ID查询品牌
     *
     * @param id ID
     * @return 品牌
     */
    @GetMapping("/brand")
    @ResponseBody
    @ApiOperation(value = "根据ID查询品牌")
    public RspDto<BrandDto> selectBrandById(@RequestParam("id") @NotNull(message = "ID不能为空") Long id)  {

        RspDto<BrandDto> brandDto = brandService.selectBrandById(id);
        log.info("brandDto: {}, id: {}", brandDto, id);

        return brandDto;

    }

}
