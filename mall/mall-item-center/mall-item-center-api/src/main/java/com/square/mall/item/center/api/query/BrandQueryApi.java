package com.square.mall.item.center.api.query;

import com.square.mall.common.dto.PageRspDto;
import com.square.mall.common.dto.RspDto;
import com.square.mall.item.center.api.dto.BrandDto;

import java.util.List;

/**
 * 品牌查询API
 *
 * @author Gencent
 * @date 2020/7/24
 */
public interface BrandQueryApi {

    /**
     * 条件查询品牌数据列表
     * @param brandDto 查询条件
     * @return 品牌数据列表
     */
    RspDto<List<BrandDto>> selectBrandByCondition(BrandDto brandDto);

    /**
     * 分页条件查询品牌数据列表
     * @param brandDto 查询条件
     * @param pageNum 当前页
     * @param pageSize 分页大小
     * @return 品牌数据列表
     */
    PageRspDto<List<BrandDto>> selectPageBrandByCondition(BrandDto brandDto, Integer pageNum, Integer pageSize);

}
