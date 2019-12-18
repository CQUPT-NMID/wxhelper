package cn.edu.cqupt.nmid.wxhelper.wxhelper.controller.admin;

import cn.edu.cqupt.nmid.wxhelper.wxhelper.dao.ItemDao;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.enums.Status;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.Era;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.service.ItemService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
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
 *
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
    public String itemInput(@RequestParam @ApiParam("展品名称") String itemname,

                            @RequestParam @ApiParam("展品介绍") String intro,
                            @RequestParam @ApiParam("展品类型名") String typename ,
                            @RequestParam @ApiParam("展品年代") String era,
                            @RequestParam @ApiParam("展品图片") MultipartFile[] photo,
                            @ApiParam("展品视频") MultipartFile video  ){
        JSONObject returnData = new JSONObject();
        String message = null;
        Status status = null;
        try {
            itemService.saveItem(itemname,intro,typename,era,video,photo);
            status = Status.SUCCESS;
        }catch (Exception e){
             message = e.getMessage();
             status = Status.FAISE;
            logger.error(e.getMessage());
        }
        returnData.put("status",status.getCode());
        returnData.put("message",message == null ?status.getMessage():message);
        return returnData.toJSONString();
    }

    @GetMapping("/index")
    public String index(){
        return "admin/index";
    }

    @PostMapping("/eraInput")
    public String eraInput(@RequestParam Era era){
        itemService.saveEra(era);
        return "admin/index";
    }

}
