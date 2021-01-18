package com.square.mall.common.util;

import com.square.mall.common.constant.CommonConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

/**
 * AES加解密工具类
 *
 * @author Gencent
 * @date 2020/7/2
 */
@Slf4j
public class AesUtil {

    private static final String Algorithm = "AES";

	public static void main(String[] args) {
		String cltk = "{\"addressDetail\":\"东软软件园\",\"area\":\"浑南新区\",\"city\":\"沈阳市\",\"consignee\":\"刘航\",\"contact\":\"15940132259\",\"mobileNo\":\"15940132259\",\"monthlyId\":\"165223\",\"province\":\"辽宁省\"}";
		String key = "jkQWpwub6S3zwZvr";
		String encrypt = encrypt(cltk, key);
		System.out.println("加密后：" + encrypt.length() + " " + encrypt);
		String decryptToken = decryptToken(encrypt, key);
		System.out.println("解密后：" + decryptToken);
	}

    /**
     * 加密
     */
    public static String encrypt(String str, String password) {
        try {
            byte[] enCodeFormat = getSecretKey(password);
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            // 创建密码器
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            byte[] byteContent = str.getBytes(CommonConstant.ENCODE_UTF_8);
            // 初始化
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] result = cipher.doFinal(byteContent);
            // 加密
            return Base64.encodeBase64String(result);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | UnsupportedEncodingException
                | IllegalBlockSizeException | BadPaddingException e) {
            log.error("encrypt password failed, ", e);
        }
        return null;
    }


    /**
     * 加密，去除特殊字符
     */
    public static String encryptExcludeSpecialChars(String str, String password) {
        try {
            byte[] enCodeFormat = getSecretKey(password);
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            // 创建密码器
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            byte[] byteContent = str.getBytes(CommonConstant.ENCODE_UTF_8);
            // 初始化
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] result = cipher.doFinal(byteContent);
            return StringUtil.getBytesToHexStr(result);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | UnsupportedEncodingException
            | IllegalBlockSizeException | BadPaddingException e) {
            log.error("encrypt password failed, ", e);
        }
        return null;
    }

    /**
     * 解密
     */
    public static String decryptToken(String str, String password) {

        log.info("decryptToken---------解密-----------密文：{}，秘钥：{}", str, password);

        // 长token解密得到明文token
        String realToken = "";
        try {
            realToken = decryptECB(str, password);
        } catch (Exception e) {
            log.error("decrypt password failed, ", e);
        }

        log.info("decryptToken---------解密-----------明文：{}", realToken);

        return realToken;
    }

    /**
     * 解密，没有特殊字符情况下
     */
    public static String decryptExcludeSpecialChars(String str, String password) {

        log.info("decryptToken---------解密-----------密文：{}，秘钥：{}", str, password);

        String decryptStr = "";
        try {
            decryptStr = decryptECBExcludeSpecialChars(str, password);
        } catch (Exception e) {
            log.error("decrypt password failed, ", e);
        }

        log.info("decryptStr---------解密-----------明文：{}", decryptStr);

        return decryptStr;
    }

    private static String decryptECB(String data, String key) throws Exception {
        if (StringUtil.isBlank(key) || key.length() < 16) {
            return null;
        }

        // 将字符串转化为字节数组
        byte[] decryptdata = Base64.decodeBase64(data);
        byte[] skey = getSecretKey(key);

        // 解密
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, toKey(skey));
        byte[] result = cipher.doFinal(decryptdata);

        // 转化为字符串
        return new String(result);
    }

    private static String decryptECBExcludeSpecialChars(String data, String key) throws Exception {

        if (StringUtil.isBlank(key) || key.length() < 16) {
            return null;
        }

        // 将字符串转化为字节数组
        byte[] decryptdata = StringUtil.getHexStrToBytes(data);
        byte[] skey = getSecretKey(key);

        // 解密
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, toKey(skey));
        byte[] result = cipher.doFinal(decryptdata);

        // 转化为字符串
        return new String(result);
    }

    private static byte[] getSecretKey(String key) throws UnsupportedEncodingException {
       return key.substring(0, 16).getBytes(CommonConstant.ENCODE_UTF_8);
    }

    private static Key toKey(byte[] key) throws IllegalArgumentException {
        return new SecretKeySpec(key, Algorithm);
    }

}
