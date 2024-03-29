package com.square.mall.item.center.biz.dao;

import com.square.mall.item.center.biz.eo.TemplateSpecificationEo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 模板规格Dao
 *
 * @author Gencent
 * @date 2020/7/28
 */
public interface TemplateSpecificationDao {

    /**
     * 插入模板规格
     *
     * @param templateSpecificationEo 模板规格
     * @return 是否成功，1成功，0失败
     */
    int insertTemplateSpecification(TemplateSpecificationEo templateSpecificationEo);

    /**
     * 更新模板规格
     *
     * @param templateSpecificationEo 模板规格
     * @return 是否成功，1成功，0失败
     */
    int updateTemplateSpecification(TemplateSpecificationEo templateSpecificationEo);

    /**
     * 根据模板ID删除模板规格
     *
     * @param templateId 模板ID
     * @return 影响行数
     */
    int deleteTemplateSpecificationByTemplateId(@Param("templateId") Long templateId);

    /**
     * 条件查询模板规格列表
     *
     * @param templateSpecificationEo 查询条件
     * @return 模板规格列表
     */
    List<TemplateSpecificationEo> selectTemplateSpecificationByCondition(TemplateSpecificationEo templateSpecificationEo);

    /**
     * 根据模板ID查询规格ID列表
     *
     * @param templateId 模板ID
     * @return 规格ID列表
     */
    List<Long> selectSpecIdByTemplateId(@Param("templateId") Long templateId);

}
