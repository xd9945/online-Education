package com.atguigu.servicebase.exceptionhandler;

import com.atguigu.commonutils.result.ResultCode;
import com.atguigu.commonutils.vo.ResultVo;
import com.atguigu.servicebase.exceptionhandler.exception.GuliException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

/**
 * @author xudongzhou
 * @date 2022-07-05
 * @desc  全局异常处理
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     *   指定出现什么异常执行这个方法
     */
    @ExceptionHandler(Exception.class)
    public ResultVo error(Exception e) {
        e.printStackTrace();
        return ResultVo.error().message("服务器又耍流氓了..");
    }


    /**
     * 自定义异常处理
     * @param e
     * @return ResultVo
     */
    @ExceptionHandler(GuliException.class)
    public ResultVo error(GuliException e) {
        log.error("异常信息：{}",e);
        return ResultVo.error().code(e.getCode()).message(e.getMsg());
    }

    @ExceptionHandler(IOException.class)
    public ResultVo error(IOException e){
        log.error("异常信息",e);

        throw GuliException.from(ResultCode.UPLOAD_FILE_ERROR);
    }
}
