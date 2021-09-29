package com.yimi.oysc.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.yimi.oysc.enumerate.PermissionTypeEnum;
import com.yimi.oysc.enumerate.StatusEnum;
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
@TableName("t_permission")
public class PermissionEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 名称
     */
    @TableField("NAME")
    private String name;

    /**
     * URL:接口地址，MENU：菜单，BUTTON：按钮，FIELD：字段
     */
    @TableField("TYPE")
    private PermissionTypeEnum type;

    /**
     * 资源地址
     */
    @TableField("URL")
    private String url;

    /**
     * 1:启用，0：禁用
     */
    @TableField("STATUS")
    private StatusEnum status;

    /**
     * 父ID，为空表示为最顶层节点
     * @Date: 2021/9/27 15:19
    **/
    @TableField("PARENT_ID")
    private Integer parentId;

    /**
     * 备注
     */
    @TableField("REMARK")
    private String remark;

    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    @TableField("CREATE_BY")
    private String createBy;

    @TableField("UPDATE_TIME")
    private LocalDateTime updateTime;

    @TableField("UPDATE_BY")
    private String updateBy;


}
