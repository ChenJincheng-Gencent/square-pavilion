package com.square.mall.item.center.service.service.impl;

import com.square.mall.common.dto.PageRspDto;
import com.square.mall.item.center.api.dto.ExtraAttributesDto;
import com.square.mall.item.center.service.service.ExtraAttributesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 扩展属性Service实现类
 *
 * @author Gencent
 * @date 2020/7/28
 */
@Slf4j
@Service
public class ExtraAttributesServiceImpl implements ExtraAttributesService {
    @Override
    public int insertExtraAttributes(ExtraAttributesDto extraAttributesDto) {
        return 0;
    }

    @Override
    public int updateExtraAttributes(ExtraAttributesDto extraAttributesDto) {
        return 0;
    }

    @Override
    public int deleteExtraAttributes(Long id) {
        return 0;
    }

    @Override
    public List<ExtraAttributesDto> selectExtraAttributesByCondition(ExtraAttributesDto extraAttributesDto) {
        return null;
    }

    @Override
    public PageRspDto<List<ExtraAttributesDto>> selectPageExtraAttributesByCondition(ExtraAttributesDto extraAttributesDto, Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public ExtraAttributesDto selectExtraAttributesByName(String name) {
        return null;
    }

    @Override
    public ExtraAttributesDto selectExtraAttributesById(Long id) {
        return null;
    }
}
