package com.square.mall.item.center.service.service;

import com.square.mall.item.center.api.dto.SpecificationDto;

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

    int updateSpecification(SpecificationDto specificationDto);

    int deleteSpecification(Long id);
}
