package cn.edu.cqupt.nmid.wxhelper.wxhelper.controller;

import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.BaseItem;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.Item;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.service.ItemService;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.service.impl.ItemServiceImpl;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.utils.Result;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.utils.page.PageRequest;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.utils.page.PageResult;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.JsonObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
@Api(value = "/api/index",description = "查询展品相关")
@RestController
@RequestMapping(path = "/api/index",produces = {"application/json"})
public class IndexController {

    @Autowired
    private ItemService itemService;

    @ApiOperation("默认查询")
    @PostMapping("")
    public Result index(@RequestBody @ApiParam(value = "分页请求类",type = "PageRequest" ,required = true) PageRequest pageRequest){
        PageHelper.startPage(pageRequest.getPageNum(),pageRequest.getPageSize());
        List<BaseItem> items = itemService.getAll();
        PageInfo pageInfo = new PageInfo<>(items);
        PageResult pageResult = new PageResult(pageInfo);
        HashMap<String, Object> map = new HashMap<>();
        map.put("item",pageResult);
        return  Result.success(map);
    }


    @ApiOperation("通过类型获取展品")
    @PostMapping("/getItemsByType")
    public Result getItemsByType(@RequestBody @ApiParam(value = "分页请求类",required = true) PageRequest pageRequest,
            @RequestParam @ApiParam(name = "id",value = "类型id",required = true) Integer id){

        PageHelper.startPage(pageRequest.getPageNum(),pageRequest.getPageSize());
        List<BaseItem> items = itemService.getItemsByType(id);
        PageResult pageResult = new PageResult(new PageInfo(items));
        HashMap<String, Object> map = new HashMap<>();
        map.put("item",pageResult);

        return Result.success(map);
    }


    @ApiOperation("通过年代获取展品")
    @PostMapping("/getItemsByEra")
    public Result getItemsByEra(@RequestBody @ApiParam(value = "分页请求类",required = true) PageRequest pageRequest
            ,@RequestParam @ApiParam(name = "id",value = "年代id",required = true) Integer id){
        PageHelper.startPage(pageRequest.getPageNum(),pageRequest.getPageSize());
        List<BaseItem> items = itemService.getItemsByEra(id);
        PageResult pageResult = new PageResult(new PageInfo(items));
        HashMap<String, Object> map = new HashMap<>();
        map.put("item",pageResult);

        return Result.success(map);
    }


    @ApiOperation("通过名称获取展品")
    @PostMapping("/searchByName")

    public Result SearchByName(@RequestBody @ApiParam(value = "分页请求类",required = true) PageRequest pageRequest,
                               @RequestParam @ApiParam(name="itemName",value ="展品名称",required = true) String itemName){
        PageHelper.startPage(pageRequest.getPageNum(),pageRequest.getPageSize());
        List<BaseItem> items = itemService.searchByName(itemName);
        PageResult pageResult = new PageResult(new PageInfo(items));
        HashMap<String, Object> map = new HashMap<>();
        map.put("item",pageResult);

        return Result.success(map);
    }


    @ApiOperation("通过收藏量的多少")
    @PostMapping("/getItemsByCollection")
    public Result getItemsByCollection(@RequestBody @ApiParam(value = "分页请求类",required = true) PageRequest pageRequest){
        PageHelper.startPage(pageRequest.getPageNum(),pageRequest.getPageSize());
        List<BaseItem> items = itemService.getItemsByCollection();
        PageResult pageResult = new PageResult(new PageInfo(items));
        HashMap<String, Object> map = new HashMap<>();
        map.put("item",pageResult);
        return Result.success(map);
    }


    @ApiOperation("通过点击量的多少")

    @PostMapping("/getItemsByClick")
    public Result getItemsByClick(@RequestBody @ApiParam(value = "分页请求类",required = true) PageRequest pageRequest){
        PageHelper.startPage(pageRequest.getPageNum(),pageRequest.getPageSize());
        List<BaseItem> items = itemService.getItemsByClick();
        PageResult pageResult = new PageResult(new PageInfo(items));
        HashMap<String, Object> map = new HashMap<>();
        map.put("item",pageResult);
        return Result.success(map);
    }


    @ApiOperation("通过评论数的多少")
    @PostMapping("/getItemsByComment")
    public Result getItemsByComment(@RequestBody @ApiParam(value = "分页请求类",required = true) PageRequest pageRequest){
        PageHelper.startPage(pageRequest.getPageNum(),pageRequest.getPageSize());
        List<BaseItem> items = itemService.getItemsByComment();
        PageResult pageResult = new PageResult(new PageInfo(items));
        HashMap<String, Object> map = new HashMap<>();
        map.put("item",pageResult);
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
