package com.atguigu.edu.model.vo;

import lombok.Data;

/**
 * @author xudongzhou
 * @date 2022-07-05
 * @desc
 */
@Data
public class CoursePublishVo {

    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;
}
