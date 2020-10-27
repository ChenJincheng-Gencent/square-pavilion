package com.square.mall.trade.center.service.service.impl;

import com.square.mall.trade.center.api.dto.ShoppingCartDto;
import com.square.mall.trade.center.service.dao.ShoppingCartDao;
import com.square.mall.trade.center.service.eo.ShoppingCartEo;
import com.square.mall.trade.center.service.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 购物车Service实现类
 *
 * @author Gencent
 * @date 2020/10/26
 */
@Service
@Slf4j
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Resource
    private ShoppingCartDao shoppingCartDao;

    @Override
    public int insertShoppingCart(ShoppingCartDto shoppingCartDto) {

        if (null == shoppingCartDto) {
            log.error("shoppingCartDto is null.");
            return 0;
        }

        ShoppingCartEo shoppingCartEo = new ShoppingCartEo();
        BeanUtils.copyProperties(shoppingCartDto, shoppingCartEo);
        return shoppingCartDao.insertShoppingCart(shoppingCartEo);

    }

    @Override
    public int updateShoppingCart(ShoppingCartDto shoppingCartDto) {

        if (null == shoppingCartDto) {
            log.error("shoppingCartDto is null.");
            return 0;
        }
        ShoppingCartEo shoppingCartEo = new ShoppingCartEo();
        BeanUtils.copyProperties(shoppingCartDto, shoppingCartEo);
        return shoppingCartDao.updateShoppingCart(shoppingCartEo);
    }

    @Override
    public int deleteShoppingCart(Long memberId, Long itemId) {

        if (null == memberId || null == itemId) {
            log.error("memberId or itemId is null.");
            return 0;
        }

        return shoppingCartDao.deleteShoppingCart(memberId, itemId);

    }

    @Override
    public int batchDeleteShoppingCartList(Long memberId, Long[] itemIds) {
        return 0;
    }

    @Override
    public Integer selectItemNumByMemberAndItemId(Long memberId, Long itemId) {
        return shoppingCartDao.selectItemNumByMemberAndItemId(memberId, itemId);
    }

    @Override
    public List<ShoppingCartDto> selectShoppingCartList(Long memberId) {

        if (null == memberId) {
            log.error("memberId is null.");
            return null;
        }

        List<ShoppingCartDto> shoppingCartDtoList = new ArrayList<>();
        List<ShoppingCartEo> shoppingCartEoList = shoppingCartDao.selectShoppingCartList(memberId);
        if (null != shoppingCartEoList && shoppingCartEoList.size() > 0) {
            shoppingCartEoList.forEach( x -> {
                ShoppingCartDto shoppingCartDto = new ShoppingCartDto();
                BeanUtils.copyProperties(x, shoppingCartDto);
                shoppingCartDtoList.add(shoppingCartDto);
            });
        }

        return shoppingCartDtoList;
    }

}
