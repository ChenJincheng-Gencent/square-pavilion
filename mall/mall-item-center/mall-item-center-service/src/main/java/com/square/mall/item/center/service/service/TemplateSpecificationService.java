package com.square.mall.item.center.service.service;

import com.square.mall.common.dto.PageRspDto;
import com.square.mall.item.center.api.dto.TemplateSpecificationDto;

import java.util.List;

/**
 * 模板规格Service
 *
 * @author Gencent
 * @date 2020/7/28
 */
public interface TemplateSpecificationService {

    /**
     * 插入模板规格
     *
     * @param templateSpecificationDto 模板规格
     * @return 操作结果
     */
    int insertTemplateSpecification(TemplateSpecificationDto templateSpecificationDto);

    /**
     * 更新模板规格
     *
     * @param templateSpecificationDto 模板规格
     * @return 操作结果
     */
    int updateTemplateSpecification(TemplateSpecificationDto templateSpecificationDto);

    /**
     * 删除模板规格
     *
     * @param id ID
     * @return 操作结果
     */
    int deleteTemplateSpecification(Long id);

    /**
     * 条件查询模板规格列表
     * @param templateSpecificationDto 查询条件
     * @return 模板规格列表
     */
    List<TemplateSpecificationDto> selectTemplateSpecificationByCondition(TemplateSpecificationDto templateSpecificationDto);

    /**
     * 分页条件查询模板规格列表
     *
     * @param templateSpecificationDto 查询条件
     * @param pageNum 当前页
     * @param pageSize 分页大小
     * @return 模板规格列表
     */
    PageRspDto<List<TemplateSpecificationDto>> selectPageTemplateSpecificationByCondition(TemplateSpecificationDto templateSpecificationDto,
        Integer pageNum, Integer pageSize);

    /**
     * 根据模板ID查询规格ID列表
     *
     * @param templateId 模板ID
     * @return 规格ID列表
     */
    List<Long> selectSpecIdByTemplateId(Long templateId);

}
