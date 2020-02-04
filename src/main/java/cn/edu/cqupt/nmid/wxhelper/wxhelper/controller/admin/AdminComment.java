package cn.edu.cqupt.nmid.wxhelper.wxhelper.controller.admin;

import cn.edu.cqupt.nmid.wxhelper.wxhelper.controller.CommentController;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.enums.Status;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.service.CommentService;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author MaYunHao
 * @version 1.0
 * @description
 * @date 2020/2/5 1:37
 */
@Api(value = "/api/admin", description = "后台管理")
@RestController
@RequestMapping("/api/admin")
public class AdminComment {

    private Logger logger = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    private CommentService commentService;
    /**
     * 通过评论id删除评论
     * @param commentid
     * @return
     */
    @ApiOperation("通过评论id删除评论")
    @GetMapping("/deleteCommentByCommentId")
    public Result deleteCommentByCommentId(@RequestParam Integer commentid){
        try{
            commentService.deleteCommentByCommentId(commentid);
            return Result.success();
        }catch (Exception e){
            logger.error(e.getMessage());
            return Result.failure(Status.SysError);
        }
    }

}

