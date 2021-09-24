package com.yimi.oysc.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 询价单保司询价结果表
 * </p>
 *
 * @author Davy
 * @since 2021-06-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("car_inquiry")
public class CarInquiryEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 保司询价单号
     */
    @TableId("inquiryId")
    private String inquiryId;

    /**
     * 订单号
     */
    @TableField("orderId")
    private String orderId;

    /**
     * 商户id
     */
    @TableField("merchantId")
    private String merchantId;

    /**
     * 保险公司代码
     */
    @TableField("insurCompanyCode")
    private String insurCompanyCode;

    /**
     * 总保费
     */
    @TableField("premium")
    private Double premium;

    /**
     * 总保额
     */
    @TableField("amount")
    private Double amount;

    /**
     * 业务扩展属性
     */
    @TableField("busiInfo")
    private String busiInfo;

    /**
     * 生成订单：1.是，0.否
     */
    @TableField("genOrder")
    private Integer genOrder;

    /**
     * 状态：0.询价中，1.询价成功，2.询价失败，3.询价待校验
     */
    @TableField("status")
    private Integer status;

    /**
     * 询价消息备注
     */
    @TableField("msg")
    private String msg;

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
