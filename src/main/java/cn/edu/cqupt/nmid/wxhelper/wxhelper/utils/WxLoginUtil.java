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

    public static final String APPID = "wxcd5625a6f15d74b9";
    public static final String APPSecret = "19b513001986dca3274c74b4dae7aa78";

    /**
     * 获取用户得到openid
     *
     * @param code      临时登陆凭证小程序的appSecret
     * @return
     */
    public static Map<String, Object> getWxUserOpenid(String code) {
        //拼接url
        StringBuilder url = new StringBuilder("https://api.weixin.qq.com/sns/jscode2session?");
        //appid设置
        url.append("appid=");
        url.append(APPID);
        //secret设置
        url.append("&secret=");
        url.append(APPSecret);
        //code设置
        url.append("&js_code=");
        url.append(code);
        url.append("&grant_type=authorization_code");
        Map<String, Object> map = null;
        try {
            //构建一个Client
            HttpClient client = HttpClientBuilder.create().build();
            //构建一个GET请求
            HttpGet get = new HttpGet(url.toString());
            //提交GET请求
            HttpResponse response = client.execute(get);
            //拿到返回的HttpResponse的"实体"
            HttpEntity result = response.getEntity();
            String content = EntityUtils.toString(result);
            //打印返回的信息
            System.out.println(content);
            map = JSON.parseObject(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }



}
