package com.yimi.oysc.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDate;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.yimi.oysc.enumerate.StatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@TableName("t_role")
@ApiModel("角色对象")
public class RoleEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "ID", type = IdType.AUTO)
    @ApiModelProperty("主键ID")
    private Integer id;

    @TableField("NAME")
    @ApiModelProperty("角色名称")
    private String name;

    @TableField("CODE")
    @ApiModelProperty("角色编码")
    private String code;

    /**
     * 1:启用，0：禁用
     */
    @TableField("STATUS")
    @ApiModelProperty("角色状态")
    private StatusEnum status;

    /**
     * 备注
     */
    @TableField("REMARK")
    @ApiModelProperty("角色备注")
    private String remark;

    @TableField("CREATE_TIME")
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @TableField("CREATE_BY")
    @ApiModelProperty("创建人")
    private String createBy;

    @TableField("UPDATE_TIME")
    @ApiModelProperty("修改时间")
    private LocalDateTime updateTime;

    @TableField("UPDATE_BY")
    @ApiModelProperty("修改人")
    private String updateBy;

    @TableField("TEST_DATE")
    private LocalDate testDate;

}
