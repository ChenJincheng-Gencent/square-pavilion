package com.square.mall.manager.application.controller;

import com.square.mall.common.dto.RspDto;
import com.square.mall.item.center.api.dto.CategoryDto;
import com.square.mall.manager.application.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 分类Controller
 *
 * @author Gencent
 * @date 2020/8/10
 */
@Controller
@EnableAutoConfiguration
@RequestMapping(value = "/manager/v1")
@Slf4j
@Validated
@Api(tags = "分类REST API")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    /**
     * 根据上级ID查询分类列表
     *
     * @param parentId 上级ID
     * @return 分类列表
     */
    @GetMapping("/category/list")
    @ResponseBody
    @ApiOperation(value = "根据上级ID查询分类列表")
    public RspDto<List<CategoryDto>> selectBrandById(@RequestParam("parentId") @NotNull(message = "上级ID不能为空") Long parentId)  {

        RspDto<List<CategoryDto>> categoryDtoList = categoryService.selectCategoryByParentId(parentId);
        log.info("categoryDtoList: {}, parentId: {}", categoryDtoList, parentId);

        return categoryDtoList;

    }

    /**
     * 根据ID查询分类
     *
     * @param id ID
     * @return 分类列表
     */
    @GetMapping("/category")
    @ResponseBody
    @ApiOperation(value = "根据ID查询分类")
    public RspDto<CategoryDto> selectCategoryById(@RequestParam("id") @NotNull(message = "ID不能为空") Long id)  {

        RspDto<CategoryDto> categoryDto = categoryService.selectCategoryById(id);
        log.info("categoryDto: {}, id: {}", categoryDto, id);

        return categoryDto;

    }

    /**
     * 插入分类
     *
     * @param categoryDto 分类
     * @return 数据库ID
     */
    @PostMapping("/category")
    @ResponseBody
    @ApiOperation(value = "插入分类")
    public RspDto insertCategory(@RequestBody @Valid CategoryDto categoryDto) {
        RspDto<Long> id = categoryService.insertCategory(categoryDto);
        log.info("id: {}, categoryDto: {}", id.getData(), categoryDto);
        return id;
    }

    /**
     * 更新分类
     *
     * @param categoryDto 分类
     * @return 响应
     */
    @PutMapping("/category")
    @ResponseBody
    @ApiOperation(value = "更新分类")
    public RspDto updateCategory(@RequestBody @Valid CategoryDto categoryDto) {

        return categoryService.updateCategory(categoryDto);
    }

}
