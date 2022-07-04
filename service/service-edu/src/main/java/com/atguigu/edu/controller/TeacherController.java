package com.atguigu.edu.controller;

import com.atguigu.commonutils.vo.ResultVo;
import com.atguigu.edu.entity.Teacher;
import com.atguigu.edu.model.query.TeacherQuery;
import com.atguigu.edu.service.TeacherService;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author xudongzhou
 * @since 2022-06-23
 */
@Api(description = "讲师管理")
//@CrossOrigin
@RestController
@RequestMapping("/edu/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @ApiOperation(value = "所有讲师列表")
    @GetMapping("findAll")
    public ResultVo findAllTeacher(){
        List<Teacher> list = teacherService.list(null);
        return ResultVo.ok().data("items", list);
    }

    @ApiOperation(value = "逻辑删除讲师")
    @DeleteMapping("{id}")
    public ResultVo deleteTeacher(@ApiParam(name = "id", value = "讲师id", required = true) @PathVariable String id) {
        boolean removeById = teacherService.removeById(id);
        if (removeById) {
            return ResultVo.ok();
        } else {
            return ResultVo.error();
        }
    }

    @ApiOperation("分页查询讲师（不带条件）")
    @GetMapping("/pageTeacher/{page}/{size}")
    public ResultVo pageTeacher(@ApiParam(value = "page",defaultValue = "1") @PathVariable("page") int page,
                                @ApiParam(value = "size",defaultValue = "10")@PathVariable("size") int size){

        //1.创建page对象
        //调用方法时候，底层封装，把分页所有数据封装到pageTeacher对象里面
        IPage<Teacher> teacherPage = new Page<>(page, size);
        teacherService.page(teacherPage, null);

        Map<String,Object> map = new HashMap<>(16);

        map.put("total",teacherPage.getTotal());
        map.put("rows",teacherPage.getRecords());

        return ResultVo.ok().data(map);
    }


    /**
     * 教师分页带条件查询
     * @param page 当前页
     * @param size  每页的数量
     * @return   ResultVo
     */

    @ApiOperation("讲师分页查询带条件")
    @PostMapping("/pageTeacherCondition/{page}/{size}")
    public ResultVo pageTeacherCondition(@ApiParam(value = "page",defaultValue = "1") @PathVariable("page") int page,
                                         @ApiParam(value = "size",defaultValue = "10")@PathVariable("size") int size,
                                         @RequestBody TeacherQuery teacherQuery){
        //创建page对象
        IPage<Teacher> pageTeacher = new Page<>(page,size);

        //构建条件
        LambdaQueryWrapper<Teacher> query = new LambdaQueryWrapper<>();
        query.like(org.apache.commons.lang3.StringUtils.isNotBlank(teacherQuery.getName()),Teacher::getName,teacherQuery.getName());
        query.eq(Objects.nonNull(teacherQuery.getLevel()),Teacher::getLevel,teacherQuery.getLevel());
        query.ge(org.apache.commons.lang3.StringUtils.isNotBlank(teacherQuery.getBegin()),Teacher::getGmtCreate,teacherQuery.getBegin());
        query.le(StringUtils.isNotBlank(teacherQuery.getEnd()),Teacher::getGmtCreate,teacherQuery.getEnd());
        query.orderByDesc(Teacher::getGmtCreate);
        teacherService.page(pageTeacher, query);
        Map<String,Object> map = new HashMap<>(16);
        map.put("total",pageTeacher.getTotal());
        map.put("rows",pageTeacher.getRecords());
        return ResultVo.ok().data(map);
    }

    @GetMapping("/getTeacher/{id}")
    @ApiOperation("根据讲师id进行查询")
    public ResultVo getTeacher(@ApiParam(value = "id",required = true) @PathVariable("id") String id){
        Teacher teacher = teacherService.getById(id);
        return ResultVo.ok().data("teacher",teacher);
    }

    @ApiOperation("修改讲师功能")
    @PutMapping("/updateTeacher/{id}")
    public ResultVo updateTeacher(@PathVariable String id,@RequestBody Teacher teacher){

        Teacher result = teacherService.getById(id);
        BeanUtils.copyProperties(teacher,result);
        result.setId(id);
        boolean updateResult = teacherService.updateById(result);

        if(updateResult){
            return ResultVo.ok();
        }else {
            return ResultVo.error();
        }
    }

    /**
     * 添加讲师的方法
     * @param teacher
     * @return ResultVo
     */
    @ApiOperation("添加讲师")
    @PostMapping("addTeacher")
    public ResultVo addTeacher(@RequestBody Teacher teacher){

        boolean save = teacherService.save(teacher);
        if(save){
            return ResultVo.ok();
        }
        return ResultVo.error();
    }


}

