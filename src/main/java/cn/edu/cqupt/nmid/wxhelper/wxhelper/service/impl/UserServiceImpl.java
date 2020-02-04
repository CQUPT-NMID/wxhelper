package cn.edu.cqupt.nmid.wxhelper.wxhelper.service.impl;

import cn.edu.cqupt.nmid.wxhelper.wxhelper.dao.UserDao;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.BaseItem;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.Item;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.User;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Qualifier("userDao")
    @Autowired
    private UserDao userDao;
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
    public List<BaseItem> getHistoryByUserId(String id) {
        return userDao.getHistoryByUserId(id);
    }

    @Override
    public void likeItem(Integer item_id, Boolean isLike) {
        userDao.likeItem(item_id,isLike);
    }

    @Override
    public void collectionItem(Integer item_id, Boolean isCollection) {
        userDao.collectionItem(item_id,isCollection);
    }

    @Override
    public void commentItem(Integer item_id, Boolean isComment) {
        userDao.commentItem(item_id,isComment);
    }
}
