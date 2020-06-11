package cn.edu.cqupt.nmid.wxhelper.wxhelper.service;

import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.BaseItem;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.Item;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.User;

import java.util.List;

public interface UserService {

    /**
     * 登陆
     * @param user
     * @return
     */
    User login(User user);

    /**
     * 通过用户id获取用户的所有历史记录
     * @param id
     * @param sign 条件
     * @return
     */
    List<BaseItem> getHistoryByUserId(String id,String sign);

    /**
     * 改变历史表中item_id的记录的喜欢状态
     * @param userid 用户id
     * @param item_id
     * @param isLike 是否喜欢
     */
    void likeItem(String userid,Integer item_id,Boolean isLike);

    /**
     * 改变历史表中item_id为收藏状态
     * @param userid 用户id
     * @param item_id
     * @param isCollection 是否收藏
     */
    void collectionItem(String userid,Integer item_id,Boolean isCollection);

    /**
     * 评论完成或者删除后改变记录
     * @param item_id
     * @param isComment
     */
    void commentItem(String userid,Integer item_id,Boolean isComment);

    /**
     * 删除历史记录
     * @param itemid
     * @param userid
     */
    void deleteHistroy(Integer itemid,String userid);

    /**
     * 添加历史
     * @param userid
     * @param itemid
     */
    void saveHistory(String userid, Integer itemid);
}
