package com.square.mall.manager.application.service.impl;

import com.square.mall.common.dto.PageRspDto;
import com.square.mall.common.dto.RspDto;
import com.square.mall.item.center.api.TemplateApi;
import com.square.mall.item.center.api.dto.TemplateDto;
import com.square.mall.item.center.api.dto.TemplateGroupDto;
import com.square.mall.item.center.api.query.TemplateQueryApi;
import com.square.mall.manager.application.service.TemplateService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

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

    @Reference
    private TemplateQueryApi templateQueryApi;

    @Reference
    private TemplateApi templateApi;

    @Override
    public RspDto<Long> insertTemplateGroup(TemplateGroupDto templateGroupDto) {
        return templateApi.insertTemplateGroup(templateGroupDto);
    }

    @Override
    public RspDto<TemplateGroupDto> selectTemplateGroupByTemplateId(Long templateId) {
        return templateQueryApi.selectTemplateGroupByTemplateId(templateId);
    }

    @Override
    public PageRspDto<List<TemplateGroupDto>> selectPageTemplateGroupByCondition(TemplateDto templateDto, Integer pageNum, Integer pageSize) {
        return templateQueryApi.selectPageTemplateGroupByCondition(templateDto, pageNum, pageSize);
    }

}
