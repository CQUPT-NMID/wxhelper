package cn.edu.cqupt.nmid.wxhelper.wxhelper.service;

import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.Comment;

import java.util.List;

/**
 * @author MaYunHao
 * @version 1.0
 * @description
 * @date 2019/12/12 20:58
 */

public interface CommentService {
    void publishComment(Integer itemid,String content,String userid);

    List<Comment> getCommentByItemId(Integer itemid);

    List<Comment> getCommentByUserId(String userid);

    void deleteCommentByCommentId(Integer commentid);
}
