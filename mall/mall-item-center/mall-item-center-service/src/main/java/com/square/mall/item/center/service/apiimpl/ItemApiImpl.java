package com.square.mall.item.center.service.apiimpl;

import com.square.mall.item.center.api.ItemApi;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *  商品API实现类
 *
 * @author Gencent
 * @date 2020/7/23
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class ItemApiImpl implements ItemApi {
}
