package com.square.mall.item.center.service.service;

import com.square.mall.common.dto.PageRspDto;
import com.square.mall.item.center.api.dto.SpecificationDto;

import java.util.List;

/**
 * 规格Service
 *
 * @author Gencent
 * @date 2020/7/27
 */
public interface SpecificationService {

    /**
     * 插入规格
     *
     * @param specificationDto 规格
     * @return 操作结果
     */
    int insertSpecification(SpecificationDto specificationDto);

    /**
     * 更新规格
     *
     * @param specificationDto 规格
     * @return 操作结果
     */
    int updateSpecification(SpecificationDto specificationDto);

    /**
     * 删除规格
     *
     * @param id ID
     * @return 操作结果
     */
    int deleteSpecification(Long id);

    /**
     * 条件查询规格列表
     * @param specificationDto 查询条件
     * @return 规格列表
     */
    List<SpecificationDto> selectSpecificationByCondition(SpecificationDto specificationDto);

    /**
     * 分页条件查询规格列表
     *
     * @param specificationDto 查询条件
     * @param pageNum 当前页
     * @param pageSize 分页大小
     * @return 规格列表
     */
    PageRspDto<List<SpecificationDto>> selectPageSpecificationByCondition(SpecificationDto specificationDto,
        Integer pageNum, Integer pageSize);

    /**
     * 根据ID查询规格
     *
     * @param id ID
     * @return 规格
     */
    SpecificationDto selectSpecificationById(Long id);

}
