package com.square.mall.item.center.service.service.impl;

import com.square.mall.item.center.api.dto.CategoryDto;
import com.square.mall.item.center.service.dao.CategoryDao;
import com.square.mall.item.center.service.eo.CategoryEo;
import com.square.mall.item.center.service.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 分类Service实现类
 *
 * @author Gencent
 * @date 2020/8/10
 */
@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryDao categoryDao;

    @Override
    public CategoryDto selectCategoryByParentId(Long parentId) {

        if (null == parentId) {
            log.error("parentId is null.");
            return null;
        }
        CategoryEo categoryEo = categoryDao.selectCategoryByParentId(parentId);
        if (null == categoryEo) {
            log.error("categoryEo is null. parentId: {}", parentId);
            return null;
        }
        CategoryDto categoryDto = new CategoryDto();
        BeanUtils.copyProperties(categoryEo, categoryDto);

        return categoryDto;

    }

}
