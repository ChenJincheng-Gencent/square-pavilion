package com.square.mall.item.center.service.apiimpl.query;

import com.square.mall.common.dto.RspDto;
import com.square.mall.item.center.api.dto.SpecificationOptionDto;
import com.square.mall.item.center.api.query.SpecificationOptionQueryApi;
import com.square.mall.item.center.service.service.SpecificationOptionService;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.util.List;

/**
 * 规格选项查询API实现类
 *
 * @author Gencent
 * @date 2020/7/28
 */
@Slf4j

public class SpecificationOptionQueryApiImpl implements SpecificationOptionQueryApi {

    @Resource
    private SpecificationOptionService specificationOptionService;

    @Override
    public RspDto<List<SpecificationOptionDto>> selectSpecificationOptionBySpecId(Long specId) {
        return new RspDto<>(specificationOptionService.selectSpecificationOptionBySpecId(specId));
    }

}
