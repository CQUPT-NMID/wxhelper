package cn.edu.cqupt.nmid.wxhelper.wxhelper.controller.admin;

import cn.edu.cqupt.nmid.wxhelper.wxhelper.enums.Status;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.Recruitinfo;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.Volunteer;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.query.Reply;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.service.RecruitinfoService;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.service.VolunteerService;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.utils.Result;
import io.swagger.annotations.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @author MaYunHao
 * @version 1.0
 * @description
 * @date 2020/3/11 16:18
 */

@Api(value = "/api/admin/volunteer", tags = "后台管理：志愿相关")
@RestController
@RequestMapping("/api/admin/volunteer")
public class AdminVolunteer {
    private final RecruitinfoService recruitinfoService;

    private final VolunteerService volunteerService;

    public AdminVolunteer(RecruitinfoService recruitinfoService, VolunteerService volunteerService) {
        this.recruitinfoService = recruitinfoService;
        this.volunteerService = volunteerService;
    }

    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "title",value = "标题",required = true),
            @ApiImplicitParam(name = "url",value = "图片",required = true),
            @ApiImplicitParam(name = "content",value = "内容",required = true),
            @ApiImplicitParam(name = "publishTime",value = "发布时间",required = false),
            @ApiImplicitParam(name = "closeTime",value = "招募结束时间",required = true)
    })
    @ApiOperation("提交招募信息")
    @PostMapping
    public Result publishRecruitInfo(@RequestBody @ApiParam(value = "招募信息",required = true) @Validated Recruitinfo recruitinfo){
        Recruitinfo info = recruitinfoService.insert(recruitinfo);
        HashMap<String, Object> map = new HashMap<>();
        map.put("id",info.getId());
        return Result.success(map);
    }

    @ApiOperation("修改招募信息")
    @PutMapping
    public Result updateRecruitInfo(@RequestBody @ApiParam(value = "招募信息",required = true) @Validated Recruitinfo recruitinfo){
        if (recruitinfo.getId() == null){
            return Result.failure(Status.PARAM_IS_INVALID);
        }
        Recruitinfo info = recruitinfoService.update(recruitinfo);
        HashMap<String, Object> map = new HashMap<>();
        map.put("id",info.getId());
        return Result.success(map);
    }

    @ApiOperation("删除招募信息")
    @DeleteMapping("/{id}")
    public Result deleteRecruitInfo(@PathVariable(required = true) Integer id){
        boolean b = recruitinfoService.deleteById(id);
        if (b){
            return Result.success();
        }
        return Result.failure(Status.PARAM_IS_INVALID);
    }


    @ApiOperation("删除用户的志愿信息")
    @DeleteMapping("/user/{id}")
    public Result deleteVolunteerInfo(@PathVariable(required = true) Integer id){
            boolean b = volunteerService.deleteById(id);
            if (b){
                return Result.success();
            }
        return Result.failure(Status.PARAM_IS_INVALID);
    }

    @ApiOperation("获取所有用户的志愿信息")
    @GetMapping("/user")
    public Result getVolunteerInfos(){
        List<Volunteer> volunteers = volunteerService.queryAllByLimit(0, 1000);
        HashMap<String, Object> map = new HashMap<>();
        map.put("infos",volunteers);
        return Result.success(map);
    }

    @ApiOperation("回复用户的志愿信息")
    @PostMapping("/reply")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "content",value = "回复内容",required = true),
            @ApiImplicitParam(name = "id",required = true,value = "用户志愿信息id"),
            @ApiImplicitParam(name = "status",required = true,value = "是否通过")
    })
    public Result Reply(@RequestBody Reply reply){
        Volunteer volunteer = new Volunteer();
        volunteer.setId(reply.getId());
        volunteer.setStatus(reply.getStatus() ? 1 : 0);
        volunteer.setReply(reply.getContent());
        volunteerService.update(volunteer);
        return Result.success();
    }

    //查询志愿信息

}
