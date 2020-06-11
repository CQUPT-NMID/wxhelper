package cn.edu.cqupt.nmid.wxhelper.wxhelper.po.query;

/**
 * @author MaYunHao
 * @version 1.0
 * @description
 * @date 2020/3/11 17:57
 */

public class Reply {

    private String content;
    private Integer id;
    private boolean status;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
