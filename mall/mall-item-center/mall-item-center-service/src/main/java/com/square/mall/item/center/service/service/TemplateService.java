package com.square.mall.item.center.service.service;

import com.square.mall.common.dto.CommonPageRes;
import com.square.mall.item.center.api.dto.TemplateDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 模板Service
 *
 * @author Gencent
 * @date 2020/7/28
 */
public interface TemplateService {

    /**
     * 插入模板
     *
     * @param templateDto 模板
     * @return 是否成功，1成功，0失败
     */
    int insertTemplate(TemplateDto templateDto);

    /**
     * 更新模板
     *
     * @param templateDto 模板
     * @return 否成功，1成功，0失败
     */
    int updateTemplate(TemplateDto templateDto);

    /**
     * 删除模板
     *
     * @param id 数据库ID
     * @return 是否成功，1成功，0失败
     */
    int deleteTemplate(Long id);

    /**
     * 条件查询模板列表
     *
     * @param templateDto 查询条件
     * @return 模板列表
     */
    List<TemplateDto> selectTemplateByCondition(TemplateDto templateDto);

    /**
     * 分页条件查询模板列表
     *
     * @param templateDto 查询条件
     * @param pageNum 当前页
     * @param pageSize 分页大小
     * @return 模板列表
     */
    CommonPageRes<List<TemplateDto>> selectPageTemplateByCondition(TemplateDto templateDto, Integer pageNum, Integer pageSize);

    /**
     * 根据名称查询模板
     *
     * @param name 名称
     * @return 模板
     */
    TemplateDto selectTemplateByName(@Param("name") String name);

    /**
     * 根据ID查询模板
     *
     * @param id ID
     * @return 模板
     */
    TemplateDto selectTemplateById(@Param("id") Long id);
}
