package com.square.mall.common.util;

import com.square.mall.common.constant.CommonConstant;
import com.square.mall.common.constant.SymbolConstant;

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
     * 字符数组转Hex字符串
     *
     * @param bytes 字符数组
     * @return Hex字符串
     */
    public static String getBytesToHexStr(byte[] bytes) {

        StringBuffer sb = new StringBuffer();
        for (byte b : bytes) {
            String hex = Integer.toHexString(b & 0xFF);
            if (hex.length() == CommonConstant.ONE) {
                sb.append(CommonConstant.ZER0_STR).append(hex);
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    /**
     *  Hex字符串转字符数组
     *
     * @param hexStr Hex字符串
     * @return 字符数组
     */
    public static byte[] getHexStrToBytes(String hexStr) {

        if (isBlank(hexStr)) {
            return null;
        }

        hexStr= hexStr.toUpperCase();
        int length = hexStr.length() / 2;
        char[] hexChars = hexStr.toCharArray();
        byte[] bytes = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            bytes[i] = (byte) (getCharToByte(hexChars[pos]) << 4 | getCharToByte(hexChars[pos + 1]));
        }
        return bytes;
    }

    /**
     *  字符转字节
     *
     * @param c 字符
     * @return 字节
     */
    public static byte getCharToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
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
