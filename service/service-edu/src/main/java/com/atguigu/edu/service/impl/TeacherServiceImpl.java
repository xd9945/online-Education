package com.atguigu.edu.service.impl;

import com.atguigu.edu.entity.Teacher;
import com.atguigu.edu.mapper.EduTeacherMapper;
import com.atguigu.edu.service.TeacherService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author xudongzhou
 * @since 2022-06-23
 *
 */
@Transactional
@Service
public class TeacherServiceImpl extends ServiceImpl<EduTeacherMapper, Teacher> implements TeacherService {

    /**
     * C端讲师分页查询
     *
     * @param teacherPage 分页对象
     * @return 分页结果集
     */
    @Override
    public Page<Teacher> getTeacherFrontList(Page<Teacher> teacherPage) {

        LambdaQueryWrapper<Teacher> teacherQuery = new LambdaQueryWrapper<>();
        teacherQuery.orderByDesc(Teacher::getId);

        teacherPage = (Page<Teacher>) this.page(teacherPage,teacherQuery);
        return teacherPage;
    }
}

