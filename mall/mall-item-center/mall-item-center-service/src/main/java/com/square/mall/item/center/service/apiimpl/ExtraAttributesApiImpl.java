package com.square.mall.item.center.service.apiimpl;

import com.square.mall.item.center.api.ExtraAttributesApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

/**
 * 扩展属性API实现类
 *
 * @author Gencent
 * @date 2020/7/28
 */

@Slf4j
@Transactional(rollbackFor = Exception.class)
public class ExtraAttributesApiImpl implements ExtraAttributesApi {
}
