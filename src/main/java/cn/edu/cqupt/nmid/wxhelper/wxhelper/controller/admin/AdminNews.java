package cn.edu.cqupt.nmid.wxhelper.wxhelper.controller.admin;

import cn.edu.cqupt.nmid.wxhelper.wxhelper.enums.Status;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.Advice;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.News;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.service.NewsService;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @author MaYunHao
 * @version 1.0
 * @description
 * @date 2020/3/1 21:43
 */

@Api(value = "/api/admin", tags = "后台管理：新闻，建议相关")
@RestController
@RequestMapping("/api/admin")
public class AdminNews {

    @Autowired
    private NewsService newsService;

    @ApiOperation("发布或修改新闻")
    @PostMapping("/news")
    public Result updateNews(@RequestBody @ApiParam(value = "新闻",required = true)News news){
        Long id = newsService.updateNews(news);
        HashMap<String, Object> map = new HashMap<>();
        map.put("id",id);
        return Result.success(map);
    }

    @ApiOperation("通过id 删除新闻")
    @DeleteMapping("/news/{id}")
    public Status deleteNews(@PathVariable Long id){
        newsService.deleteNews(id);
        return Status.SUCCESS;
    }


    @ApiOperation("获取建议列表")
    @GetMapping("/advice")
    public Result listAdvice(){
        List<Advice> list = newsService.listAdvice();
        HashMap<String, Object> map = new HashMap<>();
        map.put("list",list);
        return Result.success(map);
    }

    @ApiOperation("通过id 删除建议")
    @DeleteMapping("/advice/{id}")
    public Status deleteAdvice(@PathVariable Long id){
        newsService.deleteAdvice(id);
        return Status.SUCCESS;
    }




}


