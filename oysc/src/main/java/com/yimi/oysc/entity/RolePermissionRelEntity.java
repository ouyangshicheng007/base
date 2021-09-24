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
 * @since 2021-09-24
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
    private Integer ID;

    @TableField("ROLE_ID")
    private Integer ROLE_ID;

    @TableField("PERMISSION_ID")
    private Integer PERMISSION_ID;

    @TableField("CREATE_TIME")
    private LocalDateTime CREATE_TIME;

    @TableField("CREATE_BY")
    private String CREATE_BY;


}
