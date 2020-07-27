package com.square.mall.item.center.service.service.impl;

import com.square.mall.common.util.DatabaseOptConstant;
import com.square.mall.common.util.StringUtil;
import com.square.mall.item.center.api.dto.SpecificationDto;
import com.square.mall.item.center.service.dao.SpecificationDao;
import com.square.mall.item.center.service.eo.SpecificationEo;
import com.square.mall.item.center.service.service.SpecificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 规格Service实现类
 *
 * @author Gencent
 * @date 2020/7/27
 */
@Slf4j
@Service
public class SpecificationServiceImpl implements SpecificationService {

    @Resource
    private SpecificationDao specificationDao;

    @Override
    public int insertSpecification(SpecificationDto specificationDto) {

        if (null == specificationDto || StringUtil.isBlank(specificationDto.getName())) {
            log.error("specificationDto or name is null or blank.");
            return DatabaseOptConstant.DATABASE_PARA_ILLEGAL;
        }
        SpecificationEo oldSpecificationEo = specificationDao.selectSpecificationByName(specificationDto.getName());
        if (null != oldSpecificationEo) {
            log.error("oldSpecificationEo already exist. name: {}", specificationDto.getName());
            return DatabaseOptConstant.DATABASE_DATA_ALREADY_EXIST;
        }
        SpecificationEo specificationEo = new SpecificationEo();
        BeanUtils.copyProperties(specificationDto, specificationEo);
        return specificationDao.insertSpecification(specificationEo);

    }

    @Override
    public int updateSpecification(SpecificationDto specificationDto) {
        return 0;
    }

    @Override
    public int deleteSpecification(Long id) {
        return 0;
    }
}
