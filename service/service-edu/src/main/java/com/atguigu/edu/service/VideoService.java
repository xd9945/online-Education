package com.atguigu.edu.service;

import com.atguigu.edu.entity.Video;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author xudongzhou
 * @since 2022-07-05
 */
public interface VideoService extends IService<Video> {

    /**
     * 根据课程id删除视频
     * @param courseId
     */
    void deleteVideoByCourseId(String courseId);
}
