package com.square.mall.item.center.service.apiimpl.query;

import com.square.mall.common.dto.RspDto;
import com.square.mall.item.center.api.dto.CategoryDto;
import com.square.mall.item.center.api.query.CategoryQueryApi;
import com.square.mall.item.center.service.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 分类查询API实现类
 *
 * @author Gencent
 * @date 2020/8/10
 */
@Slf4j
@Service
public class CategoryQueryApiImpl implements CategoryQueryApi {

    @Resource
    private CategoryService categoryService;

    @Override
    public RspDto<List<CategoryDto>> selectCategoryByParentId(Long parentId) {

        return new RspDto<>(categoryService.selectCategoryByParentId(parentId));
    }

    @Override
    public RspDto<CategoryDto> selectCategoryById(Long id) {
        return new RspDto<>(categoryService.selectCategoryById(id));
    }

}
