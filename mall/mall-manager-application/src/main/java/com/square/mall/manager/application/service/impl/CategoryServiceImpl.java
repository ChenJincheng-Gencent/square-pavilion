package com.square.mall.manager.application.service.impl;

import com.square.mall.common.dto.RspDto;
import com.square.mall.item.center.api.dto.CategoryDto;
import com.square.mall.item.center.api.query.CategoryQueryApi;
import com.square.mall.manager.application.service.CategoryService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

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

    @Override
    public RspDto<CategoryDto> selectCategoryByParentId(Long parentId) {
        return categoryQueryApi.selectCategoryByParentId(parentId);
    }

}
