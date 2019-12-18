package cn.edu.cqupt.nmid.wxhelper.wxhelper.dao;

import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.BaseItem;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.Item;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    @Select("select * from user where id = #{id}")
    User login(String id);

    @Insert("INSERT INTO `wxhelper`.`user` (`id`, `avater`, `nickname`, `gender`, `city`, `province`, `country`) VALUES (#{id}, #{avater}, #{nickname}, #{gender}, #{city}, #{province}, #{country});")
    User saveUser(User user);

    @Select("select * from user where id = #{id} order by `time`")
   // @ResultMap("baseItemMap")
    List<BaseItem> getHistoryByUserId(String id);

    @Update("update history set islike = #{isLike} where item_id = #{item_id}")
    void likeItem(Integer item_id, Boolean isLike);

    @Update("update history set iscollection = #{isCollection} where item_id = #{item_id}")
    void collectionItem(Integer item_id, Boolean isCollection);

    @Update("update history set iscomment = #{isComment} where item_id = #{item_id}")
    void commentItem(Integer item_id, Boolean isComment);
}
