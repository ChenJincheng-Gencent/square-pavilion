package com.square.mall.item.center.api;

import com.square.mall.common.dto.RspDto;
import com.square.mall.item.center.api.dto.BrandDto;

public interface BrandApi {

    RspDto<Long> insertBrand(BrandDto brandDto);

    RspDto updateBrand(BrandDto brandDto);

    RspDto deleteBrand(Long id);
}
