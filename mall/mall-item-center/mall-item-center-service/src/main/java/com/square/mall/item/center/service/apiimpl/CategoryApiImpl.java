package com.square.mall.item.center.service.apiimpl;

import com.square.mall.common.dto.RspDto;
import com.square.mall.common.util.DatabaseUtil;
import com.square.mall.common.util.ModuleConstant;
import com.square.mall.item.center.api.CategoryApi;
import com.square.mall.item.center.api.dto.CategoryDto;
import com.square.mall.item.center.service.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 分类API实现类
 *
 * @author Gencent
 * @date 2020/8/11
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class CategoryApiImpl implements CategoryApi {

    @Resource
    private CategoryService categoryService;

    @Override
    public RspDto<Long> insertCategory(CategoryDto categoryDto) {
        int success = categoryService.insertCategory(categoryDto);
        return DatabaseUtil.getResult(success, categoryDto.getId(), ModuleConstant.INVENTORY_CENTER);
    }

    @Override
    public RspDto updateCategory(CategoryDto categoryDto) {
        int success = categoryService.updateCategory(categoryDto);
        return DatabaseUtil.getResult(success, ModuleConstant.ITEM_CENTER);
    }

    @Override
    public RspDto batchDeleteCategory(Long[] ids) {
        int success = categoryService.batchDeleteCategory(ids);
        return DatabaseUtil.getResult(success, ModuleConstant.ITEM_CENTER);
    }
}
