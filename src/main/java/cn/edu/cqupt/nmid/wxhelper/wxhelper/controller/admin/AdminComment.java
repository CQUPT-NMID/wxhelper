package cn.edu.cqupt.nmid.wxhelper.wxhelper.controller.admin;

import cn.edu.cqupt.nmid.wxhelper.wxhelper.controller.CommentController;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.enums.Status;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.vo.CommentVo;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.service.CommentService;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.utils.Result;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.utils.page.PageRequest;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.utils.page.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author MaYunHao
 * @version 1.0
 * @description
 * @date 2020/2/5 1:37
 */
@Api(value = "/api/admin", tags = "后台管理:评论")
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


    /**
     * 获取展品所有的评论
     * @param pageRequest 分页信息
     * @return
     */
    @ApiOperation("获取所有评论")
    @GetMapping("/list")
    public Result listAllComments(PageRequest pageRequest){
        PageHelper.startPage(pageRequest.getPageNum(),pageRequest.getPageSize());
        List<CommentVo> commentVoList = commentService.list();
        PageResult result = new PageResult(new PageInfo(commentVoList));
        return Result.success(result);
    }





}

