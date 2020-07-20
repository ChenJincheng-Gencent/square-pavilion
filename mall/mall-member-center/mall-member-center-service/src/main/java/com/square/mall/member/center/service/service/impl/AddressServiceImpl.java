package com.square.mall.member.center.service.service.impl;

import com.square.mall.common.util.ListUtil;
import com.square.mall.member.center.api.dto.AddressDto;
import com.square.mall.member.center.service.dao.AddressDao;
import com.square.mall.member.center.service.eo.AddressEo;
import com.square.mall.member.center.service.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 收货地址Service实现类
 *
 * @author Gencent
 * @date 2020/7/20
 */
@Service
@Slf4j
public class AddressServiceImpl implements AddressService {

    @Resource
    private AddressDao addressDao;

    @Override
    public List<AddressDto> selectAddressByMemberId(Long memberId) {

        if (null == memberId) {
            log.error("memberId is null.");
            return null;
        }

        List<AddressEo> addressEos = addressDao.selectAddressByMemberId(memberId);
        if (ListUtil.isBlank(addressEos)) {
            log.error("addressEos is blank. memberId: {}", memberId);
            return null;
        }

        List<AddressDto> addressDtoList = new ArrayList<>();
        addressEos.forEach(x -> {
            AddressDto addressDto = new AddressDto();
            BeanUtils.copyProperties(x, addressDto);
            addressDtoList.add(addressDto);
        });
        return addressDtoList;

    }

    @Override
    public Long insertAddress(AddressDto addressDto) {

        if (null == addressDto) {
            log.error("addressDto is null.");
            return 0L;
        }

        AddressEo addressEo = new AddressEo();
        BeanUtils.copyProperties(addressDto, addressEo);
        addressDao.insertAddress(addressEo);
        return addressEo.getId();

    }

    @Override
    public void updateAddress(AddressDto addressDto) {

        if (null == addressDto || null == addressDto.getId()) {
            log.error("addressDto or id is null.");
            return;
        }
        AddressEo addressEo = new AddressEo();
        BeanUtils.copyProperties(addressDto, addressEo);
        addressDao.updateAddress(addressEo);

    }
}
