package com.square.mall.common.util;

import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 *  MD5工具类
 *
 * @author Gencent
 * @date 2020/7/13
 */
@Slf4j
public class Md5Util {

    /**
     * 获取字符串的MD5值
     *
     * @param str 字符串
     * @return 该字符串的MD5值
     */
    public static String getMd5(String str) {

        if (StringUtil.isBlank(str)) {
            return "";
        }

        try {
            //获取 MessageDigest 对象，参数为 MD5 字符串，表示这是一个 MD5 算法（其他还有 SHA1 算法等）：
            MessageDigest md5 = MessageDigest.getInstance(CommonConstant.MD5);
            //update(byte[])方法，输入原数据
            //类似StringBuilder对象的append()方法，追加模式，属于一个累计更改的过程
            md5.update(str.getBytes(StandardCharsets.UTF_8));
            //digest()被调用后,MessageDigest对象就被重置，即不能连续再次调用该方法计算原数据的MD5值。
            //可以手动调用reset()方法重置输入源。
            //digest()返回值16位长度的哈希值，由byte[]承接
            byte[] md5Array = md5.digest();
            //byte[]通常我们会转化为十六进制的32位长度的字符串来使用,本文会介绍三种常用的转换方法
            if (null != md5Array) {
                return StringUtil.getBytesToHexStr(md5Array);
            } else {
                return "";
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return "";
        }
    }

}
