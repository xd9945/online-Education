package com.atguigu.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atguigu.edu.entity.Subject;
import com.atguigu.edu.entity.excel.SubjectData;
import com.atguigu.edu.entity.subject.OneSubject;
import com.atguigu.edu.entity.subject.TwoSubject;
import com.atguigu.edu.listener.SubjectExcelListener;
import com.atguigu.edu.mapper.EduSubjectMapper;
import com.atguigu.edu.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author xudongzhou
 * @since 2022-06-29
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<EduSubjectMapper, Subject> implements SubjectService {

    /**
     * 保存科目
     *
     * @param file xlsx文件
     */
    @Override
    public void saveSubject(MultipartFile file, SubjectService subjectService) {


        try {
            //1.获取文件输入流
            InputStream inputStream = file.getInputStream();

            //2.调用方法进行读取

            EasyExcel.read(inputStream, SubjectData.class,new SubjectExcelListener(subjectService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * 查询所有一级分类和二级分类  树形
     *
     * @return 所有分类
     */
    @Override
    public List<OneSubject> getAllOneAndTwoSubject() {
        List<Subject> subjects = this.list(null);

        /**
         * 所有的一级分类
         */
        List<OneSubject> oneSubjectList = subjects.stream()
                .filter(subject -> subject.getParentId().equals("0"))
                .map(subject -> {
                    OneSubject oneSubject = new OneSubject();
                    oneSubject.setId(subject.getId());
                    oneSubject.setTitle(subject.getTitle());
                    return oneSubject;
                })
                .collect(Collectors.toList());
        /**
         * 封装一级分类下的二级分类
         */
        oneSubjectList.forEach(oneSubject ->{
            List<TwoSubject> twoSubjectList = subjects.stream()
                    .filter(subject -> subject.getParentId().equals(oneSubject.getId()))
                    .map(subject -> {
                        TwoSubject twoSubject = TwoSubject.builder()
                                .id(subject.getId())
                                .title(subject.getTitle())
                                .build();
                        return twoSubject;
                    })
                    .collect(Collectors.toList());

            oneSubject.setChildren(twoSubjectList);
        });
        return oneSubjectList;
    }
}
