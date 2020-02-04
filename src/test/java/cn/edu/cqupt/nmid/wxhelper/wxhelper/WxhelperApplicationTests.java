package cn.edu.cqupt.nmid.wxhelper.wxhelper;



import cn.edu.cqupt.nmid.wxhelper.wxhelper.dao.ItemDao;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.BaseItem;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.service.ItemService;
import com.alibaba.druid.support.spring.stat.annotation.Stat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class WxhelperApplicationTests {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemDao itemDao;

    @Test
    void contextLoads() {

    }
}
