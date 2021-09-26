package com.yimi.oysc.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Davy
 * @since 2021-09-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_role_permission_rel")
public class RolePermissionRelEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    @TableField("ROLE_ID")
    private Integer roleId;

    @TableField("PERMISSION_ID")
    private Integer permissionId;

    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    @TableField("CREATE_BY")
    private String createBy;


}
