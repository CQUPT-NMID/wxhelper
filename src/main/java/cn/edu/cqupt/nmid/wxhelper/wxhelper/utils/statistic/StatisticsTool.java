package cn.edu.cqupt.nmid.wxhelper.wxhelper.utils.statistic;

import cn.edu.cqupt.nmid.wxhelper.wxhelper.dao.UserDao;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.BaseItem;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author MaYunHao
 * @version 1.0
 * @description  用于统计的工具类
 * @date 2020/2/12 18:32
 */

public class StatisticsTool {


    private class Statistics {
        /**
         * 项目id
         */
        private Integer itemid;
        /**
         * 收藏数
         */
        private Integer collection_num;
        /**
         * 喜欢人数
         */
        private Integer like_num;
        /**
         * 评论人数
         */
        private Integer comment_num;

        public Statistics(Integer itemid) {
            this.itemid = itemid;
        }



    }

    public static List<Statistics> statistics;

    static {
        //初始化
        statistics = new ArrayList<>();
    }


    /**
     * 保存到数据库
     */
    public void freshAndSave(){
        
    }


}
