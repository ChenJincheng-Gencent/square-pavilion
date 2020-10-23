package com.square.mall.item.center.service.apiimpl.query;

import com.square.mall.common.dto.PageRspDto;
import com.square.mall.common.dto.RspDto;
import com.square.mall.item.center.api.dto.SpecificationDto;
import com.square.mall.item.center.api.dto.SpecificationGroupDto;
import com.square.mall.item.center.api.dto.SpecificationOptionDto;
import com.square.mall.item.center.api.query.SpecificationQueryApi;
import com.square.mall.item.center.service.service.SpecificationOptionService;
import com.square.mall.item.center.service.service.SpecificationService;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.util.List;

/**
 * 规格查询API实现类
 *
 * @author Gencent
 * @date 2020/7/28
 */
@Slf4j
public class SpecificationQueryApiImpl implements SpecificationQueryApi {

    @Resource
    private SpecificationService specificationService;

    @Resource
    private SpecificationOptionService specificationOptionService;

    @Override
    public RspDto<SpecificationGroupDto> selectSpecificationGroupBySpecId(Long specId) {
        SpecificationDto specificationDto = specificationService.selectSpecificationById(specId);
        if (null == specificationDto) {
            log.error("specificationDto is null. specId: {}", specId);
            return new RspDto<>(null);
        }
        SpecificationGroupDto specificationGroupDto = new SpecificationGroupDto();
        specificationGroupDto.setSpecificationDto(specificationDto);
        List<SpecificationOptionDto> optionDtoList = specificationOptionService.selectSpecificationOptionBySpecId(specId);
        specificationGroupDto.setSpecificationOptionDtoList(optionDtoList);
        return new RspDto<>(specificationGroupDto);
    }

    @Override
    public PageRspDto<List<SpecificationDto>> selectPageSpecificationByCondition(SpecificationDto specificationDto,
        Integer pageNum, Integer pageSize) {
        return specificationService.selectPageSpecificationByCondition(specificationDto, pageNum, pageSize);
    }

    @Override
    public RspDto<SpecificationDto> selectSpecificationById(Long id) {
        return new RspDto<>(specificationService.selectSpecificationById(id));
    }

    @Override
    public RspDto<List<SpecificationDto>> selectSpecificationAll() {
        return new RspDto<>(specificationService.selectSpecificationAll());
    }
}
