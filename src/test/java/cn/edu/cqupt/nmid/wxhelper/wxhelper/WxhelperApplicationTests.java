package cn.edu.cqupt.nmid.wxhelper.wxhelper;



import cn.edu.cqupt.nmid.wxhelper.wxhelper.service.ItemService;
import com.alibaba.druid.support.spring.stat.annotation.Stat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WxhelperApplicationTests {

    @Autowired
    private ItemService itemService;

    @Test
    void contextLoads() {

    }
}
