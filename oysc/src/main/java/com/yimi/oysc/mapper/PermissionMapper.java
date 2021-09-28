package com.yimi.oysc.mapper;

import com.yimi.oysc.entity.PermissionEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Davy
 * @since 2021-09-26
 */
public interface PermissionMapper extends BaseMapper<PermissionEntity> {


    List<PermissionEntity> listByRoleId(@Param("roleId")Integer roleId);
}
