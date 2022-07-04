package com.atguigu.edu.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author xudongzhou
 * @date 2022-07-05
 * @desc  分类
 */
@Data
public class SubjectData {

    @ExcelProperty(index = 0)
    private String oneSubjectName;


    @ExcelProperty(index = 1)
    private String twoSubjectName;
}
