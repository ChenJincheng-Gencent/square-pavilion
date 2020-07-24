package com.square.mall.item.center.service.apiimpl.query;

import com.square.mall.common.dto.RspDto;
import com.square.mall.item.center.api.dto.BrandDto;
import com.square.mall.item.center.api.query.BrandQueryApi;
import com.square.mall.item.center.service.service.BrandService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *  品牌查询API实现类
 *
 * @author Gencent
 * @date 2020/7/23
 */
@Slf4j
@Service
public class BrandQueryApiImpl implements BrandQueryApi {

    @Resource
    private BrandService brandService;

    @Override
    public RspDto<List<BrandDto>> selectBrandByCondition(BrandDto brandDto) {

        return new RspDto<>(brandService.selectBrandByCondition(brandDto));
    }

    @Override
    public RspDto<List<BrandDto>> selectPageBrandByCondition(BrandDto brandDto, Integer pageIndex, Integer pageSize) {
        return null;
    }
}