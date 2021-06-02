package com.square.mall.item.center.biz.service.impl;

import com.square.mall.common.util.ListUtil;
import com.square.mall.common.util.StringUtil;
import com.square.mall.item.center.api.dto.SpecificationOptionDto;
import com.square.mall.item.center.biz.dao.SpecificationOptionDao;
import com.square.mall.item.center.biz.eo.SpecificationOptionEo;
import com.square.mall.item.center.biz.service.SpecificationOptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
            return 0;
        }
        SpecificationOptionEo specificationOptionEo = new SpecificationOptionEo();
        BeanUtils.copyProperties(specificationOptionDto, specificationOptionEo);
        return specificationOptionDao.insertSpecificationOption(specificationOptionEo);
    }

    @Override
    public int updateSpecificationOption(SpecificationOptionDto specificationOptionDto) {

        if (null == specificationOptionDto || null == specificationOptionDto.getId()) {
            log.error("specificationOptionDto or id is null.");
            return 0;
        }
        SpecificationOptionEo oldSpecificationOptionEo = specificationOptionDao
            .selectSpecificationOptionById(specificationOptionDto.getId());
        if (null == oldSpecificationOptionEo) {
            log.error("oldSpecificationOptionEo is null. id: {}", specificationOptionDto.getId());
            return 0;
        }
        SpecificationOptionEo specificationOptionEo = new SpecificationOptionEo();
        BeanUtils.copyProperties(specificationOptionDto, specificationOptionEo);
        return specificationOptionDao.updateSpecificationOption(specificationOptionEo);

    }

    @Override
    public int deleteSpecificationOption(Long id) {

        if (null == id) {
            log.error("id is null.");
            return 0;
        }
        SpecificationOptionEo oldSpecificationOptionEo = specificationOptionDao.selectSpecificationOptionById(id);
        if (null == oldSpecificationOptionEo) {
            log.error("oldSpecificationOptionEo is null. id: {}", id);
            return 0;
        }
        return specificationOptionDao.deleteSpecificationOption(id);

    }

    @Override
    public int batchDeleteSpecificationOption(Long[] ids) {

        if (null == ids || ids.length <= 0) {
            log.error("ids is blank.");
            return 0;
        }
        return specificationOptionDao.batchDeleteSpecificationOption(ids) >= 1 ? 1: 0;
    }

    @Override
    public int deleteSpecificationOptionBySpecId(Long specId) {

        if (null == specId) {
            log.error("specId is null.");
            return 0;
        }
        return specificationOptionDao.deleteSpecificationOptionBySpecId(specId) >= 1 ? 1 : 0;

    }

    @Override
    public List<SpecificationOptionDto> selectSpecificationOptionBySpecId(Long specId) {

        if (null == specId) {
            log.error("specId is null.");
            return null;
        }
        List<SpecificationOptionEo> optionEoList = specificationOptionDao.selectSpecificationOptionBySpecId(specId);
        if (ListUtil.isBlank(optionEoList)) {
            log.error("optionEoList is blank. specId: {}", specId);
            return null;
        }

        List<SpecificationOptionDto> specificationOptionDtoList = new ArrayList<>();
        optionEoList.forEach( x -> {
            SpecificationOptionDto specificationOptionDto = new SpecificationOptionDto();
            BeanUtils.copyProperties(x, specificationOptionDto);
            specificationOptionDtoList.add(specificationOptionDto);
        });
        return specificationOptionDtoList;
    }

}
