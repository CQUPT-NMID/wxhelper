package cn.edu.cqupt.nmid.wxhelper.wxhelper;



import cn.edu.cqupt.nmid.wxhelper.wxhelper.controller.IndexController;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.controller.NewsController;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.controller.admin.AdminComment;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.controller.admin.AdminController;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.controller.admin.AdminNews;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.dao.ItemDao;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.dao.StatisticDao;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.Era;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.Item;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.News;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.Type;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.query.ItemQuery;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.service.ItemService;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.service.NewsService;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.utils.GroupTool;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.utils.Result;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.utils.page.PageRequest;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.utils.page.PageResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class WxhelperApplicationTests {

    @Autowired
    private StatisticDao statisticDao;

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private NewsController newsController;

    @Autowired
    private NewsService newsService;

    @Autowired
    private AdminNews adminNews;

    @Autowired
    private AdminController adminController;

    @Autowired
    private AdminComment adminComment;

    @Autowired
    private IndexController indexController;

    @Test
    void contextLoads() {
        List<Type> allType = itemDao.getAllType();
        allType.forEach(type -> {
            System.out.println(type.getTypename());
        });
        List<Era> allEra = itemDao.getAllEra();
        allEra.forEach(
                era ->{
                    System.out.println(era.getEraname());
        });
    }
    @Test
    void postTest(){
        Result result = newsController.publishAdvice("很不错");
        System.out.println(result);
    }
    @Test
    void getTest()
    {
//        Result result = adminNews.listAdvice();
//        System.out.println(result);
//
//        ItemQuery itemQuery = new ItemQuery();
//        PageRequest request = new PageRequest();
//        request.setPageNum(1);
//        request.setPageSize(5);
//        itemQuery.setPageRequest(request);
//        Result result = adminController.index(itemQuery);
//        System.out.println(result);
//        Result item = indexController.getItemById(30);
//        System.out.println(item);

    }



}
