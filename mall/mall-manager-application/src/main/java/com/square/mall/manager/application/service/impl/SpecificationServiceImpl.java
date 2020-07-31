package com.square.mall.manager.application.service.impl;

import com.square.mall.common.dto.PageRspDto;
import com.square.mall.common.dto.RspDto;
import com.square.mall.item.center.api.SpecificationApi;
import com.square.mall.item.center.api.dto.SpecificationDto;
import com.square.mall.item.center.api.dto.SpecificationGroupDto;
import com.square.mall.item.center.api.query.SpecificationQueryApi;
import com.square.mall.manager.application.service.SpecificationService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 规格Service实现类
 *
 * @author Gencent
 * @date 2020/7/30
 */
@Service
public class SpecificationServiceImpl implements SpecificationService {

    @Reference
    private SpecificationApi specificationApi;

    @Reference
    private SpecificationQueryApi specificationQueryApi;

    @Override
    public RspDto<Long> insertSpecificationGroup(SpecificationGroupDto specificationGroupDto) {
        return specificationApi.insertSpecificationGroup(specificationGroupDto);
    }

    @Override
    public RspDto updateSpecification(SpecificationDto specificationDto) {
        return null;
        //return specificationApi.updateSpecification(specificationDto);
    }

    @Override
    public RspDto batchDeleteSpecification(Long[] ids) {
        return null;
        //return specificationApi.batchDeleteSpecification(ids);
    }

    @Override
    public RspDto<List<SpecificationDto>> selectSpecificationByCondition(SpecificationDto specificationDto) {
        return null;
        //return specificationQueryApi.selectSpecificationByCondition(specificationDto);
    }

    @Override
    public PageRspDto<List<SpecificationDto>> selectPageSpecificationByCondition(SpecificationDto specificationDto, Integer pageNum, Integer pageSize) {
        return specificationQueryApi.selectPageSpecificationByCondition(specificationDto, pageNum, pageSize);
    }

    @Override
    public RspDto<SpecificationDto> selectSpecificationById(Long id) {
        return specificationQueryApi.selectSpecificationById(id);
    }

    @Override
    public RspDto<SpecificationGroupDto> selectSpecificationGroupBySpecId(Long specId) {
        return specificationQueryApi.selectSpecificationGroupBySpecId(specId);
    }
}
