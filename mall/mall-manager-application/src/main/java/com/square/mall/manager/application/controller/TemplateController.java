package com.square.mall.manager.application.controller;

import com.square.mall.common.dto.PageRspDto;
import com.square.mall.common.dto.RspDto;
import com.square.mall.item.center.api.dto.TemplateDto;
import com.square.mall.item.center.api.dto.TemplateGroupDto;
import com.square.mall.manager.application.service.TemplateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 模板Controller
 *
 * @author Gencent
 * @date 2020/7/31
 */
@Controller
@EnableAutoConfiguration
@RequestMapping(value = "/manager/v1")
@Slf4j
@Validated
@Api(tags = "模板REST API")
public class TemplateController {

    @Resource
    private TemplateService templateService;

    /**
     * 分页条件查询模板组合列表
     *
     * @param templateDto 查询条件
     * @param pageNum 当前页
     * @param pageSize 分页大小
     * @return 模板组合列表
     */
    @PostMapping("/template/group/list/page/condition")
    @ResponseBody
    @ApiOperation(value = "分页条件查询模板组合列表")
    public PageRspDto<List<TemplateGroupDto>> selectPageSpecificationByCondition(@RequestBody TemplateDto templateDto,
        @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize)  {

        PageRspDto<List<TemplateGroupDto>> templateGroupDtoList = templateService
            .selectPageTemplateGroupByCondition(templateDto, pageNum, pageSize);
        log.info("templateGroupDtoList: {}, templateDto: {}, pageNum: {}, pageSize: {}", templateGroupDtoList, templateDto,
            pageNum, pageSize);

        return templateGroupDtoList;

    }

    /**
     * 根据模板ID查询模板组合
     *
     * @param templateId 模板ID
     * @return 模板组合
     */
    @GetMapping("/template/group")
    @ResponseBody
    @ApiOperation(value = "根据模板ID查询模板组合")
    public RspDto<TemplateGroupDto> selectSpecificationGroupBySpecId(@RequestParam("templateId")
        @NotNull(message = "模板ID不能为空") Long templateId)  {

        RspDto<TemplateGroupDto> templateGroupDto = templateService.selectTemplateGroupByTemplateId(templateId);
        log.info("templateGroupDto: {}, templateId: {}", templateGroupDto, templateId);

        return templateGroupDto;

    }


}
