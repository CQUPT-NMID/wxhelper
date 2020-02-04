package cn.edu.cqupt.nmid.wxhelper.wxhelper.controller;

import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.BaseItem;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.Item;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.service.ItemService;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.service.impl.ItemServiceImpl;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.utils.Result;
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
import java.util.HashMap;
import java.util.List;

/**
 * @author MaYunHao
 * @version 1.0
 * @description
 * @date 2019/12/19 16:51
 */

@RestController
@RequestMapping(path = "/index",produces = {"application/json"})
public class IndexController {

    @Autowired
    private ItemService itemService;

    @ApiOperation("默认排名")
    @GetMapping("")
    public Result index( ){
        List<BaseItem> items = itemService.getAll();
        HashMap<String, Object> map = new HashMap<>();
        map.put("item",items);
        return  Result.success(map);
    }


    @ApiOperation("通过类型获取展品")
    @GetMapping("/getItemsByType")
    public Result getItemsByType(@RequestParam @ApiParam(name = "id",value = "类型id",required = true) Integer id){
        List<BaseItem> items = itemService.getItemsByType(id);
        HashMap<String, Object> map = new HashMap<>();
        map.put("item",items);
        return Result.success(map);
    }


    @ApiOperation("通过年代获取展品")
    @GetMapping("/getItemsByEra")
    public Result getItemsByEra(@RequestParam @ApiParam(name = "id",value = "年代id",required = true) Integer id){
        List<BaseItem> items = itemService.getItemsByEra(id);
        HashMap<String, Object> map = new HashMap<>();
        map.put("item",items);
        return Result.success(map);
    }


    @ApiOperation("通过名称获取展品")
    @GetMapping("/searchByName")
    public Result SearchByName(@RequestParam @ApiParam(name="itemName",value ="展品名称",required = true) String itemName){
        List<BaseItem> items = itemService.searchByName(itemName);
        HashMap<String, Object> map = new HashMap<>();
        map.put("item",items);
        return Result.success(map);
    }


    @ApiOperation("通过收藏量的多少")
    @GetMapping("/getItemsByCollection")
    public Result getItemsByCollection(){
        List<BaseItem> items = itemService.getItemsByCollection();
        HashMap<String, Object> map = new HashMap<>();
        map.put("item",items);
        return Result.success(map);
    }


    @ApiOperation("通过点击量的多少")
    @GetMapping("/getItemsByClick")
    public Result getItemsByClick(){
        List<BaseItem> items = itemService.getItemsByClick();
        HashMap<String, Object> map = new HashMap<>();
        map.put("item",items);
        return Result.success(map);
    }


    @ApiOperation("通过评论数的多少")
    @GetMapping("/getItemsByComment")
    public Result getItemsByComment(){
        List<BaseItem> items = itemService.getItemsByComment();
        HashMap<String, Object> map = new HashMap<>();
        map.put("item",items);
        return Result.success(map);
    }


    @ApiOperation("获取展品的详情")
    @GetMapping("/getItemById/{id}")
    public Result getItemById(@PathVariable(required = true) Integer id){
        Item item = itemService.getItemById(id);
        HashMap<String, Object> map = new HashMap<>();
        map.put("item",item);
        return Result.success(map);
    }
}
