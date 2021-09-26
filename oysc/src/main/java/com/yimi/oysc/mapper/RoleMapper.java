package com.yimi.oysc.mapper;

import com.yimi.oysc.entity.RoleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yimi.oysc.vo.SelectRoleCondVO;
import com.yimi.oysc.vo.SelectRoleVO;
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



    List<SelectRoleVO> selectList(@Param("condVO") SelectRoleCondVO condVO);

}
