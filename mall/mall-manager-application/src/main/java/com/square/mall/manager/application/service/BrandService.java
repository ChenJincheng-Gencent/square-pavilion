package com.square.mall.manager.application.service;

import com.square.mall.common.dto.PageRspDto;
import com.square.mall.common.dto.RspDto;
import com.square.mall.item.center.api.dto.BrandDto;

import java.util.List;

/**
 * 品牌Service
 *
 * @author Gencent
 * @date 2020/7/24
 */
public interface BrandService {

    /**
     * 插入品牌
     *
     * @param brandDto 品牌
     * @return 数据库ID
     */
    RspDto<Long> insertBrand(BrandDto brandDto);

    /**
     * 更新品牌
     *
     * @param brandDto 品牌
     * @return 响应
     */
    RspDto updateBrand(BrandDto brandDto);

    /**
     * 删除品牌
     *
     * @param ids ID数组
     * @return 响应
     */
    RspDto batchDeleteBrand(Long[] ids);

    /**
     * 条件查询品牌列表
     *
     * @param brandDto 查询条件
     * @return 品牌列表
     */
    RspDto<List<BrandDto>> selectBrandByCondition(BrandDto brandDto);

    /**
     * 分页条件查询品牌列表
     *
     * @param brandDto 查询条件
     * @param pageNum 当前页
     * @param pageSize 分页大小
     * @return 品牌列表
     */
    PageRspDto<List<BrandDto>> selectPageBrandByCondition(BrandDto brandDto, Integer pageNum, Integer pageSize);

    /**
     * 根据ID查询品牌
     *
     * @param id ID
     * @return 品牌
     */
    RspDto<BrandDto> selectBrandById(Long id);

}
