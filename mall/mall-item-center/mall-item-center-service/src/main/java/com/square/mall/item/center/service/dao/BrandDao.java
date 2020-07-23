package com.square.mall.item.center.service.dao;

import com.square.mall.item.center.service.eo.BrandEo;

import java.util.List;

public interface BrandDao {

    Long insertBrand(BrandEo brandEo);

    void updateBrand(BrandEo brandEo);

    void deleteBrand(Long id);

    List<BrandEo> selectBrandByCondition(BrandEo brandEo);

}
