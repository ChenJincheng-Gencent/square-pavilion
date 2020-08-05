package com.square.mall.manager.application.service;

import com.square.mall.common.dto.PageRspDto;
import com.square.mall.common.dto.RspDto;
import com.square.mall.item.center.api.dto.SpecificationGroupDto;
import com.square.mall.item.center.api.dto.TemplateDto;
import com.square.mall.item.center.api.dto.TemplateGroupDto;

import java.util.List;

/**
 * 模板Service
 *
 * @author Gencent
 * @date 2020/7/31
 */
public interface TemplateService {

    /**
     * 插入模板组合
     *
     * @param templateGroupDto 模板组合
     * @return 数据库ID
     */
    RspDto<Long> insertTemplateGroup(TemplateGroupDto templateGroupDto);

    /**
     * 根据模板ID查询模板组合
     *
     * @param templateId 模板ID
     * @return 模板组合
     */
    RspDto<TemplateGroupDto> selectTemplateGroupByTemplateId(Long templateId);

    /**
     * 分页条件查询模板组合列表
     *
     * @param templateDto 查询条件
     * @param pageNum 当前页
     * @param pageSize 分页大小
     * @return 模板组合列表
     */
    PageRspDto<List<TemplateGroupDto>> selectPageTemplateGroupByCondition(TemplateDto templateDto, Integer pageNum,
        Integer pageSize);

    /**
     * 更新模板组合
     *
     * @param templateGroupDto 模板组合
     * @return 响应
     */
    RspDto updateTemplateGroup(TemplateGroupDto templateGroupDto);

    /**
     * 批量删除模板组合
     *
     * @param ids 模板ID数组
     * @return 响应
     */
    RspDto batchDeleteTemplateGroup(Long[] ids);


}
