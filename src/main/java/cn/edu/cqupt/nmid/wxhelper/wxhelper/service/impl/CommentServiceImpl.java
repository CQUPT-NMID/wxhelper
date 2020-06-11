package cn.edu.cqupt.nmid.wxhelper.wxhelper.service.impl;

import cn.edu.cqupt.nmid.wxhelper.wxhelper.dao.CommentDao;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.dao.StatisticDao;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.dao.UserDao;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.Comment;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.vo.CommentVo;
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

    @Autowired
    private UserDao userDao;

    @Autowired
    private StatisticDao statisticDao;

    @Override
    public void publishComment(Integer itemid, String content, String userid) {
        //保留评论
        commentDao.saveComment(itemid,content,userid);
        //用户历史历史标记为评论
        userDao.commentItem(userid,itemid,true);
        //在item表中让评论加1
        statisticDao.updateCommentNum(itemid,true,1);
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

    @Override
    public List<CommentVo> list() {
        return commentDao.list();
    }
}
