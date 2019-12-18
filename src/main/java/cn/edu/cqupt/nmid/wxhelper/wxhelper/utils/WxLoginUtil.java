package cn.edu.cqupt.nmid.wxhelper.wxhelper.utils;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.util.*;

public class WxLoginUtil {

    public static final String APPID = "wx5339814510e6f070";
    public static final String APPSecret = "93e7de563b43b3bd4d53b058c38547cb";

    /**
     * 获取用户得到openid
     *
     * @param code      临时登陆凭证小程序的appSecret
     * @return
     */
    public static Map<String, Object> getWxUserOpenid(String code) {
        //拼接url
        StringBuilder url = new StringBuilder("https://api.weixin.qq.com/sns/jscode2session?");
        url.append("appid=");//appid设置
        url.append(APPID);
        url.append("&secret=");//secret设置
        url.append(APPSecret);
        url.append("&js_code=");//code设置
        url.append(code);
        url.append("&grant_type=authorization_code");
        Map<String, Object> map = null;
        try {
            HttpClient client = HttpClientBuilder.create().build();//构建一个Client
            HttpGet get = new HttpGet(url.toString()); //构建一个GET请求
            HttpResponse response = client.execute(get);//提交GET请求
            HttpEntity result = response.getEntity();//拿到返回的HttpResponse的"实体"
            String content = EntityUtils.toString(result);
            System.out.println(content);//打印返回的信息
            map = JSON.parseObject(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }



}
