package com.square.mall.trade.center.service.facade.impl;

import com.square.mall.trade.center.service.facade.OrderFacade;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 订单门面实现类
 *
 * @author Gencent
 * @date 2021/1/4
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OrderFacadeImpl implements OrderFacade {
}
