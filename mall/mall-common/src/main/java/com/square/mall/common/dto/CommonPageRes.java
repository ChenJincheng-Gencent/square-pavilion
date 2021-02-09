package com.square.mall.common.dto;

import com.square.mall.common.enums.ErrorCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 通用分页返回体
 *
 * @author Gencent
 * @date 2020/7/24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "通用分页返回消息体")
public class CommonPageRes<T> implements Serializable {

    private static final long serialVersionUID = -8255510440938304460L;

    /**
     * 成功
     */
    public static final CommonPageRes<Void> SUCCESS = new CommonPageRes<>(ErrorCode.SUCCESS);

    /**
     * 失败
     */
    public static final CommonPageRes<Void> FAILURE = new CommonPageRes<>(ErrorCode.FAILURE);



    /**
     * 错误码
     */
    @ApiModelProperty(name = "code", value = "错误码")
    private Integer code;

    /**
     * 错误描述
     */
    @ApiModelProperty(name = "msg", value = "错误描述")
    private String msg;

    /**
     * 总记录数
     */
    @ApiModelProperty(name = "total", value = "总记录数")
    private Long total;

    /**
     * 数据泛型
     */
    @ApiModelProperty(name = "data", value = "数据")
    private T data;

    public CommonPageRes(Long total, T data) {
        this.code = ErrorCode.SUCCESS.getCode();
        this.msg = ErrorCode.SUCCESS.getMsg();
        this.total = total;
        this.data = data;
    }

    public CommonPageRes(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
    }

}
