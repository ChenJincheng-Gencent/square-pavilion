package com.square.mall.item.center.service.service;

import com.square.mall.common.dto.PageRspDto;
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
     * @return 是否成功，1成功，0失败
     */
    int insertBrand(BrandDto brandDto);

    /**
     * 更新品牌
     *
     * @param brandDto 品牌
     * @return 否成功，1成功，0失败
     */
    int updateBrand(BrandDto brandDto);

    /**
     * 删除品牌
     *
     * @param id 数据库ID
     * @return 是否成功，1成功，0失败
     */
    int deleteBrand(Long id);

    /**
     * 批量删除品牌
     *
     * @param ids ID数组
     * @return 响应
     */
    int batchDeleteBrand(Long[] ids);

    /**
     * 条件查询品牌列表
     *
     * @param brandDto 查询条件
     * @return 品牌列表
     */
    List<BrandDto> selectBrandByCondition(BrandDto brandDto);

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
     * 根据名称查询品牌
     *
     * @param name 名称
     * @return 品牌
     */
    BrandDto selectBrandByName(String name);

    /**
     * 根据ID查询品牌
     *
     * @param id ID
     * @return 品牌
     */
    BrandDto selectBrandById(Long id);
}
