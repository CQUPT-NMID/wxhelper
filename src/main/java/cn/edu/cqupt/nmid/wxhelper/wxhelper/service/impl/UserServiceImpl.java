package cn.edu.cqupt.nmid.wxhelper.wxhelper.service.impl;

import cn.edu.cqupt.nmid.wxhelper.wxhelper.dao.ItemDao;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.dao.UserDao;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.BaseItem;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.User;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.dao.StatisticDao;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.service.ItemService;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Qualifier("userDao")
    @Autowired
    private UserDao userDao;

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private StatisticDao statisticDao;

    @Override
    public User login(User loginUser) {
        User user = userDao.login(loginUser.getId());
        if(user == null){
            userDao.saveUser(loginUser);
            user = loginUser;
        }
        return user;
    }

    @Override
    public List<BaseItem> getHistoryByUserId(String id,String sign) {
        return itemService.getHistoryByUserId(id,sign);
    }

    @Override
    public void likeItem(String userid,Integer item_id, Boolean isLike) {
        //让item的like数量+1；
        statisticDao.updateLikeNum(item_id,isLike,1);
        //让用户与item关联，及让用户喜欢的表增加该条记录
        userDao.likeItem(userid,item_id,isLike);
    }

    @Override
    public void collectionItem(String userid,Integer item_id, Boolean isCollection) {
        //让item的收藏数量+1,或减1；
        statisticDao.updateCollectNum(item_id,isCollection,1);
        userDao.collectionItem(userid,item_id,isCollection);
    }

    @Override
    public void commentItem(String userid,Integer item_id, Boolean isComment) {
        userDao.commentItem(userid,item_id,isComment);
    }

    @Override
    public void deleteHistroy(Integer itemid,String userid) {
        userDao.deleteHistory(itemid,userid);
    }

    @Override
    public void saveHistory(String userid, Integer itemid) {
        userDao.saveHistory(userid,itemid,new Date());
    }
}
