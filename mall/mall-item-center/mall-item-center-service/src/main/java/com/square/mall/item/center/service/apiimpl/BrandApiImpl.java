package com.square.mall.item.center.service.apiimpl;

import com.square.mall.common.dto.RspDto;
import com.square.mall.item.center.api.BrandApi;
import com.square.mall.item.center.api.dto.BrandDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

/**
 *  品牌API实现类
 *
 * @author Gencent
 * @date 2020/7/23
 */
@Slf4j
@Service
public class BrandApiImpl implements BrandApi {
    @Override
    public RspDto<Long> insertBrand(BrandDto brandDto) {
        return null;
    }

    @Override
    public RspDto updateBrand(BrandDto brandDto) {
        return null;
    }

    @Override
    public RspDto deleteBrand(Long id) {
        return null;
    }
}
