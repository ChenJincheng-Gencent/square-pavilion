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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

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
     * 根据上级ID查询分类
     *
     * @param parentId 上级ID
     * @return 分类
     */
    @GetMapping("/category/parent")
    @ResponseBody
    @ApiOperation(value = "根据上级ID查询分类")
    public RspDto<CategoryDto> selectBrandById(@RequestParam("parentId") @NotNull(message = "上级ID不能为空") Long parentId)  {

        RspDto<CategoryDto> categoryDto = categoryService.selectCategoryByParentId(parentId);
        log.info("categoryDto: {}, parentId: {}", categoryDto, parentId);

        return categoryDto;

    }

}