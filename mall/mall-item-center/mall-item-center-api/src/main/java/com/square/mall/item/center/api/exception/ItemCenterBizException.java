package com.square.mall.item.center.api.exception;


import com.square.mall.common.enums.ErrorCode;
import com.square.mall.common.exception.BizException;
import com.square.mall.item.center.api.enums.ItemCenterBizCode;

/**
 * 商品中心业务异常类
 *
 * @author Gencent
 * @date 2021/2/9
 */
public class ItemCenterBizException extends BizException {

    private static final long serialVersionUID = -2975716247677604292L;

    public ItemCenterBizException(Throwable cause) {
        super(cause);
    }

    public ItemCenterBizException(String message) {
        super(message);
    }

    public ItemCenterBizException(String message, Throwable cause) {
        super(message, cause);
    }

    public ItemCenterBizException(ErrorCode errorCode) {
        super(errorCode);
    }

    public ItemCenterBizException(Integer code, String msg) {
        super(code, msg);
    }

    public ItemCenterBizException(ItemCenterBizCode itemCenterBizCode){
        super(itemCenterBizCode.getCode(), itemCenterBizCode.getMsg());
    }
}
