package com.atguigu.edu.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author xudongzhou
 * @date 2022-07-05
 * @desc 课程状态
 */
@AllArgsConstructor
@Getter
public enum CourseStatusEnum {

    DRAFT("Draft"),

    NORMAL("Normal");


    private String desc;

}
