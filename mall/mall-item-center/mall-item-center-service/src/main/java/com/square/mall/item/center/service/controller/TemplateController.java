package com.square.mall.item.center.service.controller;

import com.square.mall.common.dto.PageRspDto;
import com.square.mall.common.dto.RspDto;
import com.square.mall.common.util.DatabaseOptConstant;
import com.square.mall.common.util.DatabaseUtil;
import com.square.mall.common.util.ListUtil;
import com.square.mall.common.util.ModuleConstant;
import com.square.mall.item.center.api.dto.*;
import com.square.mall.item.center.service.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 模板Controller
 *
 * @author Gencent
 * @date 2020/10/26
 */
@RestController
@RequestMapping("/template")
@Slf4j
public class TemplateController {

    @Resource
    private TemplateService templateService;

    @Resource
    private TemplateBrandService templateBrandService;

    @Resource
    private TemplateSpecificationService templateSpecificationService;

    @Resource
    private ExtraAttributesService extraAttributesService;

    @Resource
    private BrandService brandService;

    @Resource
    private SpecificationService specificationService;

    /**
     * 插入模板组合
     *
     * @param templateGroupDto 模板组合
     * @return 数据库ID
     */
    @PostMapping("/group")
    public RspDto<Long> insertTemplateGroup(@RequestBody TemplateGroupDto templateGroupDto) {

        if (null == templateGroupDto) {
            log.error("templateGroupDto is null.");
            return DatabaseUtil.getResult(DatabaseOptConstant.DATABASE_PARA_ILLEGAL, ModuleConstant.ITEM_CENTER);
        }

        TemplateDto templateDto = templateGroupDto.getTemplateDto();
        templateService.insertTemplate(templateDto);
        List<BrandDto> brandDtoList = templateGroupDto.getBrandDtoList();
        if (ListUtil.isNotBlank(brandDtoList)) {
            brandDtoList.forEach( x -> {
                TemplateBrandDto templateBrandDto = new TemplateBrandDto();
                templateBrandDto.setTemplateId(templateDto.getId());
                templateBrandDto.setBrandId(x.getId());
                log.info("templateBrandDto: {}", templateBrandDto);
                templateBrandService.insertTemplateBrand(templateBrandDto);
            });
        }
        List<SpecificationDto> specificationDtoList = templateGroupDto.getSpecificationDtoList();
        if (ListUtil.isNotBlank(specificationDtoList)) {
            specificationDtoList.forEach( x -> {
                TemplateSpecificationDto templateSpecificationDto = new TemplateSpecificationDto();
                templateSpecificationDto.setTemplateId(templateDto.getId());
                templateSpecificationDto.setSpecId(x.getId());
                log.info("templateSpecificationDto: {}", templateSpecificationDto);
                templateSpecificationService.insertTemplateSpecification(templateSpecificationDto);
            });
        }

        List<ExtraAttributesDto> extraAttributesDtoList = templateGroupDto.getExtraAttributesDtoList();
        if (ListUtil.isNotBlank(extraAttributesDtoList)) {
            extraAttributesDtoList.forEach( x -> {
                x.setTemplateId(templateDto.getId());
                extraAttributesService.insertExtraAttributes(x);
            });
        }
        return new RspDto<>(templateDto.getId());
    }

    /**
     * 更新模板组合
     *
     * @param templateGroupDto 模板组合
     * @return 响应
     */
    @PutMapping("/group")
    public RspDto updateTemplateGroup(@RequestBody TemplateGroupDto templateGroupDto) {

        if (null == templateGroupDto) {
            log.error("templateGroupDto is null.");
            return DatabaseUtil.getResult(DatabaseOptConstant.DATABASE_PARA_ILLEGAL, ModuleConstant.ITEM_CENTER);
        }

        TemplateDto templateDto = templateGroupDto.getTemplateDto();
        int success = templateService.updateTemplate(templateDto);
        if (DatabaseOptConstant.DATABASE_OPT_SUCCESS != success) {
            return DatabaseUtil.getResult(success, ModuleConstant.ITEM_CENTER);
        }

        templateBrandService.deleteTemplateBrandByTemplateId(templateDto.getId());
        List<BrandDto> brandDtoList = templateGroupDto.getBrandDtoList();
        if (ListUtil.isNotBlank(brandDtoList)) {
            brandDtoList.forEach( x -> {
                TemplateBrandDto templateBrandDto = new TemplateBrandDto();
                templateBrandDto.setBrandId(x.getId());
                templateBrandDto.setTemplateId(templateDto.getId());
                templateBrandService.insertTemplateBrand(templateBrandDto);
            });
        }

        templateSpecificationService.deleteTemplateSpecificationByTemplateId(templateDto.getId());
        List<SpecificationDto> specificationDtoList = templateGroupDto.getSpecificationDtoList();
        if (ListUtil.isNotBlank(specificationDtoList)) {
            specificationDtoList.forEach( x -> {
                TemplateSpecificationDto templateSpecificationDto = new TemplateSpecificationDto();
                templateSpecificationDto.setSpecId(x.getId());
                templateSpecificationDto.setTemplateId(templateDto.getId());
                templateSpecificationService.insertTemplateSpecification(templateSpecificationDto);
            });
        }

        extraAttributesService.deleteExtraAttributesByTemplateId(templateDto.getId());
        List<ExtraAttributesDto> extraAttributesDtoList = templateGroupDto.getExtraAttributesDtoList();
        if (ListUtil.isNotBlank(extraAttributesDtoList)) {
            extraAttributesDtoList.forEach( x -> {
                x.setTemplateId(templateDto.getId());
                extraAttributesService.insertExtraAttributes(x);
            });
        }
        return RspDto.SUCCESS;
    }

    /**
     * 批量删除模板组合
     *
     * @param ids 模板ID数组
     * @return 响应
     */
    @DeleteMapping("/group/batch/ids")
    public RspDto batchDeleteTemplateGroup(@RequestParam("ids") Long[] ids) {

        if (null == ids) {
            log.error("ids is null.");
            return DatabaseUtil.getResult(DatabaseOptConstant.DATABASE_PARA_ILLEGAL, ModuleConstant.ITEM_CENTER);
        }
        for (Long id : ids) {
            templateBrandService.deleteTemplateBrandByTemplateId(id);
            templateSpecificationService.deleteTemplateSpecificationByTemplateId(id);
            extraAttributesService.deleteExtraAttributesByTemplateId(id);
            templateService.deleteTemplate(id);
        }

        return RspDto.SUCCESS;
    }

    /**
     * 根据模板ID查询模板组合
     *
     * @param templateId 模板ID
     * @return 模板组合
     */
    @GetMapping("/group/template-id")
    public RspDto<TemplateGroupDto> selectTemplateGroupByTemplateId(@RequestParam("templateId") Long templateId) {

        TemplateDto templateDto = templateService.selectTemplateById(templateId);
        if (null == templateDto) {
            log.error("templateDto is null. templateId: {}", templateId);
            return RspDto.SUCCESS;
        }

        TemplateGroupDto templateGroupDto = new TemplateGroupDto();
        templateGroupDto.setTemplateDto(templateDto);

        List<Long> brandIds = templateBrandService.selectBrandIdByTemplateId(templateId);
        if (ListUtil.isNotBlank(brandIds)) {
            List<BrandDto> brandDtoList = new ArrayList<>();
            brandIds.forEach( x -> {
                BrandDto brandDto = brandService.selectBrandById(x);
                brandDtoList.add(brandDto);
            });
            templateGroupDto.setBrandDtoList(brandDtoList);
        }

        List<Long> specIds = templateSpecificationService.selectSpecIdByTemplateId(templateId);
        if (ListUtil.isNotBlank(specIds)) {
            List<SpecificationDto> specificationDtoList = new ArrayList<>();
            specIds.forEach( x -> {
                SpecificationDto specificationDto = specificationService.selectSpecificationById(x);
                specificationDtoList.add(specificationDto);
            });
            templateGroupDto.setSpecificationDtoList(specificationDtoList);
        }

        templateGroupDto.setExtraAttributesDtoList(extraAttributesService.selectExtraAttributesByTemplateId(templateId));

        return new RspDto<>(templateGroupDto);

    }

    /**
     * 分页条件查询模板组合列表
     *
     * @param templateDto 查询条件
     * @param pageNum 当前页
     * @param pageSize 分页大小
     * @return 模板组合列表
     */
    @PostMapping("/group/list/page/condition")
    public PageRspDto<List<TemplateGroupDto>> selectPageTemplateGroupByCondition(@RequestBody TemplateDto templateDto,
                                 @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {

        PageRspDto<List<TemplateDto>> listPageRspDto = templateService.selectPageTemplateByCondition(templateDto, pageNum,
                pageSize);
        List<TemplateDto> templateDtoList = listPageRspDto.getData();
        if (ListUtil.isBlank(templateDtoList)) {
            log.error("templateDtoList is blank. templateDto: {}, pageNum: {}, pageSize: {}", templateDto, pageNum, pageSize);
            return new PageRspDto<>(listPageRspDto.getTotal(), null);
        }

        List<TemplateGroupDto> templateGroupDtoList = new ArrayList<>();
        templateDtoList.forEach(x -> {
            TemplateGroupDto templateGroupDto = new TemplateGroupDto();
            templateGroupDto.setTemplateDto(x);

            List<Long> brandIds = templateBrandService.selectBrandIdByTemplateId(x.getId());
            if (ListUtil.isNotBlank(brandIds)) {
                List<BrandDto> brandDtoList = new ArrayList<>();
                brandIds.forEach( y -> {
                    BrandDto brandDto = brandService.selectBrandById(y);
                    brandDtoList.add(brandDto);
                });
                templateGroupDto.setBrandDtoList(brandDtoList);
            }

            List<Long> specIds = templateSpecificationService.selectSpecIdByTemplateId(x.getId());
            if (ListUtil.isNotBlank(specIds)) {
                List<SpecificationDto> specificationDtoList = new ArrayList<>();
                specIds.forEach( y -> {
                    SpecificationDto specificationDto = specificationService.selectSpecificationById(y);
                    specificationDtoList.add(specificationDto);
                });
                templateGroupDto.setSpecificationDtoList(specificationDtoList);
            }

            templateGroupDto.setExtraAttributesDtoList(extraAttributesService.selectExtraAttributesByTemplateId(x.getId()));
            templateGroupDtoList.add(templateGroupDto);
        });

        return new PageRspDto<>(listPageRspDto.getTotal(), templateGroupDtoList);

    }
}
