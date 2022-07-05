package com.atguigu.edu.service;

import com.atguigu.edu.entity.Teacher;
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
public interface TeacherService extends IService<Teacher> {

    /**
     * C端讲师分页查询
     * @param teacherPage 分页对象
     * @return 分页结果集
     */

    Page<Teacher> getTeacherFrontList(Page<Teacher> teacherPage);

}
