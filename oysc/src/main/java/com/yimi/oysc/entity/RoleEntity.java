package com.yimi.oysc.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.yimi.oysc.enumerate.StatusEnum;
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
@TableName("t_role")
public class RoleEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer ID;

    @TableField("NAME")
    private String NAME;

    /**
     * 1:启用，0：禁用
     */
    @TableField("STATUS")
    private StatusEnum STATUS;

    /**
     * 备注
     */
    @TableField("REMARK")
    private String REMARK;

    @TableField("CREATE_TIME")
    private LocalDateTime CREATE_TIME;

    @TableField("CREATE_BY")
    private String CREATE_BY;

    @TableField("UPDATE_TIME")
    private LocalDateTime UPDATE_TIME;

    @TableField("UPDATE_BY")
    private String UPDATE_BY;

}
