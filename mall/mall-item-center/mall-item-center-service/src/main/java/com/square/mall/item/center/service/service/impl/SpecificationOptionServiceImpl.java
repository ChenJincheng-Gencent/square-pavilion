package com.square.mall.item.center.service.service.impl;

import com.square.mall.common.util.DatabaseOptConstant;
import com.square.mall.common.util.StringUtil;
import com.square.mall.item.center.api.dto.SpecificationOptionDto;
import com.square.mall.item.center.service.dao.SpecificationOptionDao;
import com.square.mall.item.center.service.eo.SpecificationOptionEo;
import com.square.mall.item.center.service.service.SpecificationOptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 规格选项Service实现类
 *
 * @author Gencent
 * @date 2020/7/27
 */
@Service
@Slf4j
public class SpecificationOptionServiceImpl implements SpecificationOptionService {

    @Resource
    private SpecificationOptionDao specificationOptionDao;

    @Override
    public int insertSpecificationOption(SpecificationOptionDto specificationOptionDto) {
        if (null == specificationOptionDto || StringUtil.isBlank(specificationOptionDto.getName())) {
            log.error("specificationOptionDto or name is null or blank.");
            return DatabaseOptConstant.DATABASE_PARA_ILLEGAL;
        }
        SpecificationOptionEo specificationOptionEo = new SpecificationOptionEo();
        BeanUtils.copyProperties(specificationOptionDto, specificationOptionEo);
        return specificationOptionDao.insertSpecificationOption(specificationOptionEo);
    }

    @Override
    public int updateSpecificationOption(SpecificationOptionDto specificationOptionDto) {
        return 0;
    }

    @Override
    public int deleteSpecificationOption(Long id) {
        return 0;
    }

    @Override
    public int patchDeleteSpecificationOption(Long[] id) {
        return 0;
    }
}
