package com.square.mall.item.center.service.apiimpl;

import com.square.mall.item.center.api.TemplateSpecificationApi;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 模板规格API实现类
 *
 * @author Gencent
 * @date 2020/7/28
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class TemplateSpecificationApiImpl implements TemplateSpecificationApi {
}
