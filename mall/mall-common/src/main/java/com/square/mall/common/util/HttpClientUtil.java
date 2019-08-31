package com.square.mall.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * Restful接口调用工具类
 *
 * @author Gencent
 * @date 2019/8/20
 */

@Slf4j
public class HttpClientUtil {

    /**
     * Get方式请求restful服务
     *
     * @param url get请求地址
     * @return String
     */
    public static String doGet(String url) {

        if (StringUtil.isBlank(url)) {
            log.error("url is null or empty.");
            return null;
        }

        HttpClient hc = new HttpClient();

        hc.getHttpConnectionManager().getParams().setConnectionTimeout(30 * 60 * 1000);
        hc.getHttpConnectionManager().getParams().setSoTimeout(30 * 60 * 1000);

        HttpClientParams params = new HttpClientParams();

        params.setContentCharset("UTF-8");
        hc.setParams(params);

        GetMethod method = null;
        String responseMsg = null;
        BufferedReader br = null;
        InputStreamReader is = null;
        InputStream ins = null;

        try {
            method = new GetMethod(url);

            // 使用系统系统的默认的恢复策略
            method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());

            int status = hc.executeMethod(method);
            if (status == HttpStatus.SC_OK) {
                ins = method.getResponseBodyAsStream();
                is = new InputStreamReader(ins, StandardCharsets.UTF_8);
                br = new BufferedReader(is);
                StringBuffer sb = new StringBuffer();

                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                responseMsg = sb.toString();
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            if (method != null) {
                method.releaseConnection();
            }
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
            if (null != ins) {
                try {
                    ins.close();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
            hc.getHttpConnectionManager().getParams().setConnectionTimeout(1000);
            hc.getHttpConnectionManager().getParams().setSoTimeout(1000);
        }

        return responseMsg;

    }


}