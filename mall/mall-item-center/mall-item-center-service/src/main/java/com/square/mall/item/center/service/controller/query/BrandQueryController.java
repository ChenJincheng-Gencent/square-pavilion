package com.square.mall.item.center.service.controller.query;

import com.square.mall.common.dto.PageRspDto;
import com.square.mall.common.dto.RspDto;
import com.square.mall.item.center.api.dto.BrandDto;
import com.square.mall.item.center.service.service.BrandService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/brand/query")
public class BrandQueryController {

    @Resource
    private BrandService brandService;

    /**
     * 条件查询品牌列表
     *
     * @param brandDto 查询条件
     * @return 品牌数据列表
     */
    @PostMapping("/brand/list/condition")
    public RspDto<List<BrandDto>> selectBrandByCondition(BrandDto brandDto) {
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
    @PostMapping("/brand/list/page/condition")
    public PageRspDto<List<BrandDto>> selectPageBrandByCondition(BrandDto brandDto, Integer pageNum, Integer pageSize) {
        return brandService.selectPageBrandByCondition(brandDto, pageNum, pageSize);
    }


    /**
     * 根据ID查询品牌
     * @param id ID
     * @return 品牌
     */
    @GetMapping("/brand/id")
    public RspDto<BrandDto> selectBrandById(Long id) {
        return new RspDto<>(brandService.selectBrandById(id));
    }


    /**
     * 查询所有品牌列表
     *
     * @return 品牌列表
     */
    @GetMapping("/brand/all")
    public RspDto<List<BrandDto>> selectBrandAll() {
        return new RspDto<>(brandService.selectBrandAll());
    }

}
