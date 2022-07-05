package com.atguigu.edu.mapper;

import com.atguigu.edu.entity.Course;
import com.atguigu.edu.model.vo.CoursePublishVo;
import com.atguigu.edu.model.vo.CourseWebVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author xudongzhou
 * @since 2022-07-05
 */
public interface CourseMapper extends BaseMapper<Course> {

    /**
     * 根据课程id  查询课程信息
     * @param courseId 课程id
     * @return CoursePublishVo
     */
    CoursePublishVo getPublishCourseInfo(String courseId);

    /**
     * 根据课程id，查询课程的基本信息
     * @param courseId
     * @return
     */
    CourseWebVo getBaseCourseInfo(String courseId);

}
