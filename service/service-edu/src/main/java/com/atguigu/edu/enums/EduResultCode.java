package com.atguigu.edu.enums;

import com.atguigu.commonutils.result.IResultCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author xudongzhou
 * @date 2022-07-05
 * @desc 响应码枚举
 */
@AllArgsConstructor
@Getter
public enum EduResultCode implements IResultCode {

    SUCCESS(20000,"操作成功"),

    ERROR(20001,"响应失败"),

    FILE_IS_EMPTY(20002,"请上传文件！！！"),

    SAVE_ERROR(20003,"添加失败"),

    UPDATE_ERROR(20004,"更新失败"),

    DATA_NO_EXIST(20005,"数据不存在"),

    DELETE_ERROR(20006,"删除失败"),

    ;

    private Integer code;

    private String msg;

}
