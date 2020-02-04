package cn.edu.cqupt.nmid.wxhelper.wxhelper.controller;

import cn.edu.cqupt.nmid.wxhelper.wxhelper.config.JwtConfig;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.enums.Status;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.User;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.utils.ResponseResult;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.utils.Result;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.utils.Role;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.utils.WxLoginUtil;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Api(description = "登陆")
@Controller
@RequestMapping(value = "/api",produces = {"application/json"})
public class LoginController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Qualifier(value = "userServiceImpl")
    @Autowired
    private UserService userService;

    @Autowired
    private JwtConfig jwtConfig;

    @ApiOperation("登陆")
    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    @ResponseBody
    public Result Login(@ApiParam("code") @RequestParam String code,
                        String nickName,
                        int gender,
                        String city,
                        String province,
                        String country,
                        String avatarUrl) {
        try {
            //获取微信账号的openid
            Map<String, Object> map = WxLoginUtil.getWxUserOpenid(code);
            System.out.println(map);
            String openId = (String) map.get("openid");
            logger.info("微信账号的openid为： " + openId);
            if (openId == null) {
                throw new Exception("无法获取openid");
            }

            User user = new User(openId, nickName, gender , city, province, country, avatarUrl);
            System.out.println(user);

            userService.login(user);
            //获取并返回token
            String token = jwtConfig.createToken(openId, Role.USER);
            HashMap<String, Object> datamap = new HashMap<>();
            datamap.put("token", token);
            return Result.success(datamap);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.failure(Status.FAISE);
        }
    }

    /**
     * 用于测试情况的登陆
     *
     * @param userid
     * @return
     */
    @RequestMapping(value = "/loginIn", method = {RequestMethod.GET})
    @ResponseBody
    @ApiIgnore
    public Result LoginForTest(@RequestParam String userid) {
        String token = jwtConfig.createToken(userid,Role.USER);
        HashMap<String, Object> map = new HashMap<>();
        map.put("token", token);
        return Result.success(map);
    }

    /**
     * 获取userid的方式
     *
     * @return
     */
    @RequestMapping(value = "/info")
    @ResponseBody
    @ApiIgnore
    public Result Test() {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        String userid = (String) request.getAttribute("userid");
        HashMap<String, Object> map = new HashMap<>();
        map.put("userid", userid);
        return Result.success(map);
    }

}
