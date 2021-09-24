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
@TableName("t_user")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer ID;

    @TableField("USERNAME")
    private String USERNAME;

    @TableField("PASSWORD")
    private String PASSWORD;

    /**
     * 1:启用，0：禁用
     */
    @TableField("STATUS")
    private Integer STATUS;

    /**
     * 1:男性，0：女性
     */
    @TableField("SEX")
    private Integer SEX;

    @TableField("CREATE_TIME")
    private LocalDateTime CREATE_TIME;

    @TableField("CREATE_BY")
    private String CREATE_BY;

    @TableField("UPDATE_TIME")
    private LocalDateTime UPDATE_TIME;

    @TableField("UPDATE_BY")
    private String UPDATE_BY;


}
