package com.square.mall.common.util;

import java.util.Collection;
import java.util.List;

/**
 * 列表工具类
 *
 * @author Gencent
 * @date 2019/8/20
 */
public class ListUtil {

    /**
     * 判断集合是否不为空
     *
     * @param collection 集合
     * @return 是否不为空
     */
    public static boolean isNotBlank(final Collection<?> collection) {

        return null != collection && !collection.isEmpty();

    }

    /**
     * 判断集合是否为空
     *
     * @param collection 集合
     * @return boolean
     */
    public static boolean isBlank(final Collection<?> collection) {

        return null == collection || collection.isEmpty();

    }


}
