package com.square.mall.item.center.biz.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.square.mall.common.dto.CommonPageRes;
import com.square.mall.common.util.ListUtil;
import com.square.mall.common.util.StringUtil;
import com.square.mall.item.center.api.dto.SpecificationDto;
import com.square.mall.item.center.biz.dao.SpecificationDao;
import com.square.mall.item.center.biz.eo.SpecificationEo;
import com.square.mall.item.center.biz.service.SpecificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
            return 0;
        }
        SpecificationEo oldSpecificationEo = specificationDao.selectSpecificationByName(specificationDto.getName());
        if (null != oldSpecificationEo) {
            log.error("oldSpecificationEo already exist. name: {}", specificationDto.getName());
            return 0;
        }
        SpecificationEo specificationEo = new SpecificationEo();
        BeanUtils.copyProperties(specificationDto, specificationEo);
        int success = specificationDao.insertSpecification(specificationEo);
        specificationDto.setId(specificationEo.getId());
        return success;

    }

    @Override
    public int updateSpecification(SpecificationDto specificationDto) {

        if (null == specificationDto || StringUtil.isBlank(specificationDto.getName())) {
            return 0;
        }
        SpecificationEo specificationEo = new SpecificationEo();
        BeanUtils.copyProperties(specificationDto, specificationEo);
        return specificationDao.updateSpecification(specificationEo);
    }

    @Override
    public int deleteSpecification(Long id) {

        if (null == id) {
            log.error("id is null.");
            return 0;
        }
        return specificationDao.deleteSpecification(id);
    }

    @Override
    public List<SpecificationDto> selectSpecificationByCondition(SpecificationDto specificationDto) {
        SpecificationEo specificationEo = new SpecificationEo();
        if (null != specificationDto) {
            BeanUtils.copyProperties(specificationDto, specificationEo);
        }

        List<SpecificationEo> specificationEoList = specificationDao.selectSpecificationByCondition(specificationEo);
        if (ListUtil.isBlank(specificationEoList)) {
            log.error("specificationEoList is blank. specificationDto: {}", specificationDto);
            return null;
        }
        List<SpecificationDto> specificationDtoList = new ArrayList<>();
        specificationEoList.forEach( x -> {
            SpecificationDto specificationDtoTemp = new SpecificationDto();
            BeanUtils.copyProperties(x, specificationDtoTemp);
            specificationDtoList.add(specificationDtoTemp);
        });
        return specificationDtoList;
    }

    @Override
    public CommonPageRes<List<SpecificationDto>> selectPageSpecificationByCondition(SpecificationDto specificationDto,
                                                                                    Integer pageNum, Integer pageSize) {

        pageNum = null == pageNum ? 1 : pageNum;
        pageSize = null == pageSize ? 10 : pageSize;
        String orderBy = "create_time" + " desc";
        PageHelper.startPage(pageNum, pageSize, orderBy);
        SpecificationEo specificationEo = new SpecificationEo();
        if (null != specificationDto) {
            BeanUtils.copyProperties(specificationDto, specificationEo);
        }
        Page<SpecificationEo> page = (Page<SpecificationEo>) specificationDao.selectSpecificationByCondition(specificationEo);
        List<SpecificationDto> specificationDtoList = new ArrayList<>();
        if (ListUtil.isNotBlank(page.getResult())) {
            page.getResult().forEach( x -> {
                SpecificationDto specificationDtoTemp = new SpecificationDto();
                BeanUtils.copyProperties(x, specificationDtoTemp);
                specificationDtoList.add(specificationDtoTemp);
            });
        }
        return new CommonPageRes<>(page.getTotal(), specificationDtoList);
    }

    @Override
    public SpecificationDto selectSpecificationById(Long id) {
        if (null == id) {
            log.error("id is null.");
            return null;
        }
        SpecificationEo specificationEo = specificationDao.selectSpecificationById(id);
        if (null == specificationEo) {
            log.error("specificationEo is null. id: {}", id);
            return null;
        }
        SpecificationDto specificationDto = new SpecificationDto();
        BeanUtils.copyProperties(specificationEo, specificationDto);
        return specificationDto;
    }

    @Override
    public List<SpecificationDto> selectSpecificationAll() {

        List<SpecificationEo> specificationEoList = specificationDao.selectSpecificationAll();
        if (ListUtil.isBlank(specificationEoList)) {
            log.error("specificationEoList is blank.");
            return null;
        }

        List<SpecificationDto> specificationDtoList = new ArrayList<>();
        specificationEoList.forEach( x -> {
            SpecificationDto specificationDto = new SpecificationDto();
            BeanUtils.copyProperties(x, specificationDto);
            specificationDtoList.add(specificationDto);
        });

        return specificationDtoList;
    }
}
