package com.square.mall.item.center.service.apiimpl;

import com.square.mall.common.dto.RspDto;
import com.square.mall.common.util.DatabaseOptConstant;
import com.square.mall.common.util.DatabaseUtil;
import com.square.mall.common.util.ListUtil;
import com.square.mall.common.util.ModuleConstant;
import com.square.mall.item.center.api.TemplateApi;
import com.square.mall.item.center.api.dto.*;
import com.square.mall.item.center.service.dao.TemplateBrandDao;
import com.square.mall.item.center.service.service.ExtraAttributesService;
import com.square.mall.item.center.service.service.TemplateBrandService;
import com.square.mall.item.center.service.service.TemplateService;
import com.square.mall.item.center.service.service.TemplateSpecificationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 模板API实现类
 *
 * @author Gencent
 * @date 2020/7/28
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class TemplateApiImpl implements TemplateApi {

    @Resource
    private TemplateService templateService;

    @Resource
    private TemplateBrandService templateBrandService;

    @Resource
    private TemplateSpecificationService templateSpecificationService;

    @Resource
    private ExtraAttributesService extraAttributesService;

    @Override
    public RspDto<Long> insertTemplateGroup(TemplateGroupDto templateGroupDto) {

        if (null == templateGroupDto) {
            log.error("templateGroupDto is null.");
            return DatabaseUtil.getResult(DatabaseOptConstant.DATABASE_PARA_ILLEGAL, ModuleConstant.ITEM_CENTER);
        }

        TemplateDto templateDto = templateGroupDto.getTemplateDto();
        int success = templateService.insertTemplate(templateDto);
        if (DatabaseOptConstant.DATABASE_OPT_SUCCESS != success) {
            return DatabaseUtil.getResult(success, ModuleConstant.ITEM_CENTER);
        }
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
}
