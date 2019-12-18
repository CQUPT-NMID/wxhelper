package cn.edu.cqupt.nmid.wxhelper.wxhelper.controller;

import cn.edu.cqupt.nmid.wxhelper.wxhelper.dao.CommentDao;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.Comment;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.User;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.service.CommentService;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author MaYunHao
 * @version 1.0
 * @description
 * @date 2019/12/12 20:52
 */

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @ApiOperation("发评论")
    @PostMapping("/publishComment")
    public String publishComment( HttpSession session,
                                 @RequestParam @ApiParam(value = "展品id ",required = true) Integer itemid,
                                 @RequestParam @ApiParam(value = "评论内容",required = true) String content){
        User user = (User) session.getAttribute("user");
        commentService.publishComment(itemid,content,user.getId());
        return "";
    }

    @ApiOperation("获取一个展品的所有评论")
    @GetMapping("/getCommentByItemId")
    public String getCommentByItemId( HttpSession session,
                                     @RequestParam @ApiParam(value = "展品id",required = true) Integer itemid){
        List<Comment> comments = commentService.getCommentByItemId(itemid);
        JSONObject returnData = new JSONObject();
        returnData.put("comments",comments);
        return returnData.toJSONString();
    }

    @ApiOperation("获取一个用户所有的评论")
    @GetMapping("/getCommentByUserId")
    public String getCommentByUserId( HttpSession session){
        User user = (User) session.getAttribute("user");
        List<Comment> comments = commentService.getCommentByUserId(user.getId());
        JSONObject returnData = new JSONObject();
        returnData.put("comments",comments);
        return returnData.toJSONString();
    }

    @ApiOperation("通过评论id删除评论")
    @GetMapping("/deleteCommentByCommentId")
    public String deleteCommentByCommentId( HttpSession session,@RequestParam Integer commentid){
        commentService.deleteCommentByCommentId(commentid);
        return "";
    }
}
