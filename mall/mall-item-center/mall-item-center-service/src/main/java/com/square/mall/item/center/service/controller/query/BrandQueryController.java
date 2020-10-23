package com.square.mall.item.center.service.controller.query;

import com.square.mall.common.dto.PageRspDto;
import com.square.mall.common.dto.RspDto;
import com.square.mall.item.center.api.dto.BrandDto;
import com.square.mall.item.center.service.service.BrandService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandQueryController {

    @Resource
    private BrandService brandService;

    /**
     * 条件查询品牌列表
     *
     * @param brandDto 查询条件
     * @return 品牌数据列表
     */
    @PostMapping("/list/condition")
    public RspDto<List<BrandDto>> selectBrandByCondition(@RequestBody BrandDto brandDto) {
        return new RspDto<>(brandService.selectBrandByCondition(brandDto));
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
    public PageRspDto<List<BrandDto>> selectPageBrandByCondition(@RequestBody BrandDto brandDto, @RequestParam("pageNum") Integer pageNum,
                                                                 @RequestParam("pageSize") Integer pageSize) {
        return brandService.selectPageBrandByCondition(brandDto, pageNum, pageSize);
    }


    /**
     * 根据ID查询品牌
     * @param id ID
     * @return 品牌
     */
    @GetMapping("/id")
    public RspDto<BrandDto> selectBrandById(@RequestParam("id") Long id) {
        return new RspDto<>(brandService.selectBrandById(id));
    }


    /**
     * 查询所有品牌列表
     *
     * @return 品牌列表
     */
    @GetMapping("/all")
    public RspDto<List<BrandDto>> selectBrandAll() {
        return new RspDto<>(brandService.selectBrandAll());
    }

}
