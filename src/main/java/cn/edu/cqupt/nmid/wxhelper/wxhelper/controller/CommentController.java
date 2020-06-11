package cn.edu.cqupt.nmid.wxhelper.wxhelper.controller;

import cn.edu.cqupt.nmid.wxhelper.wxhelper.dao.CommentDao;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.enums.Status;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.Comment;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.User;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.service.CommentService;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.utils.Result;
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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.text.html.ObjectView;
import java.lang.annotation.Retention;
import java.util.HashMap;
import java.util.List;


/**
 * @author MaYunHao
 * @version 1.0
 * @description 评论相关
 * @date 2019/12/12 20:52
 */

@Api(value = "/api/comment",description = "评论相关")
@RestController
@RequestMapping("/api/comment")
public class CommentController {

    private Logger logger = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    private CommentService commentService;

    /**
     * 发评论
     * @param itemid  展品id
     * @param content 评论内容
     * @return
     */
    @ApiOperation("发评论")
    @PostMapping("/publishComment")
    public Result publishComment(@RequestParam @ApiParam(value = "展品id ",required = true) Integer itemid,
                                 @RequestParam @ApiParam(value = "评论内容",required = true) String content){
        try{
            ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = sra.getRequest();
            String userid = (String) request.getAttribute("userid");
            commentService.publishComment(itemid,content,userid);
            return Result.success();
        }catch (Exception e){
            logger.error(e.getMessage());
            return Result.failure(Status.SysError);
        }
    }

    /**
     * 拉去一个展品所有的评论
     * @param itemid  展品id
     * @return
     */
    @ApiOperation("获取一个展品的所有评论")
    @GetMapping("/getCommentByItemId")
    public Result getCommentByItemId(@RequestParam @ApiParam(value = "展品id",required = true) Integer itemid){
        try{
            List<Comment> comments = commentService.getCommentByItemId(itemid);
            HashMap<String, Object> map = new HashMap<>();
            map.put("comments",comments);
            return Result.success(map);
        }catch (Exception e){
            logger.error(e.getMessage());
            return Result.failure(Status.SysError);
        }
    }



    /**
     * 通过评论id删除评论
     * @param commentid
     * @return
     */
    @ApiOperation("通过评论id删除评论")
    @GetMapping("/deleteCommentByCommentId")
    public Result deleteCommentByCommentId(@RequestParam @ApiParam(value = "评论id",required = true) Integer commentid){
        try{
            commentService.deleteCommentByCommentId(commentid);
            return Result.success();
        }catch (Exception e){
            logger.error(e.getMessage());
            return Result.failure(Status.SysError);
        }
    }

    /**
     * 获取用户的评论记录
     * @return
     */
    @ApiOperation("获取一个用户所有的评论")
    @GetMapping("/getUserComments")
    public Result getUserComments(){
        try{
            String userId = getUserId();
            List<Comment> comments = commentService.getCommentByUserId(userId);
            HashMap<String, Object> map = new HashMap<>();
            map.put("comments",comments);
            return Result.success(map);
        }catch (Exception e){
            logger.error(e.getMessage());
            return Result.failure(Status.SysError);
        }
    }

    /**
     * 从request中获取userid
     * @return
     */
    private String getUserId(){
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        String userid = (String) request.getAttribute("userid");
        return userid;
    }

}
