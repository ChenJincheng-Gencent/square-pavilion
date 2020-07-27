package com.square.mall.item.center.service.service;

import com.square.mall.item.center.api.dto.SpecificationDto;
import com.square.mall.item.center.api.dto.SpecificationOptionDto;

public interface SpecificationOptionService {

    int insertSpecificationOption(SpecificationOptionDto specificationOptionDto);

    int updateSpecificationOption(SpecificationOptionDto specificationOptionDto);

    int deleteSpecificationOption(Long id);

    int patchDeleteSpecificationOption(Long[] id);
}
