package cn.edu.cqupt.nmid.wxhelper.wxhelper.exception;

import cn.edu.cqupt.nmid.wxhelper.wxhelper.enums.Status;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.utils.ResponseResult;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.utils.Result;
import java.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author MaYunHao
 * @version 1.0
 * @description 统一异常处理
 * @date 2020/4/18
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private Map<String,Object> error(int code,String reason){
        HashMap<String, Object> map = new HashMap<>();
        map.put("code",code);
        map.put("message",reason);
        return map;
    }

    /**-------- 通用异常处理方法 --------**/
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Map<String,Object> error(Exception e) {
        e.printStackTrace();
        logger.error("服务器发生异常 {}",e.getMessage());
        return error(500,e.getMessage());
    }

    /**-------- SQL异常处理方法 --------**/
    @ExceptionHandler(SQLException.class)
    @ResponseBody
    public Map<String,Object> sqlError(SQLException e) {
        e.printStackTrace();
        logger.error("服务器发生sql异常 {}",e.getMessage());
        return error(500,"服务器异常");
    }


    /**-------- 参数异常处理方法 --------**/
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Map<String,Object> paramError(MethodArgumentNotValidException e) {
        e.printStackTrace();
        logger.error("参数异常 {}",e.getMessage());
        return error(500,e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }


    /**-------- 权限异常处理方法 --------**/
    @ExceptionHandler(SignatureException.class)
    @ResponseBody
    public Result signatureException(SignatureException e){
        e.printStackTrace();
        logger.error("权限异常 {}",e.getMessage());
        return Result.failure(Status.EXCEED);
    }




}