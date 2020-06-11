package cn.edu.cqupt.nmid.wxhelper.wxhelper.service;

import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.Comment;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.vo.CommentVo;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.utils.page.PageRequest;

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

    /**
     * 查询所有的评论
     * @return
     */
    List<CommentVo> list();
}
