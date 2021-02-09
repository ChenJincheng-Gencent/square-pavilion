package com.square.mall.item.center.service.service;

import com.square.mall.common.dto.CommonPageRes;
import com.square.mall.item.center.api.dto.ExtraAttributesDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 扩展属性Service
 *
 * @author Gencent
 * @date 2020/7/28
 */
public interface ExtraAttributesService {

    /**
     * 插入扩展属性
     *
     * @param extraAttributesDto 扩展属性
     * @return 是否成功，1成功，0失败
     */
    int insertExtraAttributes(ExtraAttributesDto extraAttributesDto);

    /**
     * 更新扩展属性
     *
     * @param extraAttributesDto 扩展属性
     * @return 否成功，1成功，0失败
     */
    int updateExtraAttributes(ExtraAttributesDto extraAttributesDto);

    /**
     * 根据模板ID删除扩展属性
     *
     * @param templateId 模板ID
     * @return 是否成功，1成功，0失败
     */
    int deleteExtraAttributesByTemplateId(Long templateId);

    /**
     * 条件查询扩展属性列表
     *
     * @param extraAttributesDto 查询条件
     * @return 扩展属性列表
     */
    List<ExtraAttributesDto> selectExtraAttributesByCondition(ExtraAttributesDto extraAttributesDto);

    /**
     * 分页条件查询扩展属性列表
     *
     * @param extraAttributesDto 查询条件
     * @param pageNum 当前页
     * @param pageSize 分页大小
     * @return 扩展属性列表
     */
    CommonPageRes<List<ExtraAttributesDto>> selectPageExtraAttributesByCondition(ExtraAttributesDto extraAttributesDto, Integer pageNum, Integer pageSize);

    /**
     * 根据名称查询扩展属性
     *
     * @param name 名称
     * @return 扩展属性
     */
    ExtraAttributesDto selectExtraAttributesByName(@Param("name") String name);

    /**
     * 根据模板ID查询扩展属性列表
     *
     * @param templateId 模板ID
     * @return 扩展属性列表
     */
    List<ExtraAttributesDto> selectExtraAttributesByTemplateId(Long templateId);
}
