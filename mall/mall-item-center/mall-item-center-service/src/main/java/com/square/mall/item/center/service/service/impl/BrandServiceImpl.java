package com.square.mall.item.center.service.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.square.mall.common.dto.PageRspDto;
import com.square.mall.common.util.ListUtil;
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

        if (null == brandDto) {
            log.error("brandDto is null.");
            return 0;

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
            return 0;
        }
        BrandEo brandEo = new BrandEo();
        return brandDao.updateBrand(brandEo);

    }

    @Override
    public int deleteBrand(Long id) {

        if (null == id) {
            log.error("id is null.");
            return 0;
        }

        return brandDao.deleteBrand(id);

    }

    @Override
    public List<BrandDto> selectBrandByCondition(BrandDto brandDto) {
        if (null == brandDto) {
            log.error("brandDto is null.");
            return null;
        }
        BrandEo brandEo = new BrandEo();
        BeanUtils.copyProperties(brandDto, brandEo);
        List<BrandEo> brandEoList = brandDao.selectBrandByCondition(brandEo);
        List<BrandDto> brandDtoList = new ArrayList<>();
        ListUtil.copyList(brandEoList, brandDtoList);
        return brandDtoList;
    }

    @Override
    public PageRspDto<List<BrandDto>> selectPageBrandByCondition(BrandDto brandDto, Integer pageNum, Integer pageSize) {

        pageNum = null == pageNum ? 1 : pageNum;
        pageSize = null == pageSize ? 10 : pageSize;
        PageHelper.startPage(pageNum, pageSize);
        BrandEo brandEo = new BrandEo();
        BeanUtils.copyProperties(brandDto, brandEo);
        Page<BrandEo> page = (Page<BrandEo>) brandDao.selectBrandByCondition(brandEo);
        List<BrandDto> brandDtoList = new ArrayList<>();
        ListUtil.copyList(page.getResult(), brandDtoList);
        return new PageRspDto<>(page.getTotal(), brandDtoList);
    }

}
