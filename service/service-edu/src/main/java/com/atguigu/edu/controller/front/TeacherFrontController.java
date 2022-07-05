package com.atguigu.edu.controller.front;


import com.atguigu.commonutils.vo.ResultVo;
import com.atguigu.edu.entity.Course;
import com.atguigu.edu.entity.Teacher;
import com.atguigu.edu.service.CourseService;
import com.atguigu.edu.service.TeacherService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xudongzhou
 * @date 2022-07-04
 * @desc  首页数据管理
 */
@Api(tags = "首页数据管理")
@RequestMapping("/edu/indexFront")
@RestController
@CrossOrigin
public class TeacherFrontController {

    private final TeacherService teacherService;
    private final CourseService courseService;

    public TeacherFrontController(TeacherService teacherService, CourseService courseService) {
        this.teacherService = teacherService;
        this.courseService = courseService;
    }

    @ApiOperation("讲师C端分页查询")
    @GetMapping("/getTeacherInfoList/{page}/{size}")
    public ResultVo getTeacherFrontList(@PathVariable long page , @PathVariable long size){

        Page<Teacher> teacherPage = new Page<>(page,size);
        teacherPage = teacherService.getTeacherFrontList(teacherPage);

        return ResultVo.ok().data("page",teacherPage);
    }

    @ApiOperation("讲师详情的功能")
    @GetMapping("/getTeacherFrontInfo/{teacherId}")
    public ResultVo getTeacherFront(@PathVariable String teacherId){

        //1.根据讲师id查询讲师的基本信息
        Teacher teacher = teacherService.getById(teacherId);

        //2.根据讲师查询所讲课程
        LambdaQueryWrapper<Course> courseQuery = new LambdaQueryWrapper<>();
        courseQuery.eq(Course::getTeacherId,teacherId);
        List<Course> courseList = courseService.list(courseQuery);
        return ResultVo.ok().data("teacher",teacher).data("courseList",courseList);
    }

}
