package com.square.mall.job.application.dao;

import com.square.mall.job.application.eo.BrandEo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 品牌Dao
 *
 * @author Gencent
 * @date 2020/7/24
 */
public interface BrandDao {

    /**
     * 插入品牌
     *
     * @param brandEo 品牌
     * @return 是否成功，1成功，0失败
     */
    int insertBrand(BrandEo brandEo);

    /**
     * 更新品牌
     *
     * @param brandEo 品牌
     * @return 是否成功，1成功，0失败
     */
    int updateBrand(BrandEo brandEo);

    /**
     * 删除品牌
     *
     * @param id 数据库ID
     * @return 是否成功，1成功，0失败
     */
    int deleteBrand(@Param("id") Long id);

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
     * @param brandEo 查询条件
     * @return 品牌列表
     */
    List<BrandEo> selectBrandByCondition(BrandEo brandEo);

    /**
     * 根据名称查询品牌
     *
     * @param name 名称
     * @return 品牌
     */
    BrandEo selectBrandByName(@Param("name") String name);

    /**
     * 根据ID查询品牌
     *
     * @param id ID
     * @return 品牌
     */
    BrandEo selectBrandById(@Param("id") Long id);

    /**
     * 查询所有品牌列表
     *
     * @return 品牌列表
     */
    List<BrandEo> selectBrandAll();

}
