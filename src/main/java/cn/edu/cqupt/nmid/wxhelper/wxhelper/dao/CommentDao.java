package cn.edu.cqupt.nmid.wxhelper.wxhelper.dao;

import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.Comment;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.vo.CommentVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author MaYunHao54
 * @version 1.0
 * @description
 * @date 2019/12/12 20:
 */
@Repository
public interface CommentDao {

    @Insert("insert into comment(content,item_id,user_id) values(#{content},#{itemid},#{userid})")
    void saveComment(Integer itemid,String content,String userid);

    @Select("select comment_id,content,avater,nickname from commentview where item_id =#{itemid}")
    List<Comment> getCommentByItemId(Integer itemid);

    @Select("select comment_id,content,avater,nickname from commentview where user_id = #{user_id}")
    List<Comment> getCommentByUserId(String userid);

    @Delete("delete from comment where id = #{commentid}")
    void deleteCommentByCommentId(Integer commentid);

    @Select("select comment_id,content,avater,nickname from commentview where comment_id = #{commentid}")
    Comment getCommentByCommentId(Integer commentid);

    @Select("select comment_id as commentId , content ,itemname as itemName,item_id as itemId,publish_time as publishTime from commentview")
    List<CommentVo> list();
}
