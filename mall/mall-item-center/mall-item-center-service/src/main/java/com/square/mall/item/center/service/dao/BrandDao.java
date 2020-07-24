package com.square.mall.item.center.service.dao;

import com.square.mall.item.center.service.eo.BrandEo;

import java.util.List;

/**
 * 品牌dao
 *
 * @author Gencent
 * @date 2020/7/24
 */
public interface BrandDao {

    /**
     * 插入品牌数据
     * @param brandEo 品牌
     * @return 是否成功，1成功，0失败
     */
    int insertBrand(BrandEo brandEo);

    /**
     * 更新品牌数据
     * @param brandEo 品牌
     * @return 是否成功，1成功，0失败
     */
    int updateBrand(BrandEo brandEo);

    /**
     * 删除品牌数据
     * @param id 数据库ID
     * @return 是否成功，1成功，0失败
     */
    int deleteBrand(Long id);

    /**
     * 条件查询品牌数据列表
     * @param brandEo 查询条件
     * @return 品牌数据列表
     */
    List<BrandEo> selectBrandByCondition(BrandEo brandEo);

}
