package com.square.mall.item.center.service.service.impl;

import com.square.mall.item.center.api.dto.BrandDto;
import com.square.mall.item.center.service.dao.BrandDao;
import com.square.mall.item.center.service.eo.BrandEo;
import com.square.mall.item.center.service.service.BrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
        return null;
    }
}
