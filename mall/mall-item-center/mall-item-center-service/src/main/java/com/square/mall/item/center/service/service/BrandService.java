package com.square.mall.item.center.service.service;

import com.square.mall.item.center.api.dto.BrandDto;
import com.square.mall.item.center.service.eo.BrandEo;

import java.util.List;

public interface BrandService {

    Long insertBrand(BrandEo brandEo);

    void updateBrand(BrandEo brandEo);

    void deleteBrand(Long id);

    List<BrandDto> selectBrandByCondition(BrandDto brandDto);
}
