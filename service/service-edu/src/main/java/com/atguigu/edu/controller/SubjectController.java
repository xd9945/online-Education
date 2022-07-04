package com.atguigu.edu.controller;


import com.atguigu.commonutils.vo.ResultVo;
import com.atguigu.edu.entity.subject.OneSubject;
import com.atguigu.edu.enums.EduResultCode;
import com.atguigu.edu.service.SubjectService;
import com.atguigu.servicebase.exceptionhandler.exception.GuliException;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author xudongzhou
 * @since 2022-06-29
 */
@RestController
@RequestMapping("/edu/subject")
//@CrossOrigin
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }


    @PostMapping("/addSubject")
    @ApiOperation("上传Excel添加科目")
    public ResultVo addSubject(MultipartFile file){

        file = Optional.ofNullable(file).orElseThrow(() -> GuliException.from(EduResultCode.FILE_IS_EMPTY));
        //上传过来的excel文件
        subjectService.saveSubject(file,subjectService);
        return ResultVo.ok();
    }


    @ApiOperation("获取所有分类")
    @GetMapping("/findAllSubject")
    public ResultVo findAllSubject(){
        List<OneSubject> allOneAndTwoSubject = subjectService.getAllOneAndTwoSubject();

        return ResultVo.ok().data("items",allOneAndTwoSubject);
    }


}

