package cn.edu.cqupt.nmid.wxhelper.wxhelper.controller;

import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.BaseItem;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.Item;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.User;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.service.ItemService;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.service.UserService;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.service.impl.UserServiceImpl;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.utils.Result;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

/**
 * @author MaYunHao
 * @version 1.0
 * @description
 * @date 2019/12/9 18:34
 */
@Api(value = "/api/user",description = "用户相关")
@RestController("/api/user")
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private ItemService itemService;

    /**
     * 辅助类
     *获取request中的 userid
     * @return
     */
    private String getUserId(){
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        String userid = (String)request.getAttribute("userid");
        return userid;
    }

    /**
     * 获取用户的访问历史
     * @return
     */
    @ApiOperation(value = "获取用户历史")
    @GetMapping("/getHistory")
    public Result getHistory(){
        String userId = getUserId();
        List<BaseItem> histrorys = userService.getHistoryByUserId(userId);
        HashMap<String, Object> map = new HashMap<>();
        map.put("item",histrorys);
        return Result.success(map);
    }

    /**
     * 给展品点赞
     * @param itemid
     * @return
     */
    @ApiOperation("点赞")
    @GetMapping("/likeItem")
    @ApiIgnore
    public String likeItem(@RequestParam(required = true) Integer itemid){
        String userId = getUserId();
        //让item的like数量+1；

        //让用户与item关联，及让用户喜欢的表增加该条记录

        return  "";
    }


    @ApiOperation("取消点赞")
    @GetMapping("dislikeItem")
    @ApiIgnore
    public String dislikeItem(@RequestParam Integer itemid){

        return "";
    }

}
