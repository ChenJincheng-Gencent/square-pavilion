package com.square.mall.item.center.service.apiimpl;

import com.square.mall.common.dto.RspDto;
import com.square.mall.common.util.*;
import com.square.mall.item.center.api.SpecificationApi;
import com.square.mall.item.center.api.dto.SpecificationDto;
import com.square.mall.item.center.api.dto.SpecificationGroupDto;
import com.square.mall.item.center.api.dto.SpecificationOptionDto;
import com.square.mall.item.center.service.service.SpecificationOptionService;
import com.square.mall.item.center.service.service.SpecificationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 规格API实现类
 *
 * @author Gencent
 * @date 2020/7/27
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class SpecificationApiImpl implements SpecificationApi {

    @Resource
    private SpecificationService specificationService;

    @Resource
    private SpecificationOptionService specificationOptionService;

    @Override
    public RspDto<Long> insertSpecificationGroup(SpecificationGroupDto specificationGroupDto) {

        if (null == specificationGroupDto) {
            log.error("specificationGroupDto is null.");
            return DatabaseUtil.getResult(DatabaseOptConstant.DATABASE_PARA_ILLEGAL, ModuleConstant.ITEM_CENTER);
        }

        SpecificationDto specificationDto = specificationGroupDto.getSpecificationDto();
        int success = specificationService.insertSpecification(specificationDto);
        List<SpecificationOptionDto> specificationOptionDtoList = specificationGroupDto.getSpecificationOptionDtoList();
        if (ListUtil.isBlank(specificationOptionDtoList) || DatabaseOptConstant.DATABASE_OPT_SUCCESS != success) {
            return DatabaseUtil.getResult(success, specificationDto.getId(), ModuleConstant.ITEM_CENTER);
        }
        specificationOptionDtoList.forEach( x -> {
            x.setSpecId(specificationDto.getId());
            specificationOptionService.insertSpecificationOption(x);
        });
        return DatabaseUtil.getResult(success, specificationDto.getId(), ModuleConstant.ITEM_CENTER);
    }

    @Override
    public RspDto updateSpecificationGroup(SpecificationGroupDto specificationGroupDto) {

        if (null == specificationGroupDto) {
            log.error("specificationGroupDto is null.");
            return DatabaseUtil.getResult(DatabaseOptConstant.DATABASE_PARA_ILLEGAL, ModuleConstant.ITEM_CENTER);
        }
        int success = specificationService.updateSpecification(specificationGroupDto.getSpecificationDto());
        if (DatabaseOptConstant.DATABASE_OPT_SUCCESS != success) {
            return DatabaseUtil.getResult(success, ModuleConstant.ITEM_CENTER);
        }
        List<SpecificationOptionDto> specificationOptionDtoList = specificationGroupDto.getSpecificationOptionDtoList();
        if (null != specificationOptionDtoList) {
            Long[] ids = new Long[specificationOptionDtoList.size()];
            for (int i = 0; i < specificationOptionDtoList.size(); i++) {
                ids[i] = specificationOptionDtoList.get(i).getId();
            }
            int batchSuccess = specificationOptionService.batchDeleteSpecificationOption(ids);
            if (DatabaseOptConstant.DATABASE_OPT_SUCCESS != batchSuccess) {
                return DatabaseUtil.getResult(batchSuccess, ModuleConstant.ITEM_CENTER);
            }
            specificationOptionDtoList.forEach( x -> {
                x.setSpecId(specificationGroupDto.getSpecificationDto().getId());
                specificationOptionService.insertSpecificationOption(x);
            });
        }

        return DatabaseUtil.getResult(success, ModuleConstant.ITEM_CENTER);

    }

    @Override
    public RspDto batchDeleteSpecificationGroup(Long[] ids) {

        if (null == ids) {
            log.error("ids is null.");
            return DatabaseUtil.getResult(DatabaseOptConstant.DATABASE_PARA_ILLEGAL, ModuleConstant.ITEM_CENTER);
        }
        for (Long id : ids) {
            int optionSuccess = specificationOptionService.deleteSpecificationOptionBySpecId(id);
            if (DatabaseOptConstant.DATABASE_OPT_SUCCESS != optionSuccess) {
                return DatabaseUtil.getResult(optionSuccess, ModuleConstant.ITEM_CENTER);
            }
            int success = specificationService.deleteSpecification(id);
            if (DatabaseOptConstant.DATABASE_OPT_SUCCESS != success) {
                return DatabaseUtil.getResult(success, ModuleConstant.ITEM_CENTER);
            }
        }
        return DatabaseUtil.getResult(DatabaseOptConstant.DATABASE_OPT_SUCCESS, ModuleConstant.ITEM_CENTER);
    }
}
