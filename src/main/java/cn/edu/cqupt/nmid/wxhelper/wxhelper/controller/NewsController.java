package cn.edu.cqupt.nmid.wxhelper.wxhelper.controller;

import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.News;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.service.NewsService;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.utils.GroupTool;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.utils.ResponseResult;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author MaYunHao
 * @version 1.0
 * @description
 * @date 2020/3/1 20:46
 */
@Api(tags = "新闻及意见反馈相关")
@RestController
@RequestMapping("/api")
public class NewsController {

    @Autowired
    public NewsService newsService;




    @ApiOperation("获取新闻列表")
    @GetMapping("/news")
    public Result list() {
        List<News> newsList = newsService.list();
        GroupTool<News> groupTool = new GroupTool<>();
        List<List<News>> list = groupTool.group(newsList);
        HashMap<String, Object> map = new HashMap<>();
        map.put("list", list);
        return Result.success(map);
    }

    @ApiOperation("根据id获取新闻具体内容")
    @GetMapping("/news/id")
    public Result getNewsById(@PathVariable @ApiParam(required = true) Long id) {
        News news = newsService.getNewsById(id);
        HashMap<String, Object> map = new HashMap<>();
        map.put("news", news);
        return Result.success(map);
    }

    @ApiOperation("添加意见")
    @PostMapping("/advice")
    public Result publishAdvice(@RequestBody String advice) {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        String userid = (String) request.getAttribute("userid");
        Long id = newsService.publishAdvice(userid,advice);
        HashMap<String, Object> map = new HashMap<>();
        map.put("id",id);
        return Result.success(map);
    }


}
