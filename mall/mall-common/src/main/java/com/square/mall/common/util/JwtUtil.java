package com.square.mall.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * JWT工具类
 *
 * @author Gencent
 * @date 2020/7/17
 */
@Slf4j
public class JwtUtil {

    /**
     * 过期时间
     */
    private static final long EXPIRE_TIME = 24 * 60 * 60 * 1000;

    /**
     * 校验token是否正确
     * @param token 密钥
     * @param mobile 手机号码
     * @param memberId 会员ID
     * @return 是否正确
     */
    public static boolean verify(String token, String mobile, Long memberId) {

        Algorithm algorithm = Algorithm.HMAC256(String.valueOf(memberId));
        JWTVerifier verifier = JWT.require(algorithm).withClaim("mobile", mobile).build();
        verifier.verify(token);
        return true;

    }

    /**
     *  获取token中的手机号码信息
     *
     * @param token token
     * @return token中包含的手机号码
     */
    public static String getMobile(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("mobile").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 生成签名,指定时间后过期,一经生成不可修改，令牌在指定时间内一直有效
     *
     * @param mobile 手机号码
     * @param memberId 会员ID
     * @return 加密的token
     */
    public static String sign(String mobile, Long memberId) {

        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(String.valueOf(memberId));
        return JWT.create()
                .withClaim("mobile", mobile)
                .withExpiresAt(date)
                .sign(algorithm);
    }

}
