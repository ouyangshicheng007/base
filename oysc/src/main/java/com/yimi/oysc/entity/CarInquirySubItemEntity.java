package com.yimi.oysc.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 车险询价单险别信息
 * </p>
 *
 * @author Davy
 * @since 2021-06-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("car_inquiry_sub_item")
public class CarInquirySubItemEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 子询价单id
     */
    @TableField("subId")
    private Integer subId;

    /**
     * 保司询价单号
     */
    @TableField("inquiryId")
    private String inquiryId;

    /**
     * 险别代码
     */
    @TableField("itemCode")
    private String itemCode;

    /**
     * 险别选项id
     */
    @TableField("itemSelectId")
    private Integer itemSelectId;

    /**
     * 名称
     */
    @TableField("name")
    private String name;

    /**
     * 保障内容
     */
    @TableField("content")
    private String content;

    /**
     * 保费
     */
    @TableField("premium")
    private Double premium;

    /**
     * 保额
     */
    @TableField("amount")
    private Double amount;

    /**
     * 创建时间
     */
    @TableField("createTime")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField("updateTime")
    private Date updateTime;


}
