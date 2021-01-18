package com.square.mall.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 短信发送响应
 *
 * @author Gencent
 * @date 2020/7/18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmsSendRsp {

    /**
     * 响应时间
     */
    private String time;

    /**
     * 消息id
     */
    private String msgId;

    /**
     * 状态码说明（成功返回空）
     */
    private String errorMsg;

    /**
     * 状态码（详细参考提交响应状态码）
     */
    private String code;


}

