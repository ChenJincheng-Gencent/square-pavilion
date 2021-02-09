package com.square.mall.manager.application.service.impl;

import com.square.mall.common.dto.CommonPageRes;
import com.square.mall.common.dto.CommonRes;
import com.square.mall.item.center.api.TemplateApi;
import com.square.mall.item.center.api.dto.TemplateDto;
import com.square.mall.item.center.api.dto.TemplateGroupDto;
import com.square.mall.manager.application.service.TemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 模板Service实现类
 *
 * @author Gencent
 * @date 2020/7/31
 */
@Slf4j
@Service
public class TemplateServiceImpl implements TemplateService {


    @Resource
    private TemplateApi templateApi;

    @Override
    public CommonRes<Long> insertTemplateGroup(TemplateGroupDto templateGroupDto) {
        return templateApi.insertTemplateGroup(templateGroupDto);
    }

    @Override
    public CommonRes<TemplateGroupDto> selectTemplateGroupByTemplateId(Long templateId) {
        return templateApi.selectTemplateGroupByTemplateId(templateId);
    }

    @Override
    public CommonPageRes<List<TemplateGroupDto>> selectPageTemplateGroupByCondition(TemplateDto templateDto, Integer pageNum, Integer pageSize) {
        return templateApi.selectPageTemplateGroupByCondition(templateDto, pageNum, pageSize);
    }

    @Override
    public CommonRes<Void> updateTemplateGroup(TemplateGroupDto templateGroupDto) {
        return templateApi.updateTemplateGroup(templateGroupDto);
    }

    @Override
    public CommonRes<Void> batchDeleteTemplateGroup(Long[] ids) {
        return templateApi.batchDeleteTemplateGroup(ids);
    }

}
