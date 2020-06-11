package cn.edu.cqupt.nmid.wxhelper.wxhelper.dao;

import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.BaseItem;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.Item;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UserDao {

    @Select("select * from user where id = #{id}")
    User login(String id);

    @Insert("INSERT INTO `wxhelper`.`user` (`id`, `avater`, `nickname`, `gender`, `city`, `province`, `country`) VALUES (#{id}, #{avater}, #{nickname}, #{gender}, #{city}, #{province}, #{country});")
    void saveUser(User user);

    @Insert("insert into history(user_id,item_id,`time`) values(#{userid},#{item_id},#{date})")
    void saveHistory(String userid, Integer item_id, Date date);

    @Delete("delete from history where item_id=#{itemid} and userId =#{userid}")
    void deleteHistory(Integer itemid,String userid);

    @Update("update history set `like` = #{isLike} where item_id = #{item_id}")
    void likeItem(String userid,Integer item_id, Boolean isLike);

    @Update("update history set collect = #{isCollection} where item_id = #{item_id}")
    void collectionItem(String userid,Integer item_id, Boolean isCollection);

    @Update("update history set comment = #{isComment} where item_id = #{item_id}")
    void commentItem(String userid,Integer item_id, Boolean isComment);
}
