package com.yimi.oysc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yimi.oysc.entity.PermissionEntity;
import com.yimi.oysc.vo.add.AddPermissionVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Davy
 * @since 2021-09-26
 */
public interface IPermissionService extends IService<PermissionEntity> {

    PermissionEntity add(AddPermissionVO vo, String username);


    List<PermissionEntity> listByRoleId(Integer roleId);

    List<PermissionEntity> listByUserId(Integer userId);

    Boolean deletePermission(Integer permissionId);
}
