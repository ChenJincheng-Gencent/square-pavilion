package com.square.mall.common.util;

import com.alibaba.fastjson.JSON;
import com.square.mall.common.constant.CommonConstant;
import com.square.mall.common.dto.SmsSendReq;
import com.square.mall.common.dto.SmsSendRsp;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 *  短信工具类
 *
 * @author Gencent
 * @date 2020/7/17
 */
@Slf4j
public class SmsUtil {



    /**
     * 短信平台请求地址
     */
    private static final String URL = "http://smssh1.253.com/msg/send/json";

    /**
     * 用户平台API账号(非登录账号,示例:N1234567)
     */
    private static final String ACCOUNT = "*****";

    /**
     * 用户平台API密码(非登录密码)
     */
    private static final String PASSWORD = "*****";


    private static final String REPORT = "true";

    private static final String SIGNATURE ="【*****】";

    /**
     * 调用第三方平台，下发短信
     * @param receiveUser 接受者
     * @param message 短信内容
     * @return 响应
     */
    public static SmsSendRsp sendMessage(String receiveUser, String message) {
        String str = SIGNATURE + message;
        SmsSendReq smsSingleRequest = new SmsSendReq(ACCOUNT, PASSWORD, str, receiveUser, REPORT);
        String requestJson = JSON.toJSONString(smsSingleRequest);
        log.info("before request string is: {}", requestJson);
        String response = sendSmsByPost(URL, requestJson);
        log.info("response after request result is: {}", response);
        SmsSendRsp smsSingleRsp = JSON.parseObject(response, SmsSendRsp.class);
        log.info("smsSingleRsp: {}", smsSingleRsp);
        return smsSingleRsp;
    }

    /**
     * POST客户端
     *
     * @param path 短信平台URL
     * @param postContent 消息体
     * @return 响应字符串
     *
     */
    private static String sendSmsByPost(String path, String postContent) {
        URL url = null;
        try {
            url = new URL(path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);
            // 发送POST请求必须设置如下两行
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestProperty("Charset", CommonConstant.ENCODE_UTF_8);
            connection.setRequestProperty("Content-Type", "application/json");

            connection.connect();
            OutputStream os = connection.getOutputStream();
            os.write(postContent.getBytes(StandardCharsets.UTF_8));
            os.flush();

            StringBuilder sb = new StringBuilder();
            int httpRspCode = connection.getResponseCode();
            if (httpRspCode == HttpURLConnection.HTTP_OK) {
                // 开始获取数据
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                br.close();
                return sb.toString();

            } else {
                log.error("短信下发失败");
            }
        } catch (Exception e) {
            log.error("短信下发失败");
        }

        return null;
    }


    public static void main(String[] args) {
        sendMessage("13888888888", "我试试请求，看一看结果");
    }
}
