package com.atguigu.serviceedu.controller;


import com.atguigu.commonutils.R;
import com.atguigu.serviceedu.entity.EduTeacher;
import com.atguigu.serviceedu.entity.vo.TeachQuery;
import com.atguigu.serviceedu.service.EduTeacherService;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author xudongzhou
 * @since 2022-06-23
 */
@Api(description = "讲师管理")
@CrossOrigin
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {
    @Autowired
    private EduTeacherService teacherService;

    @ApiOperation(value = "所有讲师列表")
    @GetMapping("findAll")
    public List<EduTeacher> list(){
        List<EduTeacher> list = teacherService.list(null);
        return (List<EduTeacher>) R.ok().data("items", list);
    }

    @ApiOperation(value = "逻辑删除讲师")
    @DeleteMapping("{id}")
    public R deleteTeacher(@ApiParam(name = "id", value = "讲师id", required = true) @PathVariable String id) {
        boolean removeById = teacherService.removeById(id);
        if (removeById) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @ApiOperation(value = "分页讲师列表")
    @GetMapping("pageTeacher/{current}/{limit}")
    public R pageTeacher(@PathVariable Long current,@PathVariable Long limit){

        //创建page
        Page<EduTeacher> pageTeacher = new Page<>(current,limit);

        //ctrl+alt+t 异常快捷键
        IPage<EduTeacher> page = teacherService.page(pageTeacher,null);
        List<EduTeacher> records = page.getRecords();
        Long total = page.getTotal();

        HashMap<String, Object> map = new HashMap<>();
        map.put("total",total);
        map.put("rows",records);
        return R.ok().data(map);
    }


    //4.条件查询分页方法
    @ApiOperation(value = "条件查询分页方法")
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(@PathVariable Long current,
                                  @PathVariable Long limit,
                                  @RequestBody(required = false) TeachQuery teachQuery){

        //创建page
        Page<EduTeacher> pageCondition = new Page<>(current,limit);

        //QueryWrapper构建
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        //多条件组合查询，动态sql
        String name = teachQuery.getName();
        Integer level = teachQuery.getLevel();
        String begin = teachQuery.getBegin();
        String end = teachQuery.getEnd();

        //判断条件是否为空，拼接条件
        if (!StringUtils.isEmpty(level)) {
            wrapper.eq("level", level);
        }
        if (!StringUtils.isEmpty(name)) {
            wrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_create", end);
        }

        wrapper.orderByDesc("gmt_create");
        //调用方法，实现分页查询
        teacherService.page(pageCondition,wrapper);

        List<EduTeacher> records = pageCondition.getRecords();
        Long total = pageCondition.getTotal();
        HashMap<String, Object>  map = new HashMap<>();
        map.put("total", total);
        map.put("rows", records);
        return R.ok().data(map);
    }




}

