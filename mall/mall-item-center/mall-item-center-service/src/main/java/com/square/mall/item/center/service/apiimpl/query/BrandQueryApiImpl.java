package com.square.mall.item.center.service.apiimpl.query;

import com.square.mall.common.dto.RspDto;
import com.square.mall.item.center.api.dto.BrandDto;
import com.square.mall.item.center.api.query.BrandQueryApi;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

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
    @Override
    public RspDto<List<BrandDto>> selectBrandByCondition(BrandDto brandDto) {
        return null;
    }

    @Override
    public RspDto<List<BrandDto>> selectPageBrandByCondition(BrandDto brandDto, Integer pageIndex, Integer pageSize) {
        return null;
    }
}
