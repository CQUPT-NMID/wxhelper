package cn.edu.cqupt.nmid.wxhelper.wxhelper.po;

import io.swagger.annotations.ApiModel;

/**
 * @author MaYunHao
 * @version 1.0
 * @description
 * @date 2020/2/5 1:18
 */

public class Type {
    private Integer id;
    private String typename;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }
}
