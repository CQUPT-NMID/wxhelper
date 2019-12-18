package cn.edu.cqupt.nmid.wxhelper.wxhelper.utils;

import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.Item;

import java.util.List;

/**
 * @author MaYunHao
 * @version 1.0
 * @description 分页帮助类
 * @date 2019/12/13 16:07
 */

public class PageHelper<T> {

    private Integer start;
    private Integer end;
    private Integer currentPage;
    private Integer total;
    private Integer pageSize  = 10;
    private List<T> items;

    public Integer getPageSize() {
        return pageSize;
    }
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
    public List<T> getItems() {
        return items;
    }
    public void setItems(List<T> items) {
        this.items = items;
    }
    public Integer getCurrentPage() {
        return currentPage;
    }
    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }
    public Integer getStart() {
        return start;
    }
    public void setStart(Integer start) {
        this.start = start;
    }
    public Integer getEnd() {
        return end;
    }
    public void setEnd(Integer end) {
        this.end = end;
    }
    public Integer getTotal() {
        return total;
    }
    public void setTotal(Integer total) {
        this.total = total;
    }
}
