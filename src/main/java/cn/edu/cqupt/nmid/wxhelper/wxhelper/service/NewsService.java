package cn.edu.cqupt.nmid.wxhelper.wxhelper.service;

import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.Advice;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.News;

import java.util.List;

/**
 * @author MaYunHao
 * @version 1.0
 * @description
 * @date 2020/3/1 20:49
 */
public interface NewsService {
    /**
     * 获取新闻列表
     * @return
     */
    List<News> list();

    /**
     * 通过id 获取新闻详情
     * @param id
     * @return
     */
    News getNewsById(Long id);

    /**
     * 发布意见
     * @param userid
     * @param advice
     * @return
     */
    Long publishAdvice(String userid, String advice);

    /**
     * 发布新闻
     * @param news
     * @return
     */
    Long updateNews(News news);

    /**
     * 获取建议列表
     * @return
     */
    List<Advice> listAdvice();

    /**
     * 通过id删除新闻
     * @param id
     */
    void deleteNews(Long id);

    /**
     * 通过id 删除id
     * @param id
     */
    void deleteAdvice(Long id);
}
