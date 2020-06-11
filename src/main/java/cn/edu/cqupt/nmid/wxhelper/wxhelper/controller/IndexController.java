package cn.edu.cqupt.nmid.wxhelper.wxhelper.controller;

import cn.edu.cqupt.nmid.wxhelper.wxhelper.enums.RankBy;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.enums.Status;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.BaseItem;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.Era;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.Item;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.Type;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.query.ItemQuery;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.service.ItemService;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.service.UserService;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.service.impl.ItemServiceImpl;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.utils.Result;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.utils.page.PageRequest;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.utils.page.PageResult;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.JsonObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * @author MaYunHao
 * @version 1.0
 * @description
 * @date 2019/12/19 16:51
 */
@Api(value = "/api/index", description = "查询展品相关")
@RestController
@RequestMapping(path = "/api/index", produces = {"application/json"})
public class IndexController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    @ApiOperation("默认查询")
    @PostMapping("")
    public Result index(@RequestBody(required = false) @ApiParam(value = "分页请求类", type = "PageRequest", required = false) PageRequest pageRequest) {
        if (pageRequest == null) {
            pageRequest = new PageRequest();
        }
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        List<BaseItem> items = itemService.getAll();
        PageInfo pageInfo = new PageInfo<>(items);
        PageResult pageResult = new PageResult(pageInfo);
        HashMap<String, Object> map = new HashMap<>();
        map.put("item", pageResult);
        return Result.success(map);
    }


    @ApiOperation("通过类型获取展品")
    @PostMapping("/getItemsByType")
    public Result getItemsByType(@RequestBody(required = false) @ApiParam(value = "分页请求类", type = "PageRequest", required = false) PageRequest pageRequest,
                                 @RequestParam @ApiParam(name = "id", value = "类型id", required = true) Integer id) {
        if (pageRequest == null) {
            pageRequest = new PageRequest();
        }
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        List<BaseItem> items = itemService.getItemsByType(id);
        PageResult pageResult = new PageResult(new PageInfo(items));
        HashMap<String, Object> map = new HashMap<>();
        map.put("item", pageResult);

        return Result.success(map);
    }


    @ApiOperation("通过年代获取展品")
    @PostMapping("/getItemsByEra")
    public Result getItemsByEra(@RequestBody(required = false) @ApiParam(value = "分页请求类", type = "PageRequest", required = false) PageRequest pageRequest
            , @RequestParam @ApiParam(name = "id", value = "年代id", required = true) Integer id) {
        if (pageRequest == null) {
            pageRequest = new PageRequest();
        }
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        List<BaseItem> items = itemService.getItemsByEra(id);
        PageResult pageResult = new PageResult(new PageInfo(items));
        HashMap<String, Object> map = new HashMap<>();
        map.put("item", pageResult);

        return Result.success(map);
    }


    @ApiOperation("通过名称获取展品")
    @PostMapping("/searchByName")
    public Result SearchByName(@RequestBody(required = false) @ApiParam(value = "分页请求类", type = "PageRequest", required = false) PageRequest pageRequest,
                               @RequestParam @ApiParam(name = "itemName", value = "展品名称", required = true) String itemName) {
        if (pageRequest == null) {
            pageRequest = new PageRequest();
        }
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        List<BaseItem> items = itemService.searchByName(itemName);
        PageResult pageResult = new PageResult(new PageInfo(items));
        HashMap<String, Object> map = new HashMap<>();
        map.put("item", pageResult);

        return Result.success(map);
    }


    @ApiOperation("多条件查询")
    @PostMapping("/mutiQuery")
    public Result index(@RequestBody(required = false) @ApiParam(value = "查询辅助类", type = "ItemQuery", required = false) ItemQuery itemQuery) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            PageRequest pageRequest = null;
            if (itemQuery != null){
                 pageRequest = itemQuery.getPageRequest();
            }else {
                pageRequest = new PageRequest();
            }
            PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
            List<BaseItem> items = itemService.index(itemQuery);
            PageResult pageResult = new PageResult(new PageInfo(items));
            map.put("item", pageResult);
            return Result.success(map);
        } catch (Exception e) {
            logger.error("index ", e.getMessage());
            map.put("err", e.getMessage());
            return Result.failure(Status.SysError, map);
        }
    }


    @ApiOperation("通过收藏量的多少")
    @PostMapping("/getItemsByCollection")
    public Result getItemsByCollection(@RequestBody(required = false) @ApiParam(value = "分页请求类", type = "PageRequest", required = false) PageRequest pageRequest) {
        if (pageRequest == null) {
            pageRequest = new PageRequest();
        }
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        List<BaseItem> items = itemService.getItemsByCollection();
        PageResult pageResult = new PageResult(new PageInfo(items));
        HashMap<String, Object> map = new HashMap<>();
        map.put("item", pageResult);
        return Result.success(map);
    }


    @ApiOperation("通过点击量的多少")

    @PostMapping("/getItemsByClick")
    public Result getItemsByClick(@RequestBody(required = false) @ApiParam(value = "分页请求类", type = "PageRequest", required = false) PageRequest pageRequest) {
        if (pageRequest == null) {
            pageRequest = new PageRequest();
        }
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        List<BaseItem> items = itemService.getItemsByClick();
        PageResult pageResult = new PageResult(new PageInfo(items));
        HashMap<String, Object> map = new HashMap<>();
        map.put("item", pageResult);
        return Result.success(map);
    }


    @ApiOperation("通过评论数的多少")
    @PostMapping("/getItemsByComment")
    public Result getItemsByComment(@RequestBody(required = false) @ApiParam(value = "分页请求类", type = "PageRequest", required = false) PageRequest pageRequest) {
        if (pageRequest == null) {
            pageRequest = new PageRequest();
        }
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        List<BaseItem> items = itemService.getItemsByComment();
        PageResult pageResult = new PageResult(new PageInfo(items));
        HashMap<String, Object> map = new HashMap<>();
        map.put("item", pageResult);
        return Result.success(map);
    }


    @ApiOperation("获取展品的详情")
    @GetMapping("/getItemById/{id}")
    public Result getItemById(@PathVariable(required = true) Integer id) {
        Item item = itemService.getItemById(id);
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        String userid = (String) request.getAttribute("userid");
        //用户历史记录
        userService.saveHistory(userid,id);
        HashMap<String, Object> map = new HashMap<>();
        map.put("item", item);
        return Result.success(map);
    }



    @ApiOperation("查询所有类型")
    @GetMapping("/getAllType")
    public Result getAllType(){
        List<Type> types = itemService.getAllType();
        HashMap<String, Object> map = new HashMap<>();
        map.put("types",types);
        return Result.success(map);
    }

    @ApiOperation("查询所有年代")
    @GetMapping("/getAllEra")
    public Result getAllEra(){
        List<Era> eras = itemService.getAllEra();
        HashMap<String, Object> map = new HashMap<>();
        map.put("eras",eras);
        return Result.success(map);
    }


}
