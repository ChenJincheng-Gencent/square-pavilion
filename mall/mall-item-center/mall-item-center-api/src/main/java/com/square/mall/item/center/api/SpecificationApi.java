package com.square.mall.item.center.api;

import com.square.mall.common.dto.RspDto;
import com.square.mall.item.center.api.dto.SpecificationDto;
import com.square.mall.item.center.api.dto.SpecificationGroupDto;

/**
 * 规格API
 *
 * @author Gencent
 * @date 2020/7/27
 */
public interface SpecificationApi {

    /**
     * 插入规格组合
     *
     * @param specificationGroupDto 规格组合
     * @return 数据库ID
     */
    RspDto<Long> insertSpecificationGroup(SpecificationGroupDto specificationGroupDto);

    /**
     * 更新规格组合
     *
     * @param specificationGroupDto 规格组合
     * @return 响应
     */
    RspDto updateSpecificationGroup(SpecificationGroupDto specificationGroupDto);

    /**
     * 批量删除规格组合
     *
     * @param ids 规格ID数组
     * @return 响应
     */
    RspDto batchDeleteSpecificationGroup(Long[] ids);

}
