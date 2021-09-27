package com.yimi.oysc.service;

import com.yimi.oysc.entity.RoleEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yimi.oysc.vo.add.AddRoleVO;
import com.yimi.oysc.vo.update.UpdateRoleVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Davy
 * @since 2021-09-26
 */
public interface IRoleService extends IService<RoleEntity> {


    RoleEntity add(AddRoleVO vo, String username);

    RoleEntity update(UpdateRoleVO vo, String username);
}
