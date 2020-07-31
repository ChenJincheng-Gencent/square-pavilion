package com.square.mall.item.center.service.service.impl;

import com.square.mall.common.util.DatabaseOptConstant;
import com.square.mall.common.util.ListUtil;
import com.square.mall.common.util.StringUtil;
import com.square.mall.item.center.api.dto.SpecificationOptionDto;
import com.square.mall.item.center.service.dao.SpecificationOptionDao;
import com.square.mall.item.center.service.eo.SpecificationOptionEo;
import com.square.mall.item.center.service.service.SpecificationOptionService;
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
            return DatabaseOptConstant.DATABASE_PARA_ILLEGAL;
        }
        SpecificationOptionEo specificationOptionEo = new SpecificationOptionEo();
        BeanUtils.copyProperties(specificationOptionDto, specificationOptionEo);
        return specificationOptionDao.insertSpecificationOption(specificationOptionEo);
    }

    @Override
    public int updateSpecificationOption(SpecificationOptionDto specificationOptionDto) {

        if (null == specificationOptionDto || null == specificationOptionDto.getId()) {
            log.error("specificationOptionDto or id is null.");
            return DatabaseOptConstant.DATABASE_PARA_ILLEGAL;
        }
        SpecificationOptionEo oldSpecificationOptionEo = specificationOptionDao
            .selectSpecificationOptionById(specificationOptionDto.getId());
        if (null == oldSpecificationOptionEo) {
            log.error("oldSpecificationOptionEo is null. id: {}", specificationOptionDto.getId());
            return DatabaseOptConstant.DATABASE_DATA_NOT_EXIST;
        }
        SpecificationOptionEo specificationOptionEo = new SpecificationOptionEo();
        BeanUtils.copyProperties(specificationOptionDto, specificationOptionEo);
        return specificationOptionDao.updateSpecificationOption(specificationOptionEo);

    }

    @Override
    public int deleteSpecificationOption(Long id) {

        if (null == id) {
            log.error("id is null.");
            return DatabaseOptConstant.DATABASE_PARA_ILLEGAL;
        }
        SpecificationOptionEo oldSpecificationOptionEo = specificationOptionDao.selectSpecificationOptionById(id);
        if (null == oldSpecificationOptionEo) {
            log.error("oldSpecificationOptionEo is null. id: {}", id);
            return DatabaseOptConstant.DATABASE_DATA_NOT_EXIST;
        }
        return specificationOptionDao.deleteSpecificationOption(id);

    }

    @Override
    public int batchDeleteSpecificationOption(Long[] ids) {

        if (null == ids) {
            log.error("ids is null.");
            return DatabaseOptConstant.DATABASE_PARA_ILLEGAL;
        }
        return specificationOptionDao.batchDeleteSpecificationOption(ids);
    }

    @Override
    public int deleteSpecificationOptionBySpecId(Long specId) {

        if (null == specId) {
            log.error("specId is null.");
            return DatabaseOptConstant.DATABASE_PARA_ILLEGAL;
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
