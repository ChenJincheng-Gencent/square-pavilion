package com.square.mall.item.center.biz.service.impl;

import com.square.mall.common.dto.CommonPageRes;
import com.square.mall.common.util.ListUtil;
import com.square.mall.item.center.api.dto.ExtraAttributesDto;
import com.square.mall.item.center.biz.dao.ExtraAttributesDao;
import com.square.mall.item.center.biz.eo.ExtraAttributesEo;
import com.square.mall.item.center.biz.service.ExtraAttributesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    @Resource
    private ExtraAttributesDao extraAttributesDao;

    @Override
    public int insertExtraAttributes(ExtraAttributesDto extraAttributesDto) {
        if (null == extraAttributesDto) {
            log.error("extraAttributesDto is null.");
            return 0;
        }
        ExtraAttributesEo extraAttributesEo = new ExtraAttributesEo();
        BeanUtils.copyProperties(extraAttributesDto, extraAttributesEo);
        int success = extraAttributesDao.insertExtraAttributes(extraAttributesEo);
        extraAttributesDto.setId(extraAttributesEo.getId());
        return success;
    }

    @Override
    public int updateExtraAttributes(ExtraAttributesDto extraAttributesDto) {
        return 0;
    }

    @Override
    public int deleteExtraAttributesByTemplateId(Long templateId) {
        if (null == templateId) {
            log.error("templateId is null.");
            return 0;
        }
        return extraAttributesDao.deleteExtraAttributesByTemplateId(templateId);
    }

    @Override
    public List<ExtraAttributesDto> selectExtraAttributesByCondition(ExtraAttributesDto extraAttributesDto) {
        return null;
    }

    @Override
    public CommonPageRes<List<ExtraAttributesDto>> selectPageExtraAttributesByCondition(ExtraAttributesDto extraAttributesDto, Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public ExtraAttributesDto selectExtraAttributesByName(String name) {
        return null;
    }

    @Override
    public List<ExtraAttributesDto> selectExtraAttributesByTemplateId(Long templateId) {

        if (null == templateId) {
            log.error("templateId is null.");
            return null;
        }

        List<ExtraAttributesEo> extraAttributesEoList = extraAttributesDao.selectExtraAttributesByTemplateId(templateId);
        if (ListUtil.isBlank(extraAttributesEoList)) {
            log.error("extraAttributesEoList is blank. templateId: {}", templateId);
            return null;
        }

        List<ExtraAttributesDto> extraAttributesDtoList = new ArrayList<>();
        extraAttributesEoList.forEach( x -> {
            ExtraAttributesDto extraAttributesDto = new ExtraAttributesDto();
            BeanUtils.copyProperties(x, extraAttributesDto);
            extraAttributesDtoList.add(extraAttributesDto);
        });

        return extraAttributesDtoList;

    }


}
