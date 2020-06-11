package cn.edu.cqupt.nmid.wxhelper.wxhelper.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @author MaYunHao
 * @version 1.0
 * @description
 * @date 2020/3/2 22:33
 */

@Component
@Mapper
public interface StatisticDao {


    void updateLikeNum(Integer itemid, Boolean isAdd,Integer num);

    void updateCollectNum(Integer itemid, Boolean isAdd, Integer num);

    void updateCommentNum(Integer itemid, Boolean isAdd, Integer num);

}
