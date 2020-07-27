package com.square.mall.item.center.api.query;

import com.square.mall.common.dto.RspDto;
import com.square.mall.item.center.api.dto.SpecificationDto;
import com.square.mall.item.center.api.dto.SpecificationGroupDto;

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
    RspDto<SpecificationGroupDto> selectSpecificationGroup(SpecificationDto specificationDto);

}
