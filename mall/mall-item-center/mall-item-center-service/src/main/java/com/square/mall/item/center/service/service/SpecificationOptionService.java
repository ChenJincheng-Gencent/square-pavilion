package com.square.mall.item.center.service.service;

import com.square.mall.item.center.api.dto.SpecificationDto;
import com.square.mall.item.center.api.dto.SpecificationOptionDto;

/**
 * 规格选项Service
 *
 * @author Gencent
 * @date 2020/7/27
 */
public interface SpecificationOptionService {

    /**
     * 插入规格选项
     *
     * @param specificationOptionDto 规格选项
     * @return 操作结果
     */
    int insertSpecificationOption(SpecificationOptionDto specificationOptionDto);

    int updateSpecificationOption(SpecificationOptionDto specificationOptionDto);

    int deleteSpecificationOption(Long id);

    int patchDeleteSpecificationOption(Long[] id);
}
