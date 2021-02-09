package com.square.mall.item.center.service.service.impl;

import com.square.mall.common.dto.PageRspDto;
import com.square.mall.item.center.api.dto.TemplateBrandDto;
import com.square.mall.item.center.service.dao.TemplateBrandDao;
import com.square.mall.item.center.service.eo.TemplateBrandEo;
import com.square.mall.item.center.service.service.TemplateBrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 模板品牌Service实现类
 *
 * @author Gencent
 * @date 2020/7/28
 */
@Slf4j
@Service
public class TemplateBrandServiceImpl implements TemplateBrandService {

    @Resource
    private TemplateBrandDao templateBrandDao;

    @Override
    public int insertTemplateBrand(TemplateBrandDto templateBrandDto) {

        if (null == templateBrandDto) {
            log.error("templateBrandDto is null.");
            return 0;
        }

        TemplateBrandEo templateBrandEo = new TemplateBrandEo();
        BeanUtils.copyProperties(templateBrandDto, templateBrandEo);
        int success = templateBrandDao.insertTemplateBrand(templateBrandEo);
        templateBrandDto.setId(templateBrandEo.getId());

        return success;

    }

    @Override
    public int updateTemplateBrand(TemplateBrandDto templateBrandDto) {
        return 0;
    }

    @Override
    public int deleteTemplateBrandByTemplateId(Long templateId) {

        if (null == templateId) {
            log.error("templateId is null.");
            return 0;
        }

        int success = templateBrandDao.deleteTemplateBrandByTemplateId(templateId);

        return success >= 1 ? 1 : 0;

    }

    @Override
    public List<TemplateBrandDto> selectTemplateBrandByCondition(TemplateBrandDto templateBrandDto) {
        return null;
    }

    @Override
    public PageRspDto<List<TemplateBrandDto>> selectPageTemplateBrandByCondition(TemplateBrandDto templateBrandDto, Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public TemplateBrandDto selectTemplateBrandByName(String name) {
        return null;
    }

    @Override
    public List<Long> selectBrandIdByTemplateId(Long templateId) {

        if (null == templateId) {
            log.error("templateId is null.");
            return null;
        }
        return templateBrandDao.selectBrandIdByTemplateId(templateId);
    }


}
