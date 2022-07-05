package com.atguigu.edu.service;

import com.atguigu.edu.entity.Chapter;
import com.atguigu.edu.model.vo.ChapterVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author xudongzhou
 * @since 2022-07-05
 */
public interface ChapterService extends IService<Chapter> {

    /**
     * 根据课程ID 查询课程的章节小节
     * @param courseId 课程id
     * @return  章节小节
     */
    List<ChapterVo> getChapterVideoByCourseId(String courseId);


    /**
     * 根据id删除章节
     * @param chapterId
     */
    void deleteChapter(String chapterId);

    /**
     * 根据课程id 删除章节
     * @param courseId 课程id
     */
    void deleteChapterByCourseId(String courseId);

}
