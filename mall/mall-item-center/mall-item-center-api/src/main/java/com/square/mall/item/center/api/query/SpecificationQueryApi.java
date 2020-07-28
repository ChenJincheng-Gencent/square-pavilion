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
     * 条件查询规格组合
     *
     * @param specificationDto 查询条件
     * @return 规格组合
     */
    RspDto<List<SpecificationGroupDto>> selectSpecificationGroup(SpecificationDto specificationDto);

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

}
