package com.yimi.oysc.vo.common;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Auto-Generated
 *
 * @Author Davy
 * @Date 2021/9/26 15:40
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("分页入参封装对象")
public class PageWrapper<T> {

    @NotNull
    @ApiModelProperty("一页展示多少条数据")
    private Integer pageSize = 10;

    @NotNull
    @ApiModelProperty("当前是第多少页")
    private Integer currentPage = 1;

    @ApiModelProperty("升序排序字段")
    private List<String> ascColumnList;

    @ApiModelProperty("降序排序字段")
    private List<String> descColumnList;

    public <T> Page<T> toDbPage() {
        Page<T> page = new Page<T>();
        page.setSize(this.pageSize);
        page.setCurrent(this.currentPage);

        if (ascColumnList != null) {
            List<OrderItem> list = ascColumnList.stream().map(OrderItem::asc).collect(Collectors.toList());
            page.addOrder(list);
        }
        if (descColumnList != null) {
            List<OrderItem> list = descColumnList.stream().map(OrderItem::desc).collect(Collectors.toList());
            page.addOrder(list);
        }


        return page;
    }
}
