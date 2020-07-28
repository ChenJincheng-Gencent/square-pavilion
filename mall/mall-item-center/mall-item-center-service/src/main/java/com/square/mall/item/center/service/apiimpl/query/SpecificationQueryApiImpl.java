package com.square.mall.item.center.service.apiimpl.query;

import com.square.mall.common.dto.PageRspDto;
import com.square.mall.common.dto.RspDto;
import com.square.mall.common.util.ListUtil;
import com.square.mall.item.center.api.dto.SpecificationDto;
import com.square.mall.item.center.api.dto.SpecificationGroupDto;
import com.square.mall.item.center.api.dto.SpecificationOptionDto;
import com.square.mall.item.center.api.query.SpecificationQueryApi;
import com.square.mall.item.center.service.service.SpecificationOptionService;
import com.square.mall.item.center.service.service.SpecificationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 规格查询API实现类
 *
 * @author Gencent
 * @date 2020/7/28
 */
@Slf4j
@Service
public class SpecificationQueryApiImpl implements SpecificationQueryApi {

    @Resource
    private SpecificationService specificationService;

    @Resource
    private SpecificationOptionService specificationOptionService;

    @Override
    public RspDto<List<SpecificationGroupDto>> selectSpecificationGroup(SpecificationDto specificationDto) {
        List<SpecificationDto> specificationDtoList = specificationService.selectSpecificationByCondition(specificationDto);
        if (ListUtil.isBlank(specificationDtoList)) {
            log.error("specificationDtoList is blank. specificationDto: {}", specificationDto);
            return new RspDto<>(null);
        }
        List<SpecificationGroupDto> specificationGroupDtoList = new ArrayList<>();
        specificationDtoList.forEach( x -> {
            SpecificationGroupDto specificationGroupDto = new SpecificationGroupDto();
            specificationGroupDto.setSpecificationDto(x);
            List<SpecificationOptionDto> optionDtoList = specificationOptionService.selectSpecificationOptionBySpecId(x
                .getId());
            specificationGroupDto.setSpecificationOptionDtoList(optionDtoList);
            specificationGroupDtoList.add(specificationGroupDto);
        });

        return new RspDto<>(specificationGroupDtoList);
    }

    @Override
    public PageRspDto<List<SpecificationDto>> selectPageSpecificationByCondition(SpecificationDto specificationDto,
        Integer pageNum, Integer pageSize) {
        return specificationService.selectPageSpecificationByCondition(specificationDto, pageNum, pageSize);
    }
}
