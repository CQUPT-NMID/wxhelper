package cn.edu.cqupt.nmid.wxhelper.wxhelper.controller;

import cn.edu.cqupt.nmid.wxhelper.wxhelper.enums.RankBy;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.enums.Status;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.BaseItem;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.Item;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.User;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.service.ItemService;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.service.UserService;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.service.impl.UserServiceImpl;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.utils.Result;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
@Api(value = "/api/user",tags = "用户相关")
@RestController
@RequestMapping("/api/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

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
    @ApiOperation(value = "获取用户历史 可带条件")
    @GetMapping("/getHistory")
    public Result getHistory(@RequestParam(required = false)@ApiParam(value = "条件1 2 3" ,required = false) RankBy sign){
        String userId = getUserId();
        List<BaseItem> histrorys = userService.getHistoryByUserId(userId,sign != null ? sign.getSign():null);
        HashMap<String, Object> map = new HashMap<>();
        map.put("item",histrorys);
        return Result.success(map);
    }

    @ApiOperation(("删除历史"))
    @GetMapping("/deleteHistory")
    public Result deleteHistory(@RequestParam @ApiParam("展品id") Integer itemid){
        try{
            String userId = getUserId();
            userService.deleteHistroy(itemid,userId);
            return Result.success();
        }catch (Exception e){
            logger.error(e.getMessage());
            return Result.failure(Status.SysError);
        }
    }


    /**
     * 给展品点赞
     * @param itemid
     * @return
     */
    @ApiOperation("点赞或取消点赞")
    @GetMapping("/likeItem")
    public Result likeItem(@RequestParam(required = true) @ApiParam("展品id") Integer itemid,
                           @RequestParam(required = true) @ApiParam("判断是否点赞") Boolean like){
        String userId = getUserId();
        userService.likeItem(userId,itemid,like);
        return  Result.success();
    }

    /**
     * 收藏展品
     * @param itemid
     * @param collect
     * @return
     */
    @ApiOperation("收藏或取消收藏")
    @GetMapping("/collectItem")
    public Result collectItem(@RequestParam(required = true) @ApiParam("展品id") Integer itemid,
                           @RequestParam(required = true) @ApiParam("判断是否收藏") Boolean collect){
        String userId = getUserId();
        //在用户历史记录处标记为 收藏或取消收藏
        userService.collectionItem(userId,itemid,collect);
        return  Result.success();
    }

}
