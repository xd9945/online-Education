package com.atguigu.edu.entity.subject;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * @author xudongzhou
 * @date 2022-07-05
 * @desc
 */
@Data
public class OneSubject {

    private String id;

    private String title;

    /**
     * 一个一级分类里面有多个二级分类
     */
    List<TwoSubject> children = Lists.newArrayList();

}
