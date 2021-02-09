package com.square.mall.manager.application.service.impl;

import com.square.mall.common.dto.CommonPageRes;
import com.square.mall.common.dto.CommonRes;
import com.square.mall.item.center.api.SpecificationApi;
import com.square.mall.item.center.api.dto.SpecificationDto;
import com.square.mall.item.center.api.dto.SpecificationGroupDto;
import com.square.mall.manager.application.service.SpecificationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 规格Service实现类
 *
 * @author Gencent
 * @date 2020/7/30
 */
@Service
public class SpecificationServiceImpl implements SpecificationService {

    @Resource
    private SpecificationApi specificationApi;

    @Override
    public CommonRes<Long> insertSpecificationGroup(SpecificationGroupDto specificationGroupDto) {
        return specificationApi.insertSpecificationGroup(specificationGroupDto);
    }

    @Override
    public CommonRes<Void> updateSpecificationGroup(SpecificationGroupDto specificationGroupDto) {
        return specificationApi.updateSpecificationGroup(specificationGroupDto);
    }

    @Override
    public CommonRes<Void> batchDeleteSpecificationGroup(Long[] ids) {
        return specificationApi.batchDeleteSpecificationGroup(ids);
    }

    @Override
    public CommonRes<List<SpecificationDto>> selectSpecificationByCondition(SpecificationDto specificationDto) {
        return specificationApi.selectSpecificationByCondition(specificationDto);
    }

    @Override
    public CommonPageRes<List<SpecificationDto>> selectPageSpecificationByCondition(SpecificationDto specificationDto,
                                                                                    Integer pageNum, Integer pageSize) {
        return specificationApi.selectPageSpecificationByCondition(specificationDto, pageNum, pageSize);
    }

    @Override
    public CommonRes<SpecificationDto> selectSpecificationById(Long id) {
        return specificationApi.selectSpecificationById(id);
    }

    @Override
    public CommonRes<SpecificationGroupDto> selectSpecificationGroupBySpecId(Long specId) {
        return specificationApi.selectSpecificationGroupBySpecId(specId);
    }

    @Override
    public CommonRes<List<SpecificationDto>> selectSpecificationAll() {
        return specificationApi.selectSpecificationAll();
    }
}
