package com.square.mall.item.center.service.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.square.mall.common.dto.PageRspDto;
import com.square.mall.common.util.DatabaseOptConstant;
import com.square.mall.common.util.ListUtil;
import com.square.mall.common.util.StringUtil;
import com.square.mall.item.center.api.dto.BrandDto;
import com.square.mall.item.center.service.dao.BrandDao;
import com.square.mall.item.center.service.eo.BrandEo;
import com.square.mall.item.center.service.service.BrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 品牌Service实现类
 *
 * @author Gencent
 * @date 2020/7/24
 */
@Slf4j
@Service
public class BrandServiceImpl implements BrandService {

    @Resource
    private BrandDao brandDao;

    @Override
    public int insertBrand(BrandDto brandDto) {

        if (null == brandDto || StringUtil.isBlank(brandDto.getName())) {
            log.error("brandDto or name is null or blank.");
            return DatabaseOptConstant.DATABASE_PARA_ILLEGAL;

        }
        BrandEo oldBrandEo = brandDao.selectBrandByName(brandDto.getName());
        if (null != oldBrandEo) {
            log.error("oldBrandEo already exist. brandDto: {}", brandDto);
            return DatabaseOptConstant.DATABASE_DATA_ALREADY_EXIST;
        }
        BrandEo brandEo = new BrandEo();
        BeanUtils.copyProperties(brandDto, brandEo);
        int success = brandDao.insertBrand(brandEo);
        brandDto.setId(brandEo.getId());
        return success;

    }

    @Override
    public int updateBrand(BrandDto brandDto) {

        if (null == brandDto || null == brandDto.getId()) {
            log.error("brandDto or id is null");
            return DatabaseOptConstant.DATABASE_PARA_ILLEGAL;
        }
        BrandEo oldBrandEo = brandDao.selectBrandById(brandDto.getId());
        if (null == oldBrandEo) {
            log.error("oldBrandEo is null. id: {}", brandDto.getId());
            return DatabaseOptConstant.DATABASE_DATA_NOT_EXIST;
        }
        BrandEo oldBrandEo2 = brandDao.selectBrandByName(brandDto.getName());
        if (null != oldBrandEo2 && !oldBrandEo2.getId().equals(brandDto.getId())) {
            log.error("oldBrandEo2 already exist. brandDto: {}", brandDto);
            return DatabaseOptConstant.DATABASE_DATA_ALREADY_EXIST;
        }
        BrandEo brandEo = new BrandEo();
        BeanUtils.copyProperties(brandDto, brandEo);
        return brandDao.updateBrand(brandEo);

    }

    @Override
    public int deleteBrand(Long id) {

        if (null == id) {
            log.error("id is null.");
            return DatabaseOptConstant.DATABASE_PARA_ILLEGAL;
        }

        BrandEo brandEo = brandDao.selectBrandById(id);
        if (null == brandEo) {
            log.error("brandEo is null. id: {}", id);
            return DatabaseOptConstant.DATABASE_DATA_NOT_EXIST;
        }

        return brandDao.deleteBrand(id);

    }

    @Override
    public int batchDeleteBrand(Long[] ids) {
        if (null == ids) {
            log.error("ids is null");
            return DatabaseOptConstant.DATABASE_PARA_ILLEGAL;
        }
        return brandDao.batchDeleteBrand(ids) >= 1 ? 1 : 0;
    }

    @Override
    public List<BrandDto> selectBrandByCondition(BrandDto brandDto) {

        BrandEo brandEo = new BrandEo();
        BeanUtils.copyProperties(brandDto, brandEo);
        List<BrandEo> brandEoList = brandDao.selectBrandByCondition(brandEo);
        List<BrandDto> brandDtoList = new ArrayList<>();
        if (ListUtil.isNotBlank(brandEoList)) {
            brandEoList.forEach( x -> {
                BrandDto brandDtoTemp = new BrandDto();
                BeanUtils.copyProperties(x, brandDtoTemp);
                brandDtoList.add(brandDtoTemp);
            });
        }
        return brandDtoList;
    }

    @Override
    public PageRspDto<List<BrandDto>> selectPageBrandByCondition(BrandDto brandDto, Integer pageNum, Integer pageSize) {

        pageNum = null == pageNum ? 1 : pageNum;
        pageSize = null == pageSize ? 10 : pageSize;
        String orderBy = "create_time" + " desc";
        PageHelper.startPage(pageNum, pageSize, orderBy);
        BrandEo brandEo = new BrandEo();
        BeanUtils.copyProperties(brandDto, brandEo);
        Page<BrandEo> page = (Page<BrandEo>) brandDao.selectBrandByCondition(brandEo);
        List<BrandDto> brandDtoList = new ArrayList<>();
        if (ListUtil.isNotBlank(page.getResult())) {
            page.getResult().forEach( x -> {
                BrandDto brandDtoTemp = new BrandDto();
                BeanUtils.copyProperties(x, brandDtoTemp);
                brandDtoList.add(brandDtoTemp);
            });
        }
        return new PageRspDto<>(page.getTotal(), brandDtoList);
    }

    @Override
    public BrandDto selectBrandByName(String name) {
        if (StringUtil.isBlank(name)) {
            log.error("name is blank.");
            return null;
        }
        BrandDto brandDto = new BrandDto();
        BrandEo brandEo = brandDao.selectBrandByName(name);
        if (null == brandEo) {
            log.error("brandEo is null. name: {}", name);
            return null;
        }
        BeanUtils.copyProperties(brandEo, brandDto);
        return brandDto;
    }

    @Override
    public BrandDto selectBrandById(Long id) {
        if (null == id) {
            log.error("id is null.");
            return null;
        }
        BrandDto brandDto = new BrandDto();
        BrandEo brandEo = brandDao.selectBrandById(id);
        if (null == brandEo) {
            log.error("brandEo is null. id: {}", id);
            return null;
        }
        BeanUtils.copyProperties(brandEo, brandDto);
        return brandDto;
    }

}
