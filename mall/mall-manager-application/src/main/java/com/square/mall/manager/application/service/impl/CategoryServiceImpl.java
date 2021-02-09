package com.square.mall.manager.application.service.impl;

import com.square.mall.common.dto.CommonPageRes;
import com.square.mall.common.dto.CommonRes;

import com.square.mall.item.center.api.CategoryApi;
import com.square.mall.item.center.api.dto.CategoryDto;

import com.square.mall.manager.application.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 分类Service实现类
 *
 * @author Gencent
 * @date 2020/8/10
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryApi categoryApi;

    @Override
    public CommonRes<List<CategoryDto>> selectCategoryByParentId(Long parentId) {
        return categoryApi.selectCategoryByParentId(parentId);
    }

    @Override
    public CommonRes<CategoryDto> selectCategoryById(Long id) {
        return categoryApi.selectCategoryById(id);
    }

    @Override
    public CommonRes<Long> insertCategory(CategoryDto categoryDto) {
        return categoryApi.insertCategory(categoryDto);
    }

    @Override
    public CommonRes<Void> updateCategory(CategoryDto categoryDto) {
        return categoryApi.updateCategory(categoryDto);
    }

    @Override
    public CommonPageRes<List<CategoryDto>> selectPageCategoryByCondition(CategoryDto categoryDto, Integer pageNum, Integer pageSize) {
        return categoryApi.selectPageCategoryByCondition(categoryDto, pageNum, pageSize);
    }

    @Override
    public CommonRes<Void> batchDeleteCategory(Long[] ids) {
        return categoryApi.batchDeleteCategory(ids);
    }

}
