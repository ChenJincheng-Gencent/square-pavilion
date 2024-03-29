package com.square.mall.item.center.biz.service.impl;

import com.square.mall.common.dto.CommonPageRes;
import com.square.mall.item.center.api.dto.TemplateSpecificationDto;
import com.square.mall.item.center.biz.dao.TemplateSpecificationDao;
import com.square.mall.item.center.biz.eo.TemplateSpecificationEo;
import com.square.mall.item.center.biz.service.TemplateSpecificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 模板规格Service实现类
 *
 * @author Gencent
 * @date 2020/7/28
 */
@Slf4j
@Service
public class TemplateSpecificationServiceImpl implements TemplateSpecificationService {

    @Resource
    private TemplateSpecificationDao templateSpecificationDao;

    @Override
    public int insertTemplateSpecification(TemplateSpecificationDto templateSpecificationDto) {
        if (null == templateSpecificationDto) {
            log.error("templateSpecificationDto is null.");
            return 0;
        }
        TemplateSpecificationEo templateSpecificationEo = new TemplateSpecificationEo();
        BeanUtils.copyProperties(templateSpecificationDto, templateSpecificationEo);
        int success = templateSpecificationDao.insertTemplateSpecification(templateSpecificationEo);
        templateSpecificationDto.setId(templateSpecificationEo.getId());
        return success;
    }

    @Override
    public int updateTemplateSpecification(TemplateSpecificationDto templateSpecificationDto) {
        return 0;
    }

    @Override
    public int deleteTemplateSpecificationByTemplateId(Long templateId) {

        if (null == templateId) {
            log.error("templateId is null.");
            return 0;
        }
        return templateSpecificationDao.deleteTemplateSpecificationByTemplateId(templateId);
    }

    @Override
    public List<TemplateSpecificationDto> selectTemplateSpecificationByCondition(TemplateSpecificationDto templateSpecificationDto) {
        return null;
    }

    @Override
    public CommonPageRes<List<TemplateSpecificationDto>> selectPageTemplateSpecificationByCondition(TemplateSpecificationDto templateSpecificationDto, Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public List<Long> selectSpecIdByTemplateId(Long templateId) {

        if (null == templateId) {
            log.error("templateId is null.");
            return null;
        }
        return templateSpecificationDao.selectSpecIdByTemplateId(templateId);
    }
}
