package com.yimi.oysc.vo.add;

import com.yimi.oysc.enumerate.PermissionTypeEnum;
import com.yimi.oysc.enumerate.StatusEnum;
import lombok.Data;

/**
 * Auto-Generated
 *
 * @Author Davy
 * @Date 2021/9/27 15:09
 **/
@Data
public class AddPermissionVO {

    /**
     * 名称
     */
    private String name;

    /**
     * URL:接口地址，MENU：菜单，BUTTON：按钮，FIELD：字段
     */
    private PermissionTypeEnum type;

    /**
     * 资源地址
     */
    private String url;

    /**
     * 1:启用，0：禁用
     */
    private StatusEnum status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 父ID，为空表示为最顶层节点
     **/
    private Integer parentId;
}
