package com.square.mall.manager.application.controller;

import com.square.mall.common.dto.PageRspDto;
import com.square.mall.common.dto.RspDto;
import com.square.mall.common.util.ListUtil;
import com.square.mall.item.center.api.dto.*;
import com.square.mall.manager.application.service.TemplateService;
import com.square.mall.manager.application.vo.Select2Vo;
import com.square.mall.manager.application.vo.TemplateGroupVo;
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
import java.util.ArrayList;
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
    public PageRspDto<List<TemplateGroupVo>> selectPageSpecificationByCondition(@RequestBody TemplateDto templateDto,
        @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize)  {

        PageRspDto<List<TemplateGroupDto>> listPageRspDto = templateService.selectPageTemplateGroupByCondition(templateDto,
            pageNum, pageSize);
        log.info("listPageRspDto: {}, templateDto: {}, pageNum: {}, pageSize: {}", listPageRspDto, templateDto,
            pageNum, pageSize);

        List<TemplateGroupVo> templateGroupVoList = new ArrayList<>();
        List<TemplateGroupDto> templateGroupDtoList = listPageRspDto.getData();

        if (ListUtil.isNotBlank(templateGroupDtoList)) {
            templateGroupDtoList.forEach( x -> {
                TemplateGroupVo templateGroupVo = new TemplateGroupVo();
                templateGroupVo.setTemplateDto(x.getTemplateDto());
                List<BrandDto> brandDtoList = x.getBrandDtoList();
                List<Select2Vo> select2VoList = new ArrayList<>();
                if (ListUtil.isNotBlank(brandDtoList)) {
                    brandDtoList.forEach( y -> {
                        Select2Vo select2Vo = new Select2Vo();
                        select2Vo.setId(y.getId());
                        select2Vo.setText(y.getName());
                        select2VoList.add(select2Vo);
                    });
                }
                templateGroupVo.setBrandDtoList(select2VoList);

                List<SpecificationDto> specificationDtoList = x.getSpecificationDtoList();
                List<Select2Vo> select2VoList1 = new ArrayList<>();
                if (ListUtil.isNotBlank(specificationDtoList)) {
                    specificationDtoList.forEach( y -> {
                        Select2Vo select2Vo = new Select2Vo();
                        select2Vo.setId(y.getId());
                        select2Vo.setText(y.getName());
                        select2VoList1.add(select2Vo);
                    });
                }
                templateGroupVo.setSpecificationDtoList(select2VoList1);
                templateGroupVo.setExtraAttributesDtoList(x.getExtraAttributesDtoList());
                templateGroupVoList.add(templateGroupVo);
            });
        }

        return new PageRspDto<>(listPageRspDto.getTotal(), templateGroupVoList);

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
    public RspDto<TemplateGroupVo> selectSpecificationGroupBySpecId(@RequestParam("templateId")
        @NotNull(message = "模板ID不能为空") Long templateId)  {

        TemplateGroupDto templateGroupDto = templateService.selectTemplateGroupByTemplateId(templateId).getData();
        log.info("templateGroupDto: {}, templateId: {}", templateGroupDto, templateId);
        TemplateGroupVo templateGroupVo = new TemplateGroupVo();
        templateGroupVo.setTemplateDto(templateGroupDto.getTemplateDto());
        List<BrandDto> brandDtoList = templateGroupDto.getBrandDtoList();
        List<Select2Vo> select2VoList = new ArrayList<>();
        if (ListUtil.isNotBlank(brandDtoList)) {
            brandDtoList.forEach( y -> {
                Select2Vo select2Vo = new Select2Vo();
                select2Vo.setId(y.getId());
                select2Vo.setText(y.getName());
                select2VoList.add(select2Vo);
            });
        }
        templateGroupVo.setBrandDtoList(select2VoList);

        List<SpecificationDto> specificationDtoList = templateGroupDto.getSpecificationDtoList();
        List<Select2Vo> select2VoList1 = new ArrayList<>();
        if (ListUtil.isNotBlank(specificationDtoList)) {
            specificationDtoList.forEach( y -> {
                Select2Vo select2Vo = new Select2Vo();
                select2Vo.setId(y.getId());
                select2Vo.setText(y.getName());
                select2VoList1.add(select2Vo);
            });
        }
        templateGroupVo.setSpecificationDtoList(select2VoList1);
        templateGroupVo.setExtraAttributesDtoList(templateGroupDto.getExtraAttributesDtoList());

        return new RspDto<>(templateGroupVo);

    }

    /**
     * 插入模板组合
     *
     * @param templateGroupVo 模板组合
     * @return 数据库ID
     */
    @PostMapping("/template/group")
    @ResponseBody
    @ApiOperation(value = "插入模板组合")
    public RspDto insertTemplateGroup(@RequestBody @Valid TemplateGroupVo templateGroupVo) {
        TemplateGroupDto templateGroupDto = new TemplateGroupDto();
        templateGroupDto.setTemplateDto(templateGroupVo.getTemplateDto());
        List<BrandDto> brandDtoList = new ArrayList<>();
        if (ListUtil.isNotBlank(templateGroupVo.getBrandDtoList())) {
            templateGroupVo.getBrandDtoList().forEach( x -> {
                BrandDto brandDto = new BrandDto();
                brandDto.setId(x.getId());
                brandDtoList.add(brandDto);
            });
        }
        templateGroupDto.setBrandDtoList(brandDtoList);
        List<SpecificationDto> specificationDtoList = new ArrayList<>();
        if (ListUtil.isNotBlank(templateGroupVo.getSpecificationDtoList())) {
            templateGroupVo.getSpecificationDtoList().forEach( x -> {
                SpecificationDto specificationDto = new SpecificationDto();
                specificationDto.setId(x.getId());
                specificationDtoList.add(specificationDto);
            });
        }
        templateGroupDto.setSpecificationDtoList(specificationDtoList);
        templateGroupDto.setExtraAttributesDtoList(templateGroupVo.getExtraAttributesDtoList());
        RspDto<Long> id = templateService.insertTemplateGroup(templateGroupDto);
        log.info("id: {}, templateDto: {}", id.getData(), templateGroupDto);
        return id;
    }

    /**
     * 更新模板组合
     *
     * @param templateGroupVo 模板组合
     * @return 数据库ID
     */
    @PutMapping("/template/group")
    @ResponseBody
    @ApiOperation(value = "更新模板组合")
    public RspDto updateTemplateGroup(@RequestBody @Valid TemplateGroupVo templateGroupVo) {

        TemplateGroupDto templateGroupDto = new TemplateGroupDto();
        templateGroupDto.setTemplateDto(templateGroupVo.getTemplateDto());
        List<BrandDto> brandDtoList = new ArrayList<>();
        if (ListUtil.isNotBlank(templateGroupVo.getBrandDtoList())) {
            templateGroupVo.getBrandDtoList().forEach( x -> {
                BrandDto brandDto = new BrandDto();
                brandDto.setId(x.getId());
                brandDtoList.add(brandDto);
            });
        }
        templateGroupDto.setBrandDtoList(brandDtoList);
        List<SpecificationDto> specificationDtoList = new ArrayList<>();
        if (ListUtil.isNotBlank(templateGroupVo.getSpecificationDtoList())) {
            templateGroupVo.getSpecificationDtoList().forEach( x -> {
                SpecificationDto specificationDto = new SpecificationDto();
                specificationDto.setId(x.getId());
                specificationDtoList.add(specificationDto);
            });
        }
        templateGroupDto.setSpecificationDtoList(specificationDtoList);
        templateGroupDto.setExtraAttributesDtoList(templateGroupVo.getExtraAttributesDtoList());
       return templateService.updateTemplateGroup(templateGroupDto);

    }




}
