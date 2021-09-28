package com.yimi.oysc.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yimi.oysc.entity.RoleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yimi.oysc.vo.select.SelectRoleVO;
import com.yimi.oysc.vo.result.SelectRoleResultVO;
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
public interface RoleMapper extends BaseMapper<RoleEntity> {



    /**
     * 分页查询
     * @Author: Davy
     * @Date: 2021/9/26 14:32
    **/
    Page<SelectRoleResultVO> selectRoles(IPage page, @Param("condVO") SelectRoleVO condVO);

//    查询全部
//    List<SelectRoleResultVO> selectRoles(@Param("condVO") SelectRoleCondVO condVO);


    List<RoleEntity> findRolesByUserId(@Param("userId") Integer userId);
}
