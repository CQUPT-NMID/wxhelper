package cn.edu.cqupt.nmid.wxhelper.wxhelper.utils;

import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.News;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MaYunHao
 * @version 1.0
 * @description
 * @date 2020/3/10 10:58
 */

public class GroupTool<T extends Groupable> {
    /**
     * 将新闻中同一时间段得新闻分为一组
     * @param lists
     * @return
     */
    public List<List<T>> group(List<T> lists){
        int length = lists.size();
        ArrayList<List<T>> newList = new ArrayList<>();
        int newLength = 0;
        for (int i = 0; i < length; i++) {
            //原始列表
            T ts = lists.get(i);
            newLength = newList.size();
            boolean flag = true;
            for (int j = 0; j < newLength; j++) {
                //加入
                List<T> oneGroup = newList.get(j);
                if (ts.getPublishTime().getHours() == oneGroup.get(0).getPublishTime().getHours()){
                    oneGroup.add(ts);
                    flag = false;
                    break;
                }
            }
            if (flag){
                //创建组
                ArrayList<T> list = new ArrayList<>();
                list.add(ts);
                newList.add(list);
            }
        }
        return newList;
    }
}
