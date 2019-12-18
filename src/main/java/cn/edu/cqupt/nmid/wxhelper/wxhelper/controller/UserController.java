package cn.edu.cqupt.nmid.wxhelper.wxhelper.controller;

import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.BaseItem;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.Item;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.User;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.service.ItemService;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.service.UserService;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.service.impl.UserServiceImpl;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author MaYunHao
 * @version 1.0
 * @description
 * @date 2019/12/9 18:34
 */

@RestController("/user")
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private ItemService itemService;

    @ApiOperation(value = "获取用户历史")
    @GetMapping("/getHistory")
    public List<BaseItem> getHistory(HttpSession session){
        User user = (User) session.getAttribute("user");
        List<BaseItem> histrorys = userService.getHistoryByUserId(user.getId());
        return histrorys;
    }
    @ApiOperation("点赞")
    @GetMapping("/likeItem")
    public String likeItem( HttpSession session,Integer itemid){
        User user = (User)session.getAttribute("user");

        //让item的like数量+1；

        //让用户与item关联，及让用户喜欢的表增加该条记录

        return  "";
    }
    @ApiOperation("取消点赞")
    @GetMapping("dislikeItem")
    public String dislikeItem( HttpSession session,Integer itemid){

        return "";
    }
}
