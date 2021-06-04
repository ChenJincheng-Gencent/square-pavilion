package com.square.mall.manager.application.controller;

import com.square.mall.common.dto.CommonPageRes;
import com.square.mall.common.dto.CommonRes;
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
@RequestMapping(value = "/category")
@Slf4j
@Validated
@Api(tags = "分类")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    /**
     * 根据上级ID查询分类列表
     *
     * @param parentId 上级ID
     * @return 分类列表
     */
    @GetMapping("/selectCategoryByParentId")
    @ResponseBody
    @ApiOperation(value = "根据上级ID查询分类列表")
    public CommonRes<List<CategoryDto>> selectCategoryByParentId(@RequestParam("parentId") @NotNull(message = "上级ID不能为空") Long parentId)  {

        CommonRes<List<CategoryDto>> categoryDtoList = categoryService.selectCategoryByParentId(parentId);
        log.info("categoryDtoList: {}, parentId: {}", categoryDtoList, parentId);

        return categoryDtoList;

    }

    /**
     * 根据ID查询分类
     *
     * @param id ID
     * @return 分类列表
     */
    @GetMapping("/selectCategoryById")
    @ResponseBody
    @ApiOperation(value = "根据ID查询分类")
    public CommonRes<CategoryDto> selectCategoryById(@RequestParam("id") @NotNull(message = "ID不能为空") Long id)  {

        CommonRes<CategoryDto> categoryDto = categoryService.selectCategoryById(id);
        log.info("categoryDto: {}, id: {}", categoryDto, id);

        return categoryDto;

    }

    /**
     * 插入分类
     *
     * @param categoryDto 分类
     * @return 数据库ID
     */
    @PostMapping("/insertCategory")
    @ResponseBody
    @ApiOperation(value = "插入分类")
    public CommonRes<Long> insertCategory(@RequestBody @Valid CategoryDto categoryDto) {
        CommonRes<Long> id = categoryService.insertCategory(categoryDto);
        log.info("id: {}, categoryDto: {}", id.getData(), categoryDto);
        return id;
    }

    /**
     * 更新分类
     *
     * @param categoryDto 分类
     * @return 响应
     */
    @PutMapping("/updateCategory")
    @ResponseBody
    @ApiOperation(value = "更新分类")
    public CommonRes<Void> updateCategory(@RequestBody @Valid CategoryDto categoryDto) {

        return categoryService.updateCategory(categoryDto);
    }

    /**
     * 分页条件查询品牌列表
     *
     * @param categoryDto 查询条件
     * @param pageNum 当前页
     * @param pageSize 分页大小
     * @return 品牌列表
     */
    @PostMapping("/selectPageCategoryByCondition")
    @ResponseBody
    @ApiOperation(value = "分页条件查询品牌列表")
    public CommonPageRes<List<CategoryDto>> selectPageCategoryByCondition(@RequestBody CategoryDto categoryDto,
                                                                          @RequestParam("pageNum") Integer pageNum,
                                                                          @RequestParam("pageSize") Integer pageSize)  {

        CommonPageRes<List<CategoryDto>> categoryDtoList = categoryService.selectPageCategoryByCondition(categoryDto, pageNum, pageSize);
        log.info("categoryDtoList: {}, categoryDto: {}, pageNum: {}, pageSize: {}", categoryDtoList, categoryDto, pageNum, pageSize);

        return categoryDtoList;

    }

    /**
     * 批量删除分类列表
     *
     * @param ids ID数组
     * @return 响应
     */
    @DeleteMapping("/deleteCategory")
    @ResponseBody
    @ApiOperation(value = "批量删除分类列表")
    public CommonRes<Void> deleteCategory(Long[] ids) {
        log.info("ids: {}", ids);
        return categoryService.batchDeleteCategory(ids);
    }


}
