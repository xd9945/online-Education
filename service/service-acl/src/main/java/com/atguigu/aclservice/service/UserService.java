package com.atguigu.aclservice.service;

import com.atguigu.aclservice.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author xudongzhou
 * @since 2022-06-29
 */
public interface UserService extends IService<User> {

    // 从数据库中取出用户信息
    User selectByUsername(String username);

}
