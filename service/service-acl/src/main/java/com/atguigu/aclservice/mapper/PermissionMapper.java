package com.atguigu.aclservice.mapper;

import com.atguigu.aclservice.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 权限 Mapper 接口
 * </p>
 *
 * @author xudongzhou
 * @since 2022-06-29
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    List<String> selectPermissionValueByUserId(String id);

    List<String> selectAllPermissionValue();

    List<Permission> selectPermissionByUserId(String userId);

}
