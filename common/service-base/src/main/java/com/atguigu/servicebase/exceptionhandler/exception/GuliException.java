package com.atguigu.servicebase.exceptionhandler.exception;

import com.atguigu.commonutils.result.IResultCode;
import lombok.Getter;

/**
 * @author xudongzhou
 * @date 2022-07-05
 * @desc  自定义异常处理
 */
@Getter
public class GuliException extends RuntimeException {

    private Integer code;

    private String msg;

    private GuliException (Integer code,String msg){
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    private GuliException (String msg){
        super(msg);
        this.msg = msg;
    }

    public static  GuliException from(IResultCode resultCode){
        GuliException guliException = new GuliException(resultCode.getCode(), resultCode.getMsg());
        return guliException;
    }

    public static  GuliException from(String  msg){
        GuliException guliException = new GuliException(msg);
        return guliException;
    }

}
