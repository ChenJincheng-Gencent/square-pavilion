package com.square.mall.common.util;

import com.square.mall.common.dto.RspDto;

/**
 * 数据库操作工具类
 *
 * @author Gencent
 * @date 2020/7/27
 */
public class DatabaseUtil {

    /**
     * 返回数据库操作结果
     *
     * @param value 数据库操作返回值
     * @param data 返回数据
     * @param moduleName 模块名称
     * @return 数据库操作结果
     */
    public static<T> RspDto<T> getResult(int value, T data, String moduleName) {

        switch (value) {
            case DatabaseOptConstant.DATABASE_OPT_FAILED:
                switch (moduleName) {
                    case ModuleConstant.MEMBER_CENTER:
                        return new RspDto<>(ErrorCode.ME_CEN_DATABASE_OPT_FAILED);
                    case ModuleConstant.ITEM_CENTER:
                        return new RspDto<>(ErrorCode.IT_CEN_DATABASE_OPT_FAILED);
                    default:
                        return RspDto.FAILED;
                }
            case DatabaseOptConstant.DATABASE_OPT_SUCCESS:
                return new RspDto<>(data);
            case DatabaseOptConstant.DATABASE_PARA_ILLEGAL:
                switch (moduleName) {
                    case ModuleConstant.MEMBER_CENTER:
                        return new RspDto<>(ErrorCode.ME_CEN_DATABASE_PARA_ILLEGAL);
                    case ModuleConstant.ITEM_CENTER:
                        return new RspDto<>(ErrorCode.IT_CEN_DATABASE_PARA_ILLEGAL);
                    default:
                        return RspDto.FAILED;
                }
            case DatabaseOptConstant.DATABASE_DATA_NOT_EXIST:
                switch (moduleName) {
                    case ModuleConstant.MEMBER_CENTER:
                        return new RspDto<>(ErrorCode.ME_CEN_DATABASE_DATA_NOT_EXIST);
                    case ModuleConstant.ITEM_CENTER:
                        return new RspDto<>(ErrorCode.IT_CEN_DATABASE_DATA_NOT_EXIST);
                    default:
                        return RspDto.FAILED;
                }
            case DatabaseOptConstant.DATABASE_DATA_ALREADY_EXIST:
                switch (moduleName) {
                    case ModuleConstant.MEMBER_CENTER:
                        return new RspDto<>(ErrorCode.ME_CEN_DATABASE_DATA_ALREADY_EXIST);
                    case ModuleConstant.ITEM_CENTER:
                        return new RspDto<>(ErrorCode.IT_CEN_DATABASE_DATA_ALREADY_EXIST);
                    default:
                        return RspDto.FAILED;
                }
            default:
                return RspDto.FAILED;
        }

    }

    /**
     * 返回数据库操作结果
     *
     * @param value 数据库操作返回值
     * @param moduleName 模块名称
     * @return 数据库操作结果
     */
    public static RspDto getResult(int value, String moduleName) {
        return getResult(value, null, moduleName);
    }
}
