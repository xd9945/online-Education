package com.atguigu.edu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.edu.entity.Subject;
import com.atguigu.edu.entity.excel.SubjectData;
import com.atguigu.edu.enums.EduResultCode;
import com.atguigu.edu.service.SubjectService;
import com.atguigu.servicebase.exceptionhandler.exception.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * @author xudongzhou
 * @date 2022-07-05
 * @desc
 */
@AllArgsConstructor
@NoArgsConstructor
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    /**
     *
     *  因为SubjectExcelListener不能交给Spring管理，需要自己new，不能注入其它对象
     *  不能实现数据库操作
     *  需要手动注入
     */

    private SubjectService subjectService;


    /**
     * 读取excel内容，一行一行进行读取
     * @param subjectData
     * @param analysisContext
     */
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {

        System.out.println("-----------"+ subjectData);
        subjectData = Optional.ofNullable(subjectData).orElseThrow(() -> GuliException.from(EduResultCode.FILE_IS_EMPTY));


        //添加一级科目,不存在，则添加
        Subject oneSubject = getOneSubjectByName(subjectService, subjectData.getOneSubjectName());
        if(Objects.isNull(oneSubject)){
            oneSubject  = Subject.builder()
                    .parentId("0")
                    .title(subjectData.getOneSubjectName())
                    .build();

            subjectService.save(oneSubject);
        }

        //获取一级科目id
        String parentId = oneSubject.getId();

        Subject twoSubject = getTwoSubjectByName(subjectService, subjectData.getTwoSubjectName(), parentId);

        //如果二级科目不存在，则保存
        if(Objects.isNull(twoSubject)){
            twoSubject  = Subject.builder()
                    .parentId(parentId)
                    .title(subjectData.getTwoSubjectName())
                    .build();

            subjectService.save(twoSubject);
        }

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }


    /**
     *  根据名称查询一级科目是否存在
     * @param subjectService
     * @param name  科目名称
     * @return  Subject  科目
     */
    private Subject getOneSubjectByName(SubjectService subjectService,String name){
        LambdaQueryWrapper<Subject> query = new LambdaQueryWrapper<>();
        query.eq(Subject::getTitle,name);
        query.eq(Subject::getParentId,"0");
        return subjectService.getOne(query);
    }


    /**
     *  根据名称查询二级科目是否存在
     * @param subjectService
     * @param name  科目名称
     * @param pid 一级科目id
     * @return  Subject  科目
     */
    private Subject getTwoSubjectByName(SubjectService subjectService,String name,String pid){
        LambdaQueryWrapper<Subject> query = new LambdaQueryWrapper<>();
        query.eq(Subject::getTitle,name);
        query.eq(Subject::getParentId,pid);
        return subjectService.getOne(query);
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {

        System.out.println("head"+headMap);
    }


}
