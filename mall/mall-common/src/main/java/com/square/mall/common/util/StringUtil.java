package com.square.mall.common.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * 字符串工具类
 *
 * @author Gencent
 * @date 2019/8/20
 */
public class StringUtil {

    /**
     * HMAC-SHA1算法名称
     */
    private final static String HMAC_ALGORITHM = "HmacSHA1";

    /**
     * 获取字符串中的第一个汉字
     *
     * @param str 带有汉字的字符串
     * @return String
     */
    public static String getFirstChineseCharacter(String str) {

        if (null == str || str.isEmpty()) {
            return null;
        }

        char[] words = str.toCharArray();

        return String.valueOf(words[0]);

    }

    /**
     * 获取字符串中的第一个汉字和特定索引的汉字
     *
     * @param str 带有汉字的字符串
     * @return String
     */
    public static String getFirstAndIndexChineseCharacter(String str, Integer index) {

        if (null == str || str.isEmpty()) {
            return null;
        }

        char[] words = str.toCharArray();
        if (null == index || index.equals(CommonConstant.ZER0)) {
            return String.valueOf(words[0]);
        }
        if (words.length <= index) {
            return String.valueOf(words[0]) + words[words.length - 1];
        } else {
            return String.valueOf(words[0]) + words[index];
        }

    }

    /**
     * 判断字符串是否不为空
     *
     * @param str 字符串
     * @return boolean
     */
    public static boolean isNotBlank(String str) {

        return null != str && !str.isEmpty();

    }

    /**
     * 判断字符串是否为空
     *
     * @param str 字符串
     * @return boolean
     */
    public static boolean isBlank(String str) {

        return null == str || str.isEmpty();

    }

    /**
     * 获取String的MD5值
     *
     * @param str 字符串
     * @return 该字符串的MD5值
     */
    public static String getMd5(String str) {

        if (isBlank(str)) {
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
                return bytesToHex1(md5Array);
            } else {
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }

    /**
     * 完成byte->HexString
     *
     * @param md5Array MD5数组
     * @return String
     */
    private static String bytesToHex1(byte[] md5Array) {

        StringBuilder strBuilder = new StringBuilder();

        for (byte md5 : md5Array) {
            //此处为什么添加 0xff & ？
            int temp = 0xff & md5;
            String hexString = Integer.toHexString(temp);
            //如果是十六进制的0f，默认只显示f，此时要补上0
            if (hexString.length() == CommonConstant.ONE) {
                strBuilder.append("0").append(hexString);
            } else {
                strBuilder.append(hexString);
            }
        }

        return strBuilder.toString();

    }

    /**
     * 获取根据冒号分隔后的第一个字符串
     *
     * @param str 字符串
     * @return String
     */
    public static String getFirstStrSplitByColon(String str) {

        if (isBlank(str)) {
            return null;
        }

        String[] strings = str.split(SymbolConstant.COLON);
        if (strings.length > 0) {
            return strings[0];
        }

        return null;

    }

    /**
     * 获取根据竖线分隔后的第一个字符串
     *
     * @param str 字符串
     * @return String
     */
    public static String getSecondStrSplitByVerticalBar(String str) {

        if (isBlank(str)) {

            return null;

        }

        String[] strings = str.split(SymbolConstant.VERTICAL_BAR_ESCAPE);
        if (CommonConstant.TWO <= strings.length) {

            return strings[1];

        }

        return null;

    }

}
