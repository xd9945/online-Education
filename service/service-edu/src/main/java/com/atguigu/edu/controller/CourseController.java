package com.atguigu.edu.controller;


import com.atguigu.commonutils.vo.ResultVo;
import com.atguigu.edu.entity.Course;
import com.atguigu.edu.enums.CourseStatusEnum;
import com.atguigu.edu.model.form.CourseInfoForm;
import com.atguigu.edu.model.vo.CourseInfoVo;
import com.atguigu.edu.model.vo.CoursePublishVo;
import com.atguigu.edu.service.CourseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author xudongzhou
 * @since 2022-07-05
 */
@RestController
@RequestMapping("/edu/course")
//@CrossOrigin
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @ApiOperation("添加课程信息")
    @PostMapping("/addCourseInfo")
    public ResultVo addCourseInfo(@RequestBody CourseInfoForm courseInfoForm){

        String courseId = courseService.saveCourseInfo(courseInfoForm);
        return ResultVo.ok().data("courseId",courseId);
    }

    @ApiOperation("根据课程id查询课程信息")
    @GetMapping("/getCourseInfo/{courseId}")
    public ResultVo getCourseInfo(@PathVariable String courseId){
        CourseInfoVo courseInfoVo = courseService.getCourseInfoByCourseId(courseId);
        return ResultVo.ok().data("courseInfo",courseInfoVo);
    }

    @ApiOperation("修改课程信息")
    @PutMapping("updateCourseInfo")
    public ResultVo updateCourseInfo(@RequestBody CourseInfoForm courseInfoForm){
        courseService.updateCourse(courseInfoForm);
        return ResultVo.ok();
    }


    @ApiOperation("根据课程id查询课程确认信息")
    @GetMapping("/getPublishCourseInfo/{id}")
    public ResultVo getPublishCourseInfo(@PathVariable String id){
        CoursePublishVo coursePublishVo = courseService.publishCourseInfo(id);

        return ResultVo.ok().data("coursePublishInfo",coursePublishVo);
    }

    @ApiOperation("课程发布")
    @PostMapping("/publishCourse/{id}")
    public ResultVo publishCourse(@PathVariable String id){
        Course course = new Course();
        course.setId(id);
        course.setStatus(CourseStatusEnum.NORMAL.getDesc());

        courseService.updateById(course);
        return ResultVo.ok();
    }

    @ApiOperation("查询课程列表")
    @GetMapping
    public ResultVo getCourseList(){

        //TODO 完善条件查询带分页
        List<Course> list = courseService.list(null);
        return ResultVo.ok().data("list",list);
    }

    @ApiOperation("删除课程信息")
    @DeleteMapping("/{courseId}")
    public ResultVo deleteCourse(@PathVariable String courseId){

        //TODO 前端实现
        courseService.deleteCourse(courseId);
        return ResultVo.ok();
    }

}

