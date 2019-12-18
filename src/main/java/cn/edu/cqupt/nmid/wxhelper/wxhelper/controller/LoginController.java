package cn.edu.cqupt.nmid.wxhelper.wxhelper.controller;

import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.User;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.utils.WxLoginUtil;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.service.UserService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.sun.org.apache.bcel.internal.generic.DASTORE;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;


@RestController
public class LoginController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Qualifier(value = "userServiceImpl")
    @Autowired
    private UserService userService;

    @ApiOperation("登陆")
    @RequestMapping(value = "/login",method = {RequestMethod.POST})
    @ResponseBody
    public String Login(@ApiParam("code") @RequestParam String code,
                        String nickName,
                        int gender,
                        String city,
                        String province,String country,
                        String avatarUrl,
                        HttpSession session){
        JSONObject returnData = new JSONObject();
        int status = 0;
        try {
            //获取微信账号的openid
            Map<String, Object> map = WxLoginUtil.getWxUserOpenid(code);
       //     System.out.println(map);
            String openId = (String) map.get("openid");
            logger.info("微信账号的openid为： "+openId);
            if (openId == null){
                throw  new Exception("无法获取openid");
            }

            User user = new User(openId, nickName, gender == 1 ? "男" : "女", city, province, country, avatarUrl);
            System.out.println(user);

            session.setAttribute("user",user);
            userService.login(user);
            status = 200;
        }catch (Exception e){
            logger.error(e.getMessage());
            status = 400;
        }

        returnData.put("status",status);
        returnData.put("session",session);
        return returnData.toJSONString();

    }

}
