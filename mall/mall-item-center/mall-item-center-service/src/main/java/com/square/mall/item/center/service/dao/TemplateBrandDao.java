package com.square.mall.item.center.service.dao;

import com.square.mall.item.center.service.eo.BrandEo;
import com.square.mall.item.center.service.eo.TemplateBrandEo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 模板品牌Dao
 *
 * @author Gencent
 * @date 2020/7/28
 */
public interface TemplateBrandDao {

    /**
     * 插入模板品牌
     *
     * @param templateBrandEo 模板品牌
     * @return 是否成功，1成功，0失败
     */
    int insertTemplateBrand(TemplateBrandEo templateBrandEo);

    /**
     * 更新模板品牌
     *
     * @param templateBrandEo 模板品牌
     * @return 是否成功，1成功，0失败
     */
    int updateTemplateBrand(TemplateBrandEo templateBrandEo);

    /**
     * 删除模板品牌
     *
     * @param id 数据库ID
     * @return 是否成功，1成功，0失败
     */
    int deleteTemplateBrand(@Param("id") Long id);

    /**
     * 条件查询模板品牌列表
     *
     * @param templateBrandEo 查询条件
     * @return 模板品牌列表
     */
    List<TemplateBrandEo> selectTemplateBrandByCondition(TemplateBrandEo templateBrandEo);

    /**
     * 根据ID查询模板品牌
     *
     * @param id ID
     * @return 模板品牌
     */
    BrandEo selectTemplateBrandById(@Param("id") Long id);

}
