package com.square.mall.item.center.service.apiimpl;

import com.square.mall.common.dto.RspDto;
import com.square.mall.common.util.CommonConstant;
import com.square.mall.common.util.ErrorCode;
import com.square.mall.item.center.api.BrandApi;
import com.square.mall.item.center.api.dto.BrandDto;
import com.square.mall.item.center.service.service.BrandService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;

/**
 *  品牌API实现类
 *
 * @author Gencent
 * @date 2020/7/23
 */
@Slf4j
@Service
public class BrandApiImpl implements BrandApi {

    @Resource
    private BrandService brandService;

    @Override
    public RspDto<Long> insertBrand(BrandDto brandDto) {
        int success = brandService.insertBrand(brandDto);
        if (CommonConstant.DATABASE_OPT_SUCCESS == success) {
            return new RspDto<>(brandDto.getId());
        }
        return new RspDto<>(ErrorCode.IT_CEN_DATABASE_OPT_FAILED);
    }

    @Override
    public RspDto updateBrand(BrandDto brandDto) {
        int success = brandService.updateBrand(brandDto);
        if (CommonConstant.DATABASE_OPT_SUCCESS == success) {
            return RspDto.SUCCESS;
        }
        return new RspDto<>(ErrorCode.IT_CEN_DATABASE_OPT_FAILED);
    }

    @Override
    public RspDto deleteBrand(Long id) {
        int success = brandService.deleteBrand(id);
        if (CommonConstant.DATABASE_OPT_SUCCESS == success) {
            return RspDto.SUCCESS;
        }
        return new RspDto<>(ErrorCode.IT_CEN_DATABASE_OPT_FAILED);
    }
}
