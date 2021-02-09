package com.square.mall.manager.application.service.impl;

import com.square.mall.common.dto.PageRspDto;
import com.square.mall.common.dto.RspDto;
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
    public RspDto<Long> insertBrand(BrandDto brandDto) {
        return brandApi.insertBrand(brandDto);
    }

    @Override
    public RspDto updateBrand(BrandDto brandDto) {
        return brandApi.updateBrand(brandDto);
    }

    @Override
    public RspDto batchDeleteBrand(Long[] ids) {
        return brandApi.batchDeleteBrand(ids);
    }

    @Override
    public RspDto<List<BrandDto>> selectBrandByCondition(BrandDto brandDto) {
        return brandApi.selectBrandByCondition(brandDto);
    }

    @Override
    public PageRspDto<List<BrandDto>> selectPageBrandByCondition(BrandDto brandDto, Integer pageNum, Integer pageSize) {
        return brandApi.selectPageBrandByCondition(brandDto, pageNum, pageSize);
    }

    @Override
    public RspDto<BrandDto> selectBrandById(Long id) {
        return brandApi.selectBrandById(id);
    }

    @Override
    public RspDto<List<BrandDto>> selectBrandAll() {
        return brandApi.selectBrandAll();
    }
}
