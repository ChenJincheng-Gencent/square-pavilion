package com.square.mall.item.center.service.apiimpl;

import com.square.mall.common.dto.RspDto;
import com.square.mall.common.util.DatabaseUtil;
import com.square.mall.common.util.ModuleConstant;
import com.square.mall.item.center.api.BrandApi;
import com.square.mall.item.center.api.dto.BrandDto;
import com.square.mall.item.center.service.service.BrandService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 品牌API实现类
 *
 * @author Gencent
 * @date 2020/7/23
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class BrandApiImpl implements BrandApi {

    @Resource
    private BrandService brandService;

    @Override
    public RspDto<Long> insertBrand(BrandDto brandDto) {
        int success = brandService.insertBrand(brandDto);
        return DatabaseUtil.getResult(success, brandDto.getId(), ModuleConstant.ITEM_CENTER);
    }

    @Override
    public RspDto updateBrand(BrandDto brandDto) {
        int success = brandService.updateBrand(brandDto);
        return DatabaseUtil.getResult(success, ModuleConstant.ITEM_CENTER);
    }

    @Override
    public RspDto deleteBrand(Long id) {
        int success = brandService.deleteBrand(id);
        return DatabaseUtil.getResult(success, ModuleConstant.ITEM_CENTER);
    }

    @Override
    public RspDto batchDeleteBrand(Long[] ids) {
        int success = brandService.batchDeleteBrand(ids);
        return DatabaseUtil.getResult(success, ModuleConstant.ITEM_CENTER);
    }
}
