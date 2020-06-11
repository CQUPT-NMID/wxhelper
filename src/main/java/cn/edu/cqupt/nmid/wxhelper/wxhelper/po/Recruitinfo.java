package cn.edu.cqupt.nmid.wxhelper.wxhelper.po;

import cn.edu.cqupt.nmid.wxhelper.wxhelper.utils.Groupable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Future;
import java.util.Date;
import java.io.Serializable;

/**
 * (Recruitinfo)实体类
 *
 * @author MaYunHao
 * @since 2020-03-11 16:12:38
 */
@ApiModel(description = "招募信息")
public class Recruitinfo implements Serializable , Groupable {

    private static final long serialVersionUID = 747849142896478406L;
    @ApiModelProperty(value = "id",notes = "更新的时候传id，新增的时候不传")
    private Integer id;
    @ApiModelProperty(value = "标题",required = true)
    private String title;
    @ApiModelProperty(value = "首图",required = true)
    private String url;
    @ApiModelProperty(value = "内容",required = true)
    private String content;
    @ApiModelProperty(value = "发布时间",required = false)
    private Date publishTime;

    @Future
    @ApiModelProperty(value = "招募结束日期",required = true, notes = "时间必须是未来的时间")
    private Date closeTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

}