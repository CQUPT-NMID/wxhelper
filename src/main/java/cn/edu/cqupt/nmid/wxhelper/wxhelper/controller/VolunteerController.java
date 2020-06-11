package cn.edu.cqupt.nmid.wxhelper.wxhelper.controller;

import cn.edu.cqupt.nmid.wxhelper.wxhelper.enums.Status;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.Recruitinfo;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.Volunteer;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.service.RecruitinfoService;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.service.VolunteerService;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.service.impl.VolunteerServiceImpl;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.utils.GroupTool;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.utils.Result;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.utils.page.PageRequest;
import io.swagger.annotations.*;
import io.swagger.models.auth.In;
import javafx.scene.chart.ValueAxis;
import org.aspectj.weaver.NameMangler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.security.SignatureException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author MaYunHao
 * @version 1.0
 * @description
 * @date 2020/3/11 16:45
 */
@Api(value = "/api/user",tags = "志愿相关")
@RestController
@RequestMapping("/api/user/volunteer")
public class VolunteerController {

    private final VolunteerService volunteerService;

    private final RecruitinfoService recruitinfoService;



    public VolunteerController(VolunteerService volunteerService, RecruitinfoService recruitinfoService) {
        this.volunteerService = volunteerService;
        this.recruitinfoService = recruitinfoService;
    }

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

    @ApiOperation("获取招募信息列表")
    @GetMapping("/info")
    public Result getRecruitList(){
        List<Recruitinfo> recruitinfos = recruitinfoService.queryAllByLimit(0, 1000);
        GroupTool<Recruitinfo> groupTool = new GroupTool<>();
        List<List<Recruitinfo>> group = groupTool.group(recruitinfos);
        HashMap<String, Object> map = new HashMap<>();
        map.put("infos",group);
        return Result.success(map);
    }
    @ApiOperation("通过id获取招募信息")
    @GetMapping("/info/{id}")
    public Result getRecruitInfoById(@PathVariable Integer id){
        Recruitinfo recruitinfo = recruitinfoService.queryById(id);
        HashMap<String, Object> map = new HashMap<>();
        map.put("info",recruitinfo);
        return Result.success(map);
    }



    @ApiOperation("填写志愿信息")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "name",value = "志愿者姓名",required = true),
            @ApiImplicitParam(name = "telephone",value = "电话",required = false),
            @ApiImplicitParam(name = "email",value = "邮箱",required = true),
            @ApiImplicitParam(name = "publishTime",value = "发布时间",required = false),
            @ApiImplicitParam(name = "rid",value = "招募信息对应的id",required = false)
    })
    @PostMapping
    public Result publishVolunteerInfo(@RequestBody Volunteer volunteer){
        Recruitinfo recruitinfo = recruitinfoService.queryById(volunteer.getRid());
        if (recruitinfo.getCloseTime().after(new Date())){
            return Result.failure(Status.EXCEED);
        }
        volunteer.setUserid(getUserId());
        Integer id = volunteerService.publishVolunteerInfo(volunteer);
        HashMap<String, Object> map = new HashMap<>();
        map.put("id",id);
        return Result.success();
    }

    @ApiOperation("获取自己填写的志愿信息列表")
    @GetMapping
    public Result getVolunteerInfos(){
        List<Volunteer> volunteers = volunteerService.queryByUserId(getUserId());
        HashMap<String, Object> map = new HashMap<>();
        map.put("infos",volunteers);
        return Result.success(map);
    }

    @ApiOperation("通过id获取志愿信息")
    @GetMapping("/{id}")
    public Result getVolunteerInfos(@PathVariable Integer id){
        Volunteer volunteer = volunteerService.queryById(id);
        HashMap<String, Object> map = new HashMap<>();
        map.put("infos",volunteer);
        return Result.success(map);
    }

    @ApiOperation("删除自己的志愿信息，别人的志愿信息禁止删除")
    @DeleteMapping("/{id}")
    public Result deleteVolunteerInfo(@PathVariable(required = true) Integer id){
        Volunteer volunteer = volunteerService.queryById(id);
        if (volunteer.getUserid().equals(getUserId())){
            //只能删除自己的
            boolean b = volunteerService.deleteById(id);
            if (b){
                return Result.success();
            }
        }else {
            return Result.failure(Status.Forbidden);
        }
        return Result.failure(Status.PARAM_IS_INVALID);
    }

}
