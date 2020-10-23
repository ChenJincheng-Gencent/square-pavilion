package com.square.mall.item.center.service.apiimpl.query;

import com.square.mall.common.dto.PageRspDto;
import com.square.mall.common.dto.RspDto;
import com.square.mall.common.util.ListUtil;
import com.square.mall.item.center.api.dto.BrandDto;
import com.square.mall.item.center.api.dto.SpecificationDto;
import com.square.mall.item.center.api.dto.TemplateDto;
import com.square.mall.item.center.api.dto.TemplateGroupDto;
import com.square.mall.item.center.api.query.TemplateQueryApi;
import com.square.mall.item.center.service.service.*;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 模板查询API实现类
 *
 * @author Gencent
 * @date 2020/7/31
 */
@Slf4j

public class TemplateQueryApiImpl implements TemplateQueryApi {

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

    @Override
    public RspDto<TemplateGroupDto> selectTemplateGroupByTemplateId(Long templateId) {

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

    @Override
    public PageRspDto<List<TemplateGroupDto>> selectPageTemplateGroupByCondition(TemplateDto templateDto, Integer pageNum,
        Integer pageSize) {

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
