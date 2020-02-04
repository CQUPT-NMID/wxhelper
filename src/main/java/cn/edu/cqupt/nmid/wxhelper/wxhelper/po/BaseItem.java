package cn.edu.cqupt.nmid.wxhelper.wxhelper.po;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MaYunHao
 * @version 1.0
 * @description  基本展品信息类
 * @date 2019/12/13 16:13
 */

public class BaseItem {
    private Integer id;

    private List<String> photos;
    private String name;
    private Integer collectnum;
    private Integer likenum;
    private Integer commentnum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Integer getCollectnum() {
        return collectnum;
    }

    public void setCollectnum(Integer collectnum) {
        this.collectnum = collectnum;
    }

    public Integer getLikenum() {
        return likenum;
    }

    public void setLikenum(Integer likenum) {
        this.likenum = likenum;
    }

    public Integer getCommentnum() {
        return commentnum;
    }

    public void setCommentnum(Integer commentnum) {
        this.commentnum = commentnum;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    @Override
    public String toString() {
        return "BaseItem{" +
                "id=" + id +
                ", photos=" + photos +
                ", name='" + name + '\'' +
                ", collectnum=" + collectnum +
                ", likenum=" + likenum +
                ", commentnum=" + commentnum +
                '}';
    }
}
