package com.square.mall.common.util;

import java.util.List;

/**
 * 列表工具类
 *
 * @author Gencent
 * @date 2019/8/20
 */
public class ListUtil {

    /**
     * 判断字符串是否不为空
     *
     * @param list 列表
     * @return boolean
     */
    public static boolean isNotNullAndEmpty(List list) {

        return null != list && !list.isEmpty();

    }

    /**
     * 判断列表是否为空
     *
     * @param list 列表
     * @return boolean
     */
    public static boolean isNullOrEmpty(List list) {

        return null == list || list.isEmpty();

    }

}
