package cn.edu.cqupt.nmid.wxhelper.wxhelper.enums;

import io.swagger.annotations.Api;

/**
 * @author MaYunHao
 * @version 1.0
 * @description
 * @date 2020/2/5 16:15
 */


public enum RankBy {

    //默认
    DEFAULT("0",""),
    //通过点赞数排名
    LIKE_NUM("1","likenum"),
    //通过评论数排名
    COMMENT_NUM("2","collectnum"),
    //通过收藏数排名
    COLLECTION_NUM("3","commentnum");


    private String sign;
    private String msg;

     RankBy(String sign, String msg) {
        this.sign = sign;
        this.msg = msg;
    }

    public String getSign() {
        return sign;
    }

    public String getMsg() {
        return msg;
    }
}
