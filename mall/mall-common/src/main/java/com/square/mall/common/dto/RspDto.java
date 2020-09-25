package com.square.mall.common.dto;

import com.square.mall.common.util.ErrorCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 通用返回DTO
 *
 * @author Gencent
 * @date 2019/8/26
 */
@Data
@AllArgsConstructor
@ApiModel(description = "通用返回消息体")
public class RspDto<T> implements Serializable {

    private static final long serialVersionUID = 5244335969680099007L;

    /**
     * 成功
     */
    public static final RspDto SUCCESS = new RspDto(ErrorCode.SUCCESS);

    /**
     * 失败
     */
    public static final RspDto FAILED = new RspDto(ErrorCode.FAILED);

    /**
     * 错误码
     */
    @ApiModelProperty(name = "code", value = "错误码", example = "0")
    private String code;

    /**
     * 错误描述
     */
    @ApiModelProperty(name = "msg", value = "错误描述", example = "成功")
    private String msg;

    /**
     * 数据泛型
     */
    @ApiModelProperty(name = "data", value = "数据")
    private T data;

    public RspDto(T data){
        this.code = ErrorCode.SUCCESS.getCode();
        this.msg = ErrorCode.SUCCESS.getMsg();
        this.data = data;
    }

    public RspDto(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public RspDto(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
    }

}
