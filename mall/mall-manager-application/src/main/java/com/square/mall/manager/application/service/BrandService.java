package com.square.mall.manager.application.service;

import com.square.mall.common.dto.CommonPageRes;
import com.square.mall.common.dto.CommonRes;
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
    CommonRes<Long> insertBrand(BrandDto brandDto);

    /**
     * 更新品牌
     *
     * @param brandDto 品牌
     * @return 响应
     */
    CommonRes<Void> updateBrand(BrandDto brandDto);

    /**
     * 删除品牌
     *
     * @param ids ID数组
     * @return 响应
     */
    CommonRes<Void> batchDeleteBrand(Long[] ids);

    /**
     * 条件查询品牌列表
     *
     * @param brandDto 查询条件
     * @return 品牌列表
     */
    CommonRes<List<BrandDto>> selectBrandByCondition(BrandDto brandDto);

    /**
     * 分页条件查询品牌列表
     *
     * @param brandDto 查询条件
     * @param pageNum 当前页
     * @param pageSize 分页大小
     * @return 品牌列表
     */
    CommonPageRes<List<BrandDto>> selectPageBrandByCondition(BrandDto brandDto, Integer pageNum, Integer pageSize);

    /**
     * 根据ID查询品牌
     *
     * @param id ID
     * @return 品牌
     */
    CommonRes<BrandDto> selectBrandById(Long id);

    /**
     * 查询所有品牌列表
     *
     * @return 品牌列表
     */
    CommonRes<List<BrandDto>> selectBrandAll();

}
