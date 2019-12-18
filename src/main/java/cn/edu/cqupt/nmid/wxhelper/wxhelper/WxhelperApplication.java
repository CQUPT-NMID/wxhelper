package cn.edu.cqupt.nmid.wxhelper.wxhelper;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.edu.cqupt.nmid.wxhelper.wxhelper.dao")
public class WxhelperApplication {

    public static void main(String[] args) {
        SpringApplication.run(WxhelperApplication.class, args);
    }
}
