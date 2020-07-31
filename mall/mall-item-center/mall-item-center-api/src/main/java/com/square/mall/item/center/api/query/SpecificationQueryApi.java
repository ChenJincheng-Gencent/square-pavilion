package com.square.mall.item.center.api.query;

import com.square.mall.common.dto.PageRspDto;
import com.square.mall.common.dto.RspDto;
import com.square.mall.item.center.api.dto.SpecificationDto;
import com.square.mall.item.center.api.dto.SpecificationGroupDto;

import java.util.List;

/**
 * 规格查询API
 *
 * @author Gencent
 * @date 2020/7/27
 */
public interface SpecificationQueryApi {

    /**
     * 根据规格ID查询规格组合
     *
     * @param specId 规格ID
     * @return 规格组合
     */
    RspDto<SpecificationGroupDto> selectSpecificationGroupBySpecId(Long specId);

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
    RspDto<SpecificationDto> selectSpecificationById(Long id);

}
