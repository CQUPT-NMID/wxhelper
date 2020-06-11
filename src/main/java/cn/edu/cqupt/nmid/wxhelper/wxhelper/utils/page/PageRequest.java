package cn.edu.cqupt.nmid.wxhelper.wxhelper.utils.page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author MaYunHao
 * @version 1.0
 * @description
 * @date 2020/2/4 13:33
 */
@ApiModel(value = "PageRequest",description = "分页请求类")
public class PageRequest {
    /**
     * 当前页码 默认为0
     */
    @ApiModelProperty(value="当前页码",name="pageNum",example="0",required = false)
    private int pageNum = 0;
    /**
     * 每页数量 默认为0
     */
    @ApiModelProperty(value="每页大小",name="pageSize",example="0",required = false)
    private int pageSize = 0;

    public int getPageNum() {
        return pageNum;
    }
    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}