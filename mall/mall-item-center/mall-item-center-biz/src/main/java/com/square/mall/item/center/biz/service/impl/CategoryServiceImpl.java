package com.square.mall.item.center.biz.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.square.mall.common.dto.CommonPageRes;
import com.square.mall.common.util.ListUtil;
import com.square.mall.item.center.api.dto.CategoryDto;
import com.square.mall.item.center.biz.dao.CategoryDao;
import com.square.mall.item.center.biz.eo.CategoryEo;
import com.square.mall.item.center.biz.service.CategoryService;
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
    public CategoryDto selectCategoryById(Long id) {

        if (null == id) {
            log.error("id is null.");
            return null;
        }
        CategoryEo categoryEo = categoryDao.selectCategoryById(id);
        if (null == categoryEo) {
            log.error("categoryEo is null. id: {}", id);
            return null;
        }
        CategoryDto categoryDto = new CategoryDto();
        BeanUtils.copyProperties(categoryEo, categoryDto);

        return categoryDto;

    }

    @Override
    public int insertCategory(CategoryDto categoryDto) {

        if (null == categoryDto) {
            log.error("categoryDto is null.");
            return 0;
        }

        CategoryEo categoryEo = new CategoryEo();
        BeanUtils.copyProperties(categoryDto, categoryEo);
        int success = categoryDao.insertCategory(categoryEo);
        categoryDto.setId(categoryEo.getId());

        return success;

    }

    @Override
    public int updateCategory(CategoryDto categoryDto) {

        if (null == categoryDto || null == categoryDto.getId()) {
            log.error("categoryDto or id is null.");
            return 0;
        }

        CategoryEo categoryEo = new CategoryEo();
        BeanUtils.copyProperties(categoryDto, categoryEo);

        return categoryDao.updateCategory(categoryEo);

    }

    @Override
    public CommonPageRes<List<CategoryDto>> selectPageCategoryByCondition(CategoryDto categoryDto, Integer pageNum, Integer pageSize) {
        pageNum = null == pageNum ? 1 : pageNum;
        pageSize = null == pageSize ? 10 : pageSize;
        String orderBy = "create_time" + " desc";
        PageHelper.startPage(pageNum, pageSize, orderBy);
        CategoryEo categoryEo = new CategoryEo();
        BeanUtils.copyProperties(categoryDto, categoryEo);
        Page<CategoryEo> page = (Page<CategoryEo>) categoryDao.selectCategoryByCondition(categoryEo);
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        if (ListUtil.isNotBlank(page.getResult())) {
            page.getResult().forEach( x -> {
                CategoryDto categoryDtoTemp = new CategoryDto();
                BeanUtils.copyProperties(x, categoryDtoTemp);
                categoryDtoList.add(categoryDtoTemp);
            });
        }
        return new CommonPageRes<>(page.getTotal(), categoryDtoList);
    }

    @Override
    public int batchDeleteCategory(Long[] ids) {
        if (null == ids || ids.length <= 0) {
            log.error("ids is blank.");
            return 0;
        }
        return categoryDao.batchDeleteCategory(ids) >= 1 ? 1 : 0;
    }

}
