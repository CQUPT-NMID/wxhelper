package cn.edu.cqupt.nmid.wxhelper.wxhelper.po;

import java.util.ArrayList;
import java.util.List;

public class Comment {

    private Integer id;
    private String content;
    /**
     * 用户头像
     */
    private String avater;
    /**
     * 用户昵称
     */
    private String nickname;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getAvater() {
        return avater;
    }

    public void setAvater(String avater) {
        this.avater = avater;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", avater='" + avater + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }

}
