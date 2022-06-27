package com.atguigu.commonutils.exceptionhandler;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xudongzhou
 * @date 2022-06-26
 * @desc 自定义异常类
 *
 */
@Data
@AllArgsConstructor  //有参数构造器
@NoArgsConstructor   //生成无参数构造
public class GuliException extends RuntimeException{

    @ApiModelProperty(value = "状态码")
    private Integer code;//状态码
    private String msg;//输出消息

}
