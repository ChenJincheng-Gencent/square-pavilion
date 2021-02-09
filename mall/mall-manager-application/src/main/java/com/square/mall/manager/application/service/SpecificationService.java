package com.square.mall.manager.application.service;

import com.square.mall.common.dto.CommonPageRes;
import com.square.mall.common.dto.CommonRes;
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
    CommonRes<Long> insertSpecificationGroup(SpecificationGroupDto specificationGroupDto);

    /**
     * 更新规格组合
     *
     * @param specificationGroupDto 规格组合
     * @return 响应
     */
    CommonRes<Void> updateSpecificationGroup(SpecificationGroupDto specificationGroupDto);

    /**
     * 删除规格组合
     *
     * @param ids 规格ID数组
     * @return 响应
     */
    CommonRes<Void> batchDeleteSpecificationGroup(Long[] ids);

    /**
     * 条件查询规格
     *
     * @param specificationDto 查询条件
     * @return 规格列表
     */
    CommonRes<List<SpecificationDto>> selectSpecificationByCondition(SpecificationDto specificationDto);

    /**
     * 分页条件查询规格列表
     *
     * @param specificationDto 查询条件
     * @param pageNum 当前页
     * @param pageSize 分页大小
     * @return 规格列表
     */
    CommonPageRes<List<SpecificationDto>> selectPageSpecificationByCondition(SpecificationDto specificationDto, Integer pageNum, Integer pageSize);

    /**
     * 根据ID查询规格
     *
     * @param id ID
     * @return 规格
     */
    CommonRes<SpecificationDto> selectSpecificationById(Long id);

    /**
     * 根据规格ID查询规格组合
     *
     * @param specId 规格ID
     * @return 规格组合
     */
    CommonRes<SpecificationGroupDto> selectSpecificationGroupBySpecId(Long specId);

    /**
     * 查询所有规格列表
     *
     * @return 规格列表
     */
    CommonRes<List<SpecificationDto>> selectSpecificationAll();

}
