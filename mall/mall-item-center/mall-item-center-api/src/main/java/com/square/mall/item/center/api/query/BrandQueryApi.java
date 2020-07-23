package com.square.mall.item.center.api.query;

import com.square.mall.common.dto.RspDto;
import com.square.mall.item.center.api.dto.BrandDto;

import java.util.List;

public interface BrandQueryApi {

    RspDto<List<BrandDto>> selectBrandByCondition(BrandDto brandDto);

    RspDto<List<BrandDto>> selectPageBrandByCondition(BrandDto brandDto, Integer pageIndex, Integer pageSize);

}
