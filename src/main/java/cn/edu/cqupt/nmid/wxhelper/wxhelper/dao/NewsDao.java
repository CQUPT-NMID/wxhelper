package cn.edu.cqupt.nmid.wxhelper.wxhelper.dao;

import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.Advice;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.News;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author MaYunHao
 * @version 1.0
 * @description
 * @date 2020/3/1 21:01
 */
@Repository
public interface NewsDao {

    @Select("select id,title,url,publish_time as publishTime from news order by publish_time")
    List<News> list();

    @Select("select id,title,url,publish_time as publishTime,content,from news")
    News getNewsById(Long id);

    @Insert("insert into advice(advice,user_id,publish_time) values(#{advice},#{userId},#{publishTime})")
    @Options(useGeneratedKeys = true,keyProperty ="id",keyColumn = "id")
    Long publishAdvice(Advice adv);


    @Insert("insert into news(content,url,publish_time,title) values(#{content},#{url},#{publishTime},#{title})")
    @Options(useGeneratedKeys = true,keyProperty ="id",keyColumn = "id")
    Long publishNews(News news);

    @Select("select id,advice ,user_id as userId,publish_time as publishTime from advice order by publish_time")
    List<Advice> listAdvice();

    @Delete("delete from news where id = #{id}")
    void deleteNews(Long id);

    @Update("update news set content = #{content},title = #{title},url = #{url} where id = #{id}")
    void updateNews(News news);

    @Delete("delete from advice where id = {id}")
    void deleteAdvice(Long id);
}
