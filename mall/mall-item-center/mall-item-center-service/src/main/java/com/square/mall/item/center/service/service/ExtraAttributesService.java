package com.square.mall.item.center.service.service;

import com.square.mall.common.dto.PageRspDto;
import com.square.mall.item.center.api.dto.BrandDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 品牌Service
 *
 * @author Gencent
 * @date 2020/7/24
 */
public interface ExtraAttributesService {

    /**
     * 插入品牌数据
     *
     * @param brandDto 品牌
     * @return 是否成功，1成功，0失败
     */
    int insertBrand(BrandDto brandDto);

    /**
     * 更新品牌数据
     *
     * @param brandDto 品牌
     * @return 否成功，1成功，0失败
     */
    int updateBrand(BrandDto brandDto);

    /**
     * 删除品牌数据
     *
     * @param id 数据库ID
     * @return 是否成功，1成功，0失败
     */
    int deleteBrand(Long id);

    /**
     * 条件查询品牌数据列表
     *
     * @param brandDto 查询条件
     * @return 品牌数据列表
     */
    List<BrandDto> selectBrandByCondition(BrandDto brandDto);

    /**
     * 分页条件查询品牌数据列表
     *
     * @param brandDto 查询条件
     * @param pageNum 当前页
     * @param pageSize 分页大小
     * @return 品牌数据列表
     */
    PageRspDto<List<BrandDto>> selectPageBrandByCondition(BrandDto brandDto, Integer pageNum, Integer pageSize);

    /**
     * 根据名称查询品牌
     *
     * @param name 名称
     * @return 品牌
     */
    BrandDto selectBrandByName(@Param("name") String name);

    /**
     * 根据ID查询品牌
     *
     * @param id ID
     * @return 品牌
     */
    BrandDto selectBrandById(@Param("id") Long id);
}
