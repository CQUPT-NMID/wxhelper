package cn.edu.cqupt.nmid.wxhelper.wxhelper.controller;

import cn.edu.cqupt.nmid.wxhelper.wxhelper.dao.CommentDao;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.enums.Status;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.Comment;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.User;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.service.CommentService;
import com.alibaba.druid.support.spring.stat.annotation.Stat;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * @author MaYunHao
 * @version 1.0
 * @description 评论相关
 * @date 2019/12/12 20:52
 */

@RestController
@RequestMapping("/comment")
public class CommentController {

    private Logger logger = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    private CommentService commentService;

    @ApiOperation("发评论")
    @PostMapping("/publishComment")
    public String publishComment( HttpSession session,
                                 @RequestParam @ApiParam(value = "展品id ",required = true) Integer itemid,
                                 @RequestParam @ApiParam(value = "评论内容",required = true) String content){
        Status status = null;
        User user = (User) session.getAttribute("user");
        try{
            commentService.publishComment(itemid,content,user.getId());
            status = Status.SUCCESS;
        }catch (Exception e){
            logger.error(e.getMessage());
            status = Status.FAISE;
        }
        JSONObject returnData = new JSONObject();
        returnData.put("status",status.getCode());
        returnData.put("msg",status.getMessage());
        return returnData.toJSONString();
    }

    @ApiOperation("获取一个展品的所有评论")
    @GetMapping("/getCommentByItemId")
    public String getCommentByItemId( HttpSession session,
                                     @RequestParam @ApiParam(value = "展品id",required = true) Integer itemid){
        JSONObject returnData = new JSONObject();
        Status status = null;
        try{
            List<Comment> comments = commentService.getCommentByItemId(itemid);
            returnData.put("comments",comments);
            status = Status.SUCCESS;
        }catch (Exception e){
            logger.error(e.getMessage());
            status = Status.FAISE;
        }
        returnData.put("status",status.getCode());
        returnData.put("msg",status.getMessage());
        return returnData.toJSONString();
    }

    @ApiOperation("获取一个用户所有的评论")
    @GetMapping("/getCommentByUserId")
    public String getCommentByUserId( HttpSession session){
        JSONObject returnData = new JSONObject();
        Status status = null;
        try{
            User user = (User) session.getAttribute("user");
            List<Comment> comments = commentService.getCommentByUserId(user.getId());
            returnData.put("comments",comments);
            status = Status.SUCCESS;
        }catch (Exception e){
            logger.error(e.getMessage());
            status = Status.FAISE;
        }
        returnData.put("status",status.getCode());
        returnData.put("msg",status.getMessage());
        return returnData.toJSONString();
    }

    @ApiOperation("通过评论id删除评论")
    @GetMapping("/deleteCommentByCommentId")
    public String deleteCommentByCommentId( HttpSession session,@RequestParam Integer commentid){
        JSONObject returnData = new JSONObject();
        Status status = null;
        try{
            commentService.deleteCommentByCommentId(commentid);
            status = Status.SUCCESS;
        }catch (Exception e){
            logger.error(e.getMessage());
            status = Status.FAISE;
        }
        returnData.put("status",status.getCode());
        returnData.put("msg",status.getMessage());
        return returnData.toJSONString();
    }
}
