package cn.edu.cqupt.nmid.wxhelper.wxhelper.controller.admin;

import cn.edu.cqupt.nmid.wxhelper.wxhelper.MyException;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.config.JwtConfig;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.enums.Status;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.Admin;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.Era;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.Item;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.Type;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.service.AdminService;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.service.ItemService;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.service.impl.AdminServiceImpl;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.utils.Result;

import cn.edu.cqupt.nmid.wxhelper.wxhelper.utils.Role;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sf.jsqlparser.statement.execute.Execute;
import org.apache.ibatis.io.ResolverUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.print.DocFlavor;
import java.util.HashMap;
import java.util.List;


/**
 * @author MaYunHao
 * @version 1.0
 * @description  后台管理控制器
 *            主要功能（目前需要）
 *                添加展品 （最主要）
 *                删除展品
 *                修改展品
 *                查看全部
 *
 * @date 2019/12/13 11:08
 */
@Api(value = "/api/admin", description = "后台管理")
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private Logger logger = LoggerFactory.getLogger(AdminController.class);
    @Autowired
    private ItemService itemService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private JwtConfig jwtConfig;

    @ApiOperation("上传展品信息的同时上传文件")
    @PostMapping("/saveItemWithFile")
    @ResponseBody
    public Result saveItemWithFile(@RequestParam @ApiParam(value = "展品名称",required = true) String itemname,
                            @RequestParam @ApiParam(value = "展品介绍",required = true) String intro,
                            @RequestParam @ApiParam(value = "展品类型名",required = true) String typename ,
                            @RequestParam @ApiParam(value = "展品年代",required = true) String era,
                            @RequestParam(required = false) @ApiParam(value = "展品图片",required = false) MultipartFile[] photo,
                            @ApiParam(value = "展品视频",required = false) MultipartFile video  ){
        try {
            Integer id = itemService.saveItemAndFile(itemname, intro, typename, era, video, photo);
            HashMap<String, Object> map = new HashMap<>();
            map.put("id",id);
            return Result.success(map);
        }catch (Exception e){
            logger.error(e.getMessage());
            return Result.failure(Status.FAISE);
        }
    }



    @ApiOperation("添加展品信息")
    @PostMapping("/saveItem")
    @ResponseBody
    public Result saveItem(@RequestParam @ApiParam(value = "展品名称",required = true) String itemname,
                           @RequestParam @ApiParam(value = "展品介绍",required = true) String intro,
                           @RequestParam @ApiParam(value = "展品类型名",required = true) String typename ,
                           @RequestParam @ApiParam(value = "展品年代",required = true) String era,
                           @RequestParam(required = false) @ApiParam(value = "展品图片url",required = false) List<String> photos,
                           @ApiParam(value = "展品视频",required = false) String video  ){
        try {
            Integer id = itemService.saveItem(itemname,intro,typename,era,video,photos);
            HashMap<String, Object> map = new HashMap<>();
            map.put("id",id);
            return Result.success(map);
        }catch (Exception e){
            logger.error(e.getMessage());
            return Result.failure(Status.FAISE);
        }
    }

    /**
     * 删除展品
     * @param itemid  展品id
     * @return
     */
    @RequestMapping(value = "/deleteItem" ,method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("删除展品")
    public Result deleteItem(@RequestParam Integer itemid){
        try{
            itemService.deleteItem(itemid);
            return Result.success();
        }catch (Exception e){
            logger.error(e.getMessage());
            return Result.failure(Status.SysError);
        }
    }

    /**
     * 修改展品
     * @param item 有itemid的item
     * @return
     */
    @ApiOperation("修改展品")
    @PostMapping("/updateItem")
    @ResponseBody
    public Result updateItem(@RequestBody @ApiParam(required = true,value = "带有id的item") Item item){
        if(item.getId() == null){
            return Result.failure(Status.PARAM_IS_INVALID);
        }
        try {
            itemService.updateItem(item);
            return Result.success();
        }catch (Exception e){
            logger.error(e.getMessage());
            return Result.failure(Status.SysError);
        }
    }

//        ------------------------------------ 年代相关------------------------------------

    @ApiOperation("添加年代")
    @PostMapping("/inputEra")
    public Result inputEra(@RequestBody @ApiParam(required = true) Era era){
        itemService.saveEra(era);
        return Result.success();
    }

    @ApiOperation("删除年代")
    @PostMapping("/deleteEra/{id}")
    @ResponseBody
    public Result deleteEra(@PathVariable(name = "id",required = true) Integer id){
        try {
            itemService.deleteEra(id);
            return Result.success();
        } catch (Exception e) {
            logger.error("deleteEra" ,e.getMessage());
            return Result.failure(Status.SysError);
        }
    }

    @ApiOperation("修改年代")
    @PostMapping("/updateEra")
    public Result updateEra(@RequestBody @ApiParam(required = true,value = "带有id的era") Era era){
        itemService.updateEra(era);
        return Result.success();
    }

    @ApiOperation("查询所有年代")
    @PostMapping("/getAllEra")
    public Result getAllEra(){
        itemService.getAllEra();
        return Result.success();
    }
//        ------------------------------------ 类型相关------------------------------------

    @ApiOperation("添加类别")
    @PostMapping("/saveType")
    public Result saveType(@RequestBody @ApiParam(required = true) String itemname){
        itemService.saveTpye(itemname);
        return Result.success();
    }

    @ApiOperation("通过id或者类型名删除类型")
    @PostMapping("/deleteType")
    @ResponseBody
    public Result deleteType(@RequestBody Type type){
        try {
            itemService.deleteType(type);
            return Result.success();
        } catch (MyException e) {
            logger.error("deleteType" ,e.getMessage());
            return Result.failure(e.getStatus());
        }catch (Exception e){
            logger.error("deleteType" ,e.getMessage());
            return Result.failure(Status.SysError);
        }
    }

    @ApiOperation("修改类型")
    @PostMapping("/updateType")
    public Result updateType(@RequestBody @ApiParam(required = true,value = "带有id的era") Type type){
        itemService.updateType(type);
        return Result.success();
    }

    @ApiOperation("查询所有类型")
    @PostMapping("/getAllType")
    public Result getAllType(){
        List<Type> types = itemService.getAllType();
        return Result.success();
    }


    /**
     * 为展品添加图片
     * @param itemid 展品id
     * @param photos 图片
     * @return
     */
    @ApiOperation("为展品添加图片")
    @PostMapping("/updatePhoto")
    @ResponseBody
    public Result updatePhoto(@RequestParam @ApiParam(value = "展品id",required = true) Integer itemid,
                              @RequestParam @ApiParam(value = "图片",required = true) MultipartFile[] photos){
        try{
            itemService.updatePhoto(itemid,photos);
            return Result.success();
        }catch (Exception e){
            logger.error("addPhoto have error",e.getMessage());
            return Result.failure(Status.FAISE);
        }
    }

    /**
     * 上传图片
     * @param photos 图片
     * @return
     */
    @ApiOperation("上传图片")
    @PostMapping("/uploadPhoto")
    @ResponseBody
    public Result uploadPhoto(@RequestParam @ApiParam(value = "图片",required = true) MultipartFile[] photos){
        try{
           List<String> urls = itemService.uploadPhoto(photos);
            return Result.success();
        }catch (Exception e){
            logger.error("addPhoto have error",e.getMessage());
            return Result.failure(Status.FAISE);
        }
    }



    /**
     * 更新视频
     * @param itemid  展品id
     * @param video  视频
     * @return
     */
    @PostMapping("/updateVideo")
    @ResponseBody
    @ApiOperation("更新视频")
    public Result updateVideo(@RequestParam @ApiParam(value = "展品id",required = true) Integer itemid,
                              @RequestParam @ApiParam(value = "视频",required = true) MultipartFile video){
        try{
            itemService.updateVedio(itemid,video);
            return Result.success();
        }catch (Exception e){
            logger.error("addPhoto have error",e.getMessage());
            return Result.failure(Status.SysError);
        }
    }




    /**
     * 管理员登陆
     * @return
     */
    @ApiOperation("管理员登陆")
    @PostMapping("/loginAdmin")
    public Result loginAdmin(@RequestParam @ApiParam(value = "用户名",required = true) String username,
                             @RequestParam @ApiParam(value = "密码",required = true)  String password){
        try {

            Admin admin = new Admin();
            admin.setUsername(username);
            admin.setPassword(password);

            String token = jwtConfig.createToken(admin.getUsername(), Role.ADMIN);
            admin = adminService.login(admin);

            if(admin == null){
                //登录失败
                return Result.failure(Status.USER_LOGIN_FLASE);
            }

            HashMap<String, Object> map = new HashMap<>();
            map.put("token",token);
            return Result.success(map);
        }catch (Exception e){
            logger.error("loginAdmin",e.getMessage());
            return Result.failure(Status.SysError);
        }

    }




}
