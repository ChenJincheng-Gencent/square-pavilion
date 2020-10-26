package com.square.mall.manager.application.service.impl;

import com.square.mall.common.dto.PageRspDto;
import com.square.mall.common.dto.RspDto;
import com.square.mall.item.center.api.dto.SpecificationDto;
import com.square.mall.item.center.api.dto.SpecificationGroupDto;
import com.square.mall.manager.application.api.item.SpecificationApi;
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
    public RspDto<Long> insertSpecificationGroup(SpecificationGroupDto specificationGroupDto) {
        return specificationApi.insertSpecificationGroup(specificationGroupDto);
    }

    @Override
    public RspDto updateSpecificationGroup(SpecificationGroupDto specificationGroupDto) {
        return specificationApi.updateSpecificationGroup(specificationGroupDto);
    }

    @Override
    public RspDto batchDeleteSpecificationGroup(Long[] ids) {
        return specificationApi.batchDeleteSpecificationGroup(ids);
    }

    @Override
    public RspDto<List<SpecificationDto>> selectSpecificationByCondition(SpecificationDto specificationDto) {
        return specificationApi.selectSpecificationByCondition(specificationDto);
    }

    @Override
    public PageRspDto<List<SpecificationDto>> selectPageSpecificationByCondition(SpecificationDto specificationDto,
        Integer pageNum, Integer pageSize) {
        return specificationApi.selectPageSpecificationByCondition(specificationDto, pageNum, pageSize);
    }

    @Override
    public RspDto<SpecificationDto> selectSpecificationById(Long id) {
        return specificationApi.selectSpecificationById(id);
    }

    @Override
    public RspDto<SpecificationGroupDto> selectSpecificationGroupBySpecId(Long specId) {
        return specificationApi.selectSpecificationGroupBySpecId(specId);
    }

    @Override
    public RspDto<List<SpecificationDto>> selectSpecificationAll() {
        return specificationApi.selectSpecificationAll();
    }
}
