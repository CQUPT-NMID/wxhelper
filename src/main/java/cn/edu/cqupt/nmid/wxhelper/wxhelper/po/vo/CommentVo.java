package cn.edu.cqupt.nmid.wxhelper.wxhelper.po.vo;

import io.swagger.annotations.ApiModelProperty;
import sun.awt.windows.WPrinterJob;

import java.util.Date;

/**
 * @author MaYunHao
 * @version 1.0
 * @description
 * @date 2020/5/1 13:25
 */


public class CommentVo {


    @ApiModelProperty(value = "评论id")
    private Integer commentId;

    @ApiModelProperty(value = "评论内容")
    private  String content;

    @ApiModelProperty(value = "发布时间")
    private Date publishTime;

    @ApiModelProperty(value = "展品id")
    private Integer itemId;

    @ApiModelProperty(value = "展品名称")
    private String itemName;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public String toString() {
        return "CommentVo{" +
                "commentId=" + commentId +
                ", content='" + content + '\'' +
                ", publishTime=" + publishTime +
                ", itemId=" + itemId +
                ", itemName='" + itemName + '\'' +
                '}';
    }
}
