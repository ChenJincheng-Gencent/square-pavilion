package com.square.mall.common.dto;

import com.square.mall.common.util.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 基本返回DTO
 *
 * @author Gencent
 * @date 2019/8/26
 */
@Data
@AllArgsConstructor
public class RspDto<T> implements Serializable {

    private static final long serialVersionUID = 5244335969680099007L;

    /**
     *  成功
     */
    public static final RspDto SUCCESS = new RspDto(ErrorCode.SUCCESS.getCode(), ErrorCode.SUCCESS.getDesc());

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误描述
     */
    private String desc;

    /**
     * 数据泛型
     */
    private T data;

    public RspDto(T data){
        this.code = ErrorCode.SUCCESS.getCode();
        this.desc = ErrorCode.SUCCESS.getDesc();
        this.data = data;
    }

    public RspDto(String code, String desc){
        this.code = code;
        this.desc = desc;
    }

}
