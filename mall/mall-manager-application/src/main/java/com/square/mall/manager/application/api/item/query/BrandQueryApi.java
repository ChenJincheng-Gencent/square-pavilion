package com.square.mall.manager.application.api.item.query;

import com.square.mall.common.dto.PageRspDto;
import com.square.mall.common.dto.RspDto;
import com.square.mall.item.center.api.dto.BrandDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 品牌查询API
 *
 * @author Gencent
 * @date 2020/7/24
 */

@FeignClient(name="brand/query")
@Component
public interface BrandQueryApi {

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
