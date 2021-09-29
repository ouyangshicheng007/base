package com.yimi.oysc.vo.common;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Auto-Generated
 *
 * @Author Davy
 * @Date 2021/9/26 15:27
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("分页结果封装对象")
public class PageResult<T> {

    @ApiModelProperty("一页展示多少条数据")
    private Integer pageSize;

    @ApiModelProperty("当前是第多少页")
    private Integer currentPage;

    @ApiModelProperty("总条数")
    private Integer totalCount;

    @ApiModelProperty("总页数")
    private Integer totalPages;

    @ApiModelProperty("分页数据")
    private T data;


    public static <T> ResultVO<PageResult<T>> toVoPage(Page<T> page) {
        int totalCount = (int)page.getTotal();
        int pageSize = (int)page.getSize();

        int totalPages = 0;
        int mod = Math.floorMod(totalCount, pageSize);
        if (mod == 0) {
            totalPages = totalCount / pageSize;
        } else {
            totalPages = (totalCount / pageSize) + 1;
        }

        PageResult<T> pageResult = new PageResult(  pageSize,
                                                (int)page.getCurrent(),
                                                totalCount,
                                                totalPages,
                                                page.getRecords());
        return ResultVO.successResult(pageResult);
    }
}
