package com.square.mall.manager.application.service;

import com.square.mall.common.dto.PageRspDto;
import com.square.mall.common.dto.RspDto;
import com.square.mall.item.center.api.dto.SpecificationDto;
import com.square.mall.item.center.api.dto.SpecificationGroupDto;

import java.util.List;

/**
 * 规格Service
 *
 * @author Gencent
 * @date 2020/7/30
 */
public interface SpecificationService {

    /**
     * 插入规格
     *
     * @param specificationGroupDto 规格
     * @return 数据库ID
     */
    RspDto<Long> insertSpecificationGroup(SpecificationGroupDto specificationGroupDto);

    /**
     * 更新规格
     *
     * @param specificationDto 规格
     * @return 响应
     */
    RspDto updateSpecification(SpecificationDto specificationDto);

    /**
     * 删除规格
     *
     * @param ids ID数组
     * @return 响应
     */
    RspDto batchDeleteSpecification(Long[] ids);

    /**
     * 条件查询规格
     *
     * @param specificationDto 查询条件
     * @return 规格列表
     */
    RspDto<List<SpecificationDto>> selectSpecificationByCondition(SpecificationDto specificationDto);

    /**
     * 分页条件查询规格列表
     *
     * @param specificationDto 查询条件
     * @param pageNum 当前页
     * @param pageSize 分页大小
     * @return 规格列表
     */
    PageRspDto<List<SpecificationDto>> selectPageSpecificationByCondition(SpecificationDto specificationDto, Integer pageNum, Integer pageSize);

    /**
     * 根据ID查询规格
     *
     * @param id ID
     * @return 规格
     */
    RspDto<SpecificationDto> selectSpecificationById(Long id);

    /**
     * 根据规格ID查询规格组合
     *
     * @param specId 规格ID
     * @return 规格组合
     */
    RspDto<SpecificationGroupDto> selectSpecificationGroupBySpecId(Long specId);

}
