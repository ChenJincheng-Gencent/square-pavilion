package com.square.mall.item.center.service.service;

import com.square.mall.common.dto.PageRspDto;
import com.square.mall.item.center.api.dto.TemplateBrandDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 模板品牌Service
 *
 * @author Gencent
 * @date 2020/7/28
 */
public interface TemplateBrandService {

    /**
     * 插入模板品牌
     *
     * @param templateBrandDto 模板品牌
     * @return 是否成功，1成功，0失败
     */
    int insertTemplateBrand(TemplateBrandDto templateBrandDto);

    /**
     * 更新模板品牌
     *
     * @param templateBrandDto 模板品牌
     * @return 否成功，1成功，0失败
     */
    int updateTemplateBrand(TemplateBrandDto templateBrandDto);

    /**
     * 删除模板品牌
     *
     * @param id 数据库ID
     * @return 是否成功，1成功，0失败
     */
    int deleteTemplateBrand(Long id);

    /**
     * 条件查询模板品牌列表
     *
     * @param templateBrandDto 查询条件
     * @return 品牌数据列表
     */
    List<TemplateBrandDto> selectTemplateBrandByCondition(TemplateBrandDto templateBrandDto);

    /**
     * 分页条件查询模板品牌列表
     *
     * @param templateBrandDto 查询条件
     * @param pageNum 当前页
     * @param pageSize 分页大小
     * @return 模板品牌列表
     */
    PageRspDto<List<TemplateBrandDto>> selectPageTemplateBrandByCondition(TemplateBrandDto templateBrandDto, Integer pageNum, Integer pageSize);

    /**
     * 根据名称查询模板品牌
     *
     * @param name 名称
     * @return 模板品牌
     */
    TemplateBrandDto selectTemplateBrandByName(@Param("name") String name);

    /**
     * 根据ID查询模板品牌
     *
     * @param id ID
     * @return 模板品牌
     */
    TemplateBrandDto selectTemplateBrandById(@Param("id") Long id);
}
