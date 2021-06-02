package com.square.mall.item.center.biz.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.square.mall.common.dto.CommonPageRes;
import com.square.mall.common.util.ListUtil;
import com.square.mall.item.center.api.dto.TemplateDto;
import com.square.mall.item.center.biz.dao.TemplateDao;
import com.square.mall.item.center.biz.eo.TemplateEo;
import com.square.mall.item.center.biz.service.TemplateService;
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
            return 0;
        }

        TemplateEo templateEo = new TemplateEo();
        BeanUtils.copyProperties(templateDto, templateEo);
        int success = templateDao.insertTemplate(templateEo);
        templateDto.setId(templateEo.getId());

        return success;

    }

    @Override
    public int updateTemplate(TemplateDto templateDto) {

        if (null == templateDto) {
            log.error("templateDto is null.");
            return 0;
        }
        TemplateEo templateEo = new TemplateEo();
        BeanUtils.copyProperties(templateDto, templateEo);
        return templateDao.updateTemplate(templateEo);
    }

    @Override
    public int deleteTemplate(Long id) {

        if (null == id) {
            log.error("id is null.");
            return 0;
        }

        return templateDao.deleteTemplate(id);

    }

    @Override
    public List<TemplateDto> selectTemplateByCondition(TemplateDto templateDto) {
        return null;
    }

    @Override
    public CommonPageRes<List<TemplateDto>> selectPageTemplateByCondition(TemplateDto templateDto, Integer pageNum, Integer pageSize) {

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
        return new CommonPageRes<>(page.getTotal(), templateDtoList);
    }

    @Override
    public TemplateDto selectTemplateByName(String name) {
        return null;
    }

    @Override
    public TemplateDto selectTemplateById(Long id) {
        if (null == id) {
            log.info("id is null.");
            return null;
        }
        TemplateEo templateEo = templateDao.selectTemplateById(id);
        if (null == templateEo) {
            log.error("templateEo is null. id: {}", id);
            return null;
        }
        TemplateDto templateDto = new TemplateDto();
        BeanUtils.copyProperties(templateEo, templateDto);
        return templateDto;
    }
}
