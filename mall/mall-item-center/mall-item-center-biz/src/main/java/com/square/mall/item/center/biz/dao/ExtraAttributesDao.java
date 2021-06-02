package com.square.mall.item.center.biz.dao;

import com.square.mall.item.center.biz.eo.ExtraAttributesEo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 扩展属性Dao
 *
 * @author Gencent
 * @date 2020/7/28
 */
public interface ExtraAttributesDao {

    /**
     * 插入扩展属性
     *
     * @param extraAttributesEo 扩展属性
     * @return 是否成功，1成功，0失败
     */
    int insertExtraAttributes(ExtraAttributesEo extraAttributesEo);

    /**
     * 更新扩展属性
     *
     * @param extraAttributesEo 扩展属性
     * @return 是否成功，1成功，0失败
     */
    int updateExtraAttributes(ExtraAttributesEo extraAttributesEo);

    /**
     * 根据模板ID删除扩展属性
     *
     * @param templateId 模板ID
     * @return 是否成功，1成功，0失败
     */
    int deleteExtraAttributesByTemplateId(@Param("templateId") Long templateId);

    /**
     * 条件查询扩展属性列表
     *
     * @param extraAttributesEo 查询条件
     * @return 扩展属性列表
     */
    List<ExtraAttributesEo> selectExtraAttributesByCondition(ExtraAttributesEo extraAttributesEo);

    /**
     * 根据名称查询扩展属性
     *
     * @param name 名称
     * @return 扩展属性
     */
    ExtraAttributesEo selectExtraAttributesByName(@Param("name") String name);


    /**
     * 根据模板ID查询扩展属性列表
     *
     * @param templateId 模板ID
     * @return 扩展属性列表
     */
    List<ExtraAttributesEo> selectExtraAttributesByTemplateId(@Param("templateId") Long templateId);

}
