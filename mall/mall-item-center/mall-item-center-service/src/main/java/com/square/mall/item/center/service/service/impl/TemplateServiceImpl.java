package com.square.mall.item.center.service.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.square.mall.common.dto.PageRspDto;
import com.square.mall.common.util.DatabaseOptConstant;
import com.square.mall.common.util.DatabaseUtil;
import com.square.mall.common.util.ModuleConstant;
import com.square.mall.common.util.ListUtil;
import com.square.mall.item.center.api.dto.SpecificationDto;
import com.square.mall.item.center.api.dto.TemplateDto;
import com.square.mall.item.center.service.dao.TemplateDao;
import com.square.mall.item.center.service.eo.TemplateEo;
import com.square.mall.item.center.service.dao.TemplateDao;
import com.square.mall.item.center.service.eo.SpecificationEo;
import com.square.mall.item.center.service.eo.TemplateEo;
import com.square.mall.item.center.service.service.TemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 模板Service实现类
 *
 * @author Gencent
 * @date 2020/7/28
 */
@Service
@Slf4j
public class TemplateServiceImpl implements TemplateService {

    @Resource
    private TemplateDao templateDao;

    @Override
    public int insertTemplate(TemplateDto templateDto) {

        if (null == templateDto) {
            log.error("templateDto is null.");
            return DatabaseOptConstant.DATABASE_PARA_ILLEGAL;
        }

        TemplateEo templateEo = new TemplateEo();
        BeanUtils.copyProperties(templateDto, templateEo);
        int success = templateDao.insertTemplate(templateEo);
        templateDto.setId(templateEo.getId());

        return success;

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

        pageNum = null == pageNum ? 1 : pageNum;
        pageSize = null == pageSize ? 10 : pageSize;
        String orderBy = "create_time" + " desc";
        PageHelper.startPage(pageNum, pageSize, orderBy);
        TemplateEo templateEo = new TemplateEo();
        if (null != templateDto) {
            BeanUtils.copyProperties(templateDto, templateEo);
        }
        Page<TemplateEo> page = (Page<TemplateEo>) templateDao.selectTemplateByCondition(templateEo);
        List<TemplateDto> templateDtoList = new ArrayList<>();
        if (ListUtil.isNotBlank(page.getResult())) {
            page.getResult().forEach( x -> {
                TemplateDto templateDtoTemp = new TemplateDto();
                BeanUtils.copyProperties(x, templateDtoTemp);
                templateDtoList.add(templateDtoTemp);
            });
        }
        return new PageRspDto<>(page.getTotal(), templateDtoList);
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
