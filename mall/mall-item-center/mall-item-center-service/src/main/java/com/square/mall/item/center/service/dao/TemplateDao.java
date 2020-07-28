package com.square.mall.item.center.service.dao;

import com.square.mall.item.center.service.eo.TemplateEo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 模板Dao
 *
 * @author Gencent
 * @date 2020/7/28
 */
public interface TemplateDao {

    /**
     * 插入模板
     *
     * @param templateEo 模板
     * @return 是否成功，1成功，0失败
     */
    int insertTemplate(TemplateEo templateEo);

    /**
     * 更新模板
     *
     * @param templateEo 模板
     * @return 是否成功，1成功，0失败
     */
    int updateTemplate(TemplateEo templateEo);

    /**
     * 删除模板
     *
     * @param id 数据库ID
     * @return 是否成功，1成功，0失败
     */
    int deleteTemplate(@Param("id") Long id);

    /**
     * 条件查询模板列表
     *
     * @param templateEo 查询条件
     * @return 模板列表
     */
    List<TemplateEo> selectTemplateByCondition(TemplateEo templateEo);

    /**
     * 根据名称查询模板
     *
     * @param name 名称
     * @return 模板
     */
    TemplateEo selectTemplateByName(@Param("name") String name);

    /**
     * 根据ID查询模板
     *
     * @param id ID
     * @return 模板
     */
    TemplateEo selectTemplateById(@Param("id") Long id);

}
