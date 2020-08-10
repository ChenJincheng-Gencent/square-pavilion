package com.square.mall.item.center.service.service.impl;

import com.square.mall.common.util.ListUtil;
import com.square.mall.item.center.api.dto.CategoryDto;
import com.square.mall.item.center.service.dao.CategoryDao;
import com.square.mall.item.center.service.eo.CategoryEo;
import com.square.mall.item.center.service.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
    public List<CategoryDto> selectCategoryByParentId(Long parentId) {

        if (null == parentId) {
            log.error("parentId is null.");
            return null;
        }
        List<CategoryEo> categoryEoList = categoryDao.selectCategoryByParentId(parentId);
        if (ListUtil.isBlank(categoryEoList)) {
            log.error("categoryEoList is blank. parentId: {}", parentId);
            return null;
        }

        List<CategoryDto> categoryDtoList = new ArrayList<>();
        categoryEoList.forEach( x -> {
            CategoryDto categoryDto = new CategoryDto();
            BeanUtils.copyProperties(x, categoryDto);
            categoryDtoList.add(categoryDto);
        });

        return categoryDtoList;

    }

    @Override
    public CategoryDto selectCategory(Long id) {

        if (null == id) {
            log.error("id is null.");
            return null;
        }
        CategoryEo categoryEo = categoryDao.selectCategory(id);
        if (null == categoryEo) {
            log.error("categoryEo is null. id: {}", id);
            return null;
        }
        CategoryDto categoryDto = new CategoryDto();
        BeanUtils.copyProperties(categoryEo, categoryDto);

        return categoryDto;

    }

}
