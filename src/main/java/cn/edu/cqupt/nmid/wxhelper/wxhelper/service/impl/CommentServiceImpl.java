package cn.edu.cqupt.nmid.wxhelper.wxhelper.service.impl;

import cn.edu.cqupt.nmid.wxhelper.wxhelper.dao.CommentDao;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.Comment;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author MaYunHao
 * @version 1.0
 * @description
 * @date 2019/12/12 20:58
 */

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;
    @Override
    public void publishComment(Integer itemid, String content, String userid) {
        commentDao.saveComment(itemid,content,userid);
    }

    @Override
    public List<Comment> getCommentByItemId(Integer itemid) {
        return commentDao.getCommentByItemId(itemid);
    }

    @Override
    public List<Comment> getCommentByUserId(String userid) {
        return commentDao.getCommentByUserId(userid);
    }

    @Override
    public void deleteCommentByCommentId(Integer commentid) {
        commentDao.deleteCommentByCommentId(commentid);
    }
}
