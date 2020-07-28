package com.square.mall.item.center.service.service.impl;

import com.square.mall.common.dto.PageRspDto;
import com.square.mall.item.center.api.dto.TemplateDto;
import com.square.mall.item.center.service.service.TemplateService;

import java.util.List;

/**
 * 模板Service实现类
 *
 * @author Gencent
 * @date 2020/7/28
 */
public class TemplateServiceImpl implements TemplateService {
    @Override
    public int insertTemplate(TemplateDto templateDto) {
        return 0;
    }

    @Override
    public int updateTemplate(TemplateDto templateDto) {
        return 0;
    }

    @Override
    public int deleteTemplate(Long id) {
        return 0;
    }

    @Override
    public List<TemplateDto> selectTemplateByCondition(TemplateDto templateDto) {
        return null;
    }

    @Override
    public PageRspDto<List<TemplateDto>> selectPageTemplateByCondition(TemplateDto templateDto, Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public TemplateDto selectTemplateByName(String name) {
        return null;
    }

    @Override
    public TemplateDto selectTemplateById(Long id) {
        return null;
    }
}
