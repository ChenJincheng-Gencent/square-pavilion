package com.square.mall.manager.application.service.impl;

import com.square.mall.common.dto.CommonPageRes;
import com.square.mall.common.dto.CommonRes;
import com.square.mall.item.center.api.BrandApi;
import com.square.mall.item.center.api.dto.BrandDto;
import com.square.mall.manager.application.service.BrandService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 品牌Service实现类
 *
 * @author Gencent
 * @date 2020/7/24
 */
@Service
public class BrandServiceImpl implements BrandService {

    @Resource
    private BrandApi brandApi;

    @Override
    public CommonRes<Long> insertBrand(BrandDto brandDto) {
        return brandApi.insertBrand(brandDto);
    }

    @Override
    public CommonRes<Void> updateBrand(BrandDto brandDto) {
        return brandApi.updateBrand(brandDto);
    }

    @Override
    public CommonRes<Void> batchDeleteBrand(Long[] ids) {
        return brandApi.batchDeleteBrand(ids);
    }

    @Override
    public CommonRes<List<BrandDto>> selectBrandByCondition(BrandDto brandDto) {
        return brandApi.selectBrandByCondition(brandDto);
    }

    @Override
    public CommonPageRes<List<BrandDto>> selectPageBrandByCondition(BrandDto brandDto, Integer pageNum, Integer pageSize) {
        return brandApi.selectPageBrandByCondition(brandDto, pageNum, pageSize);
    }

    @Override
    public CommonRes<BrandDto> selectBrandById(Long id) {
        return brandApi.selectBrandById(id);
    }

    @Override
    public CommonRes<List<BrandDto>> selectBrandAll() {
        return brandApi.selectBrandAll();
    }
}
