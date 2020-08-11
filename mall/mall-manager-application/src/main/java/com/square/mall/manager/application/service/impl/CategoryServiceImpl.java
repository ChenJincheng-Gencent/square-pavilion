package com.square.mall.manager.application.service.impl;

import com.square.mall.common.dto.PageRspDto;
import com.square.mall.common.dto.RspDto;
import com.square.mall.item.center.api.CategoryApi;
import com.square.mall.item.center.api.dto.CategoryDto;
import com.square.mall.item.center.api.query.CategoryQueryApi;
import com.square.mall.manager.application.service.CategoryService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 分类Service实现类
 *
 * @author Gencent
 * @date 2020/8/10
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Reference
    private CategoryQueryApi categoryQueryApi;

    @Reference
    private CategoryApi categoryApi;

    @Override
    public RspDto<List<CategoryDto>> selectCategoryByParentId(Long parentId) {
        return categoryQueryApi.selectCategoryByParentId(parentId);
    }

    @Override
    public RspDto<CategoryDto> selectCategoryById(Long id) {
        return categoryQueryApi.selectCategoryById(id);
    }

    @Override
    public RspDto<Long> insertCategory(CategoryDto categoryDto) {
        return categoryApi.insertCategory(categoryDto);
    }

    @Override
    public RspDto updateCategory(CategoryDto categoryDto) {
        return categoryApi.updateCategory(categoryDto);
    }

    @Override
    public PageRspDto<List<CategoryDto>> selectPageCategoryByCondition(CategoryDto categoryDto, Integer pageNum, Integer pageSize) {
        return categoryQueryApi.selectPageCategoryByCondition(categoryDto, pageNum, pageSize);
    }

    @Override
    public RspDto batchDeleteCategory(Long[] ids) {
        return categoryApi.batchDeleteCategory(ids);
    }

}
