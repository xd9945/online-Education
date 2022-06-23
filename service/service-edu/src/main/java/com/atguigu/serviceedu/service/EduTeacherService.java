package com.atguigu.serviceedu.service;

import com.atguigu.serviceedu.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author xudongzhou
 * @since 2022-06-23
 */
public interface EduTeacherService extends IService<EduTeacher> {

    Map<String, Object> getTeacherFrontList(Page<EduTeacher> pageTeacher);

}
