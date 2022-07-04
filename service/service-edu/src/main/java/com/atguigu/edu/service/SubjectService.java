package com.atguigu.edu.service;

import com.atguigu.edu.entity.Subject;
import com.atguigu.edu.entity.subject.OneSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author xudongzhou
 * @since 2022-06-29
 */
public interface SubjectService extends IService<Subject> {

    /**
     * 保存科目
     * @param file  xlsx文件
     * @param  subjectService 科目service
     */
    void saveSubject(MultipartFile file, SubjectService subjectService);


    /**
     *   查询所有一级分类和二级分类
     * @return 所有分类
     */
    List<OneSubject> getAllOneAndTwoSubject();

}
