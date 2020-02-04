package cn.edu.cqupt.nmid.wxhelper.wxhelper.controller.admin;

import cn.edu.cqupt.nmid.wxhelper.wxhelper.dao.ItemDao;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.enums.Status;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.Era;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.service.ItemService;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.utils.Result;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

@Controller
@RequestMapping("/admin")
public class AdminController {

    private Logger logger = LoggerFactory.getLogger(AdminController.class);
    @Autowired
    private ItemService itemService;

    @ApiOperation("添加展品")
    @PostMapping("/itemInput")
    @ResponseBody
    public Result itemInput(@RequestParam @ApiParam("展品名称") String itemname,

                            @RequestParam @ApiParam("展品介绍") String intro,
                            @RequestParam @ApiParam("展品类型名") String typename ,
                            @RequestParam @ApiParam("展品年代") String era,
                            @RequestParam(required = false) @ApiParam("展品图片") MultipartFile[] photo,
                            @ApiParam("展品视频") MultipartFile video  ){
        try {
            itemService.saveItem(itemname,intro,typename,era,video,photo);
            return Result.success();
        }catch (Exception e){
            logger.error(e.getMessage());
            return Result.failure(Status.FAISE);
        }
    }



    @ApiOperation("添加年代")
    @PostMapping("/eraInput")
    public Result eraInput(@RequestParam @ApiParam("年代名称") String eraname,
                           @ApiParam("年代开始时间") String begintime ,
                           @ApiParam("年代结束时间") String endtime){
        Era era = new Era();
        era.setBegintime(begintime);
        era.setEndtime(endtime);
        era.setEraname(eraname);
        itemService.saveEra(era);
        return Result.success();
    }

    /**
     * 为展品添加图片
     * @param itemid 展品id
     * @param photos 图片
     * @return
     */
    @PostMapping("/updatePhoto")
    @ResponseBody
    public Result updatePhoto(@RequestParam @ApiParam("展品id") Integer itemid,
                              @RequestParam @ApiParam("图片") MultipartFile[] photos){

        try{
            itemService.updatePhoto(itemid,photos);
            return Result.success();
        }catch (Exception e){
            logger.error("addPhoto have error",e.getMessage());
            return Result.failure(Status.FAISE);
        }

    }


}
