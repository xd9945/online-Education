package com.atguigu.commonutils.result;

/**
 * @author xudongzhou
 * @date 2022-07-05
 * @desc 响应码和响应信息定义
 */
public interface IResultCode {

    /**
     *  获取响应码
     * @return 响应码
     */
    Integer getCode();


    /**
     * 获取响应信息
     *
     * @return 响应信息
     */
    String getMsg();

}
