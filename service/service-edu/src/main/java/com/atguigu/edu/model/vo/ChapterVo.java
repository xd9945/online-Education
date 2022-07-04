package com.atguigu.edu.model.vo;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author xudongzhou
 * @date 2022-07-05
 * @desc
 */
@Data
@AllArgsConstructor
public class ChapterVo {

    private String id;

    private String title;


    /**
     * 小节
     */
    private List<VideoVo> children = Lists.newArrayList();

    public ChapterVo(String id, String title) {
        this.id = id;
        this.title = title;
    }
}
