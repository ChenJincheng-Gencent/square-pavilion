package com.square.mall.item.center.service.service.impl;

import com.square.mall.common.dto.PageRspDto;
import com.square.mall.item.center.api.dto.TemplateSpecificationDto;
import com.square.mall.item.center.service.dao.TemplateSpecificationDao;
import com.square.mall.item.center.service.service.TemplateSpecificationService;
import lombok.extern.slf4j.Slf4j;
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
        return 0;
    }

    @Override
    public int updateTemplateSpecification(TemplateSpecificationDto templateSpecificationDto) {
        return 0;
    }

    @Override
    public int deleteTemplateSpecification(Long id) {
        return 0;
    }

    @Override
    public List<TemplateSpecificationDto> selectTemplateSpecificationByCondition(TemplateSpecificationDto templateSpecificationDto) {
        return null;
    }

    @Override
    public PageRspDto<List<TemplateSpecificationDto>> selectPageTemplateSpecificationByCondition(TemplateSpecificationDto templateSpecificationDto, Integer pageNum, Integer pageSize) {
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
