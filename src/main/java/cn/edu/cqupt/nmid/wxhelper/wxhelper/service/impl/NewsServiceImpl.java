package cn.edu.cqupt.nmid.wxhelper.wxhelper.service.impl;

import cn.edu.cqupt.nmid.wxhelper.wxhelper.dao.NewsDao;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.Advice;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.News;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author MaYunHao
 * @version 1.0
 * @description
 * @date 2020/3/1 20:49
 */
@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsDao newsDao;

    @Override
    public List<News> list() {
        return newsDao.list();
    }

    @Override
    public News getNewsById(Long id) {
        return newsDao.getNewsById(id);
    }

    @Override
    public Long publishAdvice(String userid, String advice) {
        Advice adv = new Advice();
        adv.setAdvice(advice);
        adv.setPublishTime(new Date());
        adv.setUserId(userid);
        newsDao.publishAdvice(adv);
        return adv.getId();
    }

    @Override
    public Long updateNews(News news) {
        if (news.getId() == null){
            news.setPublishTime(new Date());
            newsDao.publishNews(news);
        }else{
            newsDao.updateNews(news);
        }
        return news.getId();
    }

    @Override
    public List<Advice> listAdvice() {
        return newsDao.listAdvice();
    }

    @Override
    public void deleteNews(Long id) {
        newsDao.deleteNews(id);
    }

    @Override
    public void deleteAdvice(Long id) {
        newsDao.deleteAdvice(id);
    }
}
