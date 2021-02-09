package com.square.mall.item.center.service.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.square.mall.common.dto.PageRspDto;
import com.square.mall.common.util.ListUtil;
import com.square.mall.common.util.StringUtil;
import com.square.mall.item.center.api.dto.ItemDto;
import com.square.mall.item.center.service.dao.ItemDao;
import com.square.mall.item.center.service.eo.ItemEo;
import com.square.mall.item.center.service.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 商品Service实现类
 *
 * @author Gencent
 * @date 2020/7/29
 */
@Slf4j
@Service
public class ItemServiceImpl implements ItemService {

    @Resource
    private ItemDao itemDao;

    @Override
    public int insertItem(ItemDto itemDto) {
        if (null == itemDto || StringUtil.isBlank(itemDto.getName())) {
            log.error("itemDto or name is null or blank.");
            return 0;

        }
        ItemEo oldItemEo = itemDao.selectItemByName(itemDto.getName());
        if (null != oldItemEo) {
            log.error("oldItemEo already exist. itemDto: {}", itemDto);
            return 0;
        }
        ItemEo itemEo = new ItemEo();
        BeanUtils.copyProperties(itemDto, itemEo);
        int success = itemDao.insertItem(itemEo);
        itemDto.setId(itemEo.getId());
        return success;
    }

    @Override
    public int updateItem(ItemDto itemDto) {

        if (null == itemDto || null == itemDto.getId()) {
            log.error("itemDto or id is null");
            return 0;
        }
        ItemEo oldItemEo = itemDao.selectItemById(itemDto.getId());
        if (null == oldItemEo) {
            log.error("oldItemEo is null. id: {}", itemDto.getId());
            return 0;
        }
        ItemEo itemEo = new ItemEo();
        return itemDao.updateItem(itemEo);
    }

    @Override
    public int deleteItem(Long id) {
        if (null == id) {
            log.error("id is null.");
            return 0;
        }

        ItemEo itemEo = itemDao.selectItemById(id);
        if (null == itemEo) {
            log.error("itemEo is null. id: {}", id);
            return 0;
        }

        return itemDao.deleteItem(id);
    }

    @Override
    public List<ItemDto> selectItemByCondition(ItemDto itemDto) {
        ItemEo itemEo = new ItemEo();
        BeanUtils.copyProperties(itemDto, itemEo);
        List<ItemEo> itemEoList = itemDao.selectItemByCondition(itemEo);
        List<ItemDto> itemDtoList = new ArrayList<>();
        if (ListUtil.isNotBlank(itemEoList)) {
            itemEoList.forEach( x -> {
                ItemDto itemDtoTemp = new ItemDto();
                BeanUtils.copyProperties(x, itemDtoTemp);
                itemDtoList.add(itemDtoTemp);
            });
        }
        return itemDtoList;
    }

    @Override
    public PageRspDto<List<ItemDto>> selectPageItemByCondition(ItemDto itemDto, Integer pageNum, Integer pageSize) {
        pageNum = null == pageNum ? 1 : pageNum;
        pageSize = null == pageSize ? 10 : pageSize;
        String orderBy = "create_time" + " desc";
        PageHelper.startPage(pageNum, pageSize, orderBy);
        ItemEo itemEo = new ItemEo();
        BeanUtils.copyProperties(itemDto, itemEo);
        Page<ItemEo> page = (Page<ItemEo>) itemDao.selectItemByCondition(itemEo);
        List<ItemDto> itemDtoList = new ArrayList<>();
        if (ListUtil.isNotBlank(page.getResult())) {
            page.getResult().forEach( x -> {
                ItemDto itemDtoTemp = new ItemDto();
                BeanUtils.copyProperties(x, itemDtoTemp);
                itemDtoList.add(itemDtoTemp);
            });
        }
        return new PageRspDto<>(page.getTotal(), itemDtoList);
    }

    @Override
    public ItemDto selectItemByName(String name) {
        if (StringUtil.isBlank(name)) {
            log.error("name is blank.");
            return null;
        }
        ItemDto itemDto = new ItemDto();
        ItemEo itemEo = itemDao.selectItemByName(name);
        if (null == itemEo) {
            log.error("itemEo is null. name: {}", name);
            return null;
        }
        BeanUtils.copyProperties(itemEo, itemDto);
        return itemDto;
    }

    @Override
    public ItemDto selectItemById(Long id) {
        if (null == id) {
            log.error("id is null.");
            return null;
        }
        ItemDto itemDto = new ItemDto();
        ItemEo itemEo = itemDao.selectItemById(id);
        if (null == itemEo) {
            log.error("itemEo is null. id: {}", id);
            return null;
        }
        BeanUtils.copyProperties(itemEo, itemDto);
        return itemDto;
    }
}
