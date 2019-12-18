package cn.edu.cqupt.nmid.wxhelper.wxhelper.controller;

import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.BaseItem;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.Item;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.service.ItemService;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.service.impl.ItemServiceImpl;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Type;
import java.util.List;

@RestController
@RequestMapping(path = "/index",produces = {"application/json"})
public class IndexController {

    @Autowired
    private ItemService itemService;

    @ApiOperation("通过类型获取展品")
    @GetMapping("/getItemsByType")
    public String getItemsByType(@RequestParam @ApiParam(name = "id",value = "类型id",required = true) Integer id,
                                  HttpSession session){
        List<BaseItem> items = itemService.getItemsByType(id);
        return JSONObject.toJSONString(items);
    }

    @ApiOperation("通过年代获取展品")
    @GetMapping("/getItemsByEra")
    public String getItemsByEra(@RequestParam @ApiParam(name = "id",value = "年代id",required = true) Integer id,
                                 HttpSession session){
        List<BaseItem> items = itemService.getItemsByEra(id);
        return JSONObject.toJSONString(items);
    }
    @GetMapping("/searchByName")
    public String SearchByName(@RequestParam @ApiParam(name="itemName",value ="展品名称",required = true) String itemName,
                                HttpSession session){
        List<BaseItem> items = itemService.searchByName(itemName);
        return JSONObject.toJSONString(items);

    }

    @ApiOperation("默认排名")
    @GetMapping("")
    public String index( HttpSession session){
        List<BaseItem> items = itemService.getAll();
        return  JSONObject.toJSONString(items);
    }

    @ApiOperation("通过收藏量的多少")
    @GetMapping("/getItemsByCollection")
    public String getItemsByCollection( HttpSession session){
        List<BaseItem> items = itemService.getItemsByCollection();
        return JSONObject.toJSONString(items);
    }

    @ApiOperation("通过点击量的多少")
    @GetMapping("/getItemsByClick")
    public String getItemsByClick( HttpSession session){
        List<BaseItem> items = itemService.getItemsByClick();
        return JSONObject.toJSONString(items);
    }
    @ApiOperation("通过评论数的多少")
    @GetMapping("/getItemsByComment")
    public String getItemsByComment( HttpSession session){
        List<BaseItem> items = itemService.getItemsByComment();
        return JSONObject.toJSONString(items);
    }

    @ApiOperation("获取展品的详情")
    @GetMapping("/getItemById/{id}")
    public Item getItemById(@PathVariable(required = true) Integer id){
        Item item = itemService.getItemById(id);
        return item;
    }
}
