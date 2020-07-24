package com.square.mall.common.util;

import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * 列表工具类
 *
 * @author Gencent
 * @date 2019/8/20
 */
public class ListUtil {

    /**
     * 判断列表是否不为空
     *
     * @param list 列表
     * @return 是否不为空
     */
    public static boolean isNotBlank(List list) {

        return null != list && !list.isEmpty();

    }

    /**
     * 判断列表是否为空
     *
     * @param list 列表
     * @return boolean
     */
    public static boolean isBlank(List list) {

        return null == list || list.isEmpty();

    }

    /**
     * List拷贝
     * @param source 来源列表
     * @param target 目标列表
     */
    public static void copyList(List source, List target) {

        if (isBlank(source) || null == target) {
            return;
        }

        for (int i = 0; i < source.size(); i++) {
            BeanUtils.copyProperties(source.get(i), target.get(i));
        }

    }

}
