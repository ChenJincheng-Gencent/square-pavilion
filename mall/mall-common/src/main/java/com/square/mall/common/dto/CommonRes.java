package com.square.mall.common.dto;

import com.square.mall.common.enums.ErrorCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 通用返回体
 *
 * @author Gencent
 * @date 2019/8/26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "通用返回消息体")
public class CommonRes<T> implements Serializable {

    private static final long serialVersionUID = 5244335969680099007L;

    /**
     * 成功
     */
    public static final CommonRes<Void> SUCCESS = new CommonRes<>(ErrorCode.SUCCESS);

    /**
     * 失败
     */
    public static final CommonRes<Void> FAILURE = new CommonRes<>(ErrorCode.FAILURE);

    /**
     * 错误码
     */
    @ApiModelProperty(name = "code", value = "错误码", example = "0")
    private Integer code;

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

    public CommonRes(T data){
        this.code = ErrorCode.SUCCESS.getCode();
        this.msg = ErrorCode.SUCCESS.getMsg();
        this.data = data;
    }

    public CommonRes(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public CommonRes(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
    }

}
