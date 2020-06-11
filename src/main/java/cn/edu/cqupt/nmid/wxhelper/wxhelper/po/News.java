package cn.edu.cqupt.nmid.wxhelper.wxhelper.po;

import cn.edu.cqupt.nmid.wxhelper.wxhelper.utils.Groupable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author MaYunHao
 * @version 1.0
 * @description
 * @date 2020/3/1 20:51
 */

@ApiModel(description = "博物馆新闻")
public class News implements Groupable {

    private Long id;
    @ApiModelProperty(value = "标题",required = true)
    private String title;
    @ApiModelProperty(value = "图片",required = true)
    private String url;
    @ApiModelProperty(value = "内容",required = true)
    private String content;
    @ApiModelProperty(value = "发布时间",required = false)
    private Date publishTime;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", content='" + content + '\'' +
                ", publishTime=" + publishTime +
                '}';
    }
}
