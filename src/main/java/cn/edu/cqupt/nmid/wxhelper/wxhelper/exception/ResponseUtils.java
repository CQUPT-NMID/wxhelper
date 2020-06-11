package cn.edu.cqupt.nmid.wxhelper.wxhelper.exception;


import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletResponse;
import java.io.PrintWriter;

/**
 * @author MaYunHao
 * @version 1.0
 * @description
 * @date 2020/4/16 11:48
 */

public class ResponseUtils {

    private static final Logger logger = LoggerFactory.getLogger(ResponseUtils.class);

    /**
     * @Title: out
     * @Description:  response输出JSON数据
     * @param response : 响应请求
     * @param reuslt: 返回结果
     * @return void
     **/
    public static void out(ServletResponse response, Object reuslt){
        PrintWriter out = null;
        try {
            response.setContentType("application/json;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            out = response.getWriter();
            out.println(JSONObject.toJSONString(reuslt));
        } catch (Exception e) {
            logger.error("输出JSON报错!"+e);
        }finally{
            if(null != out){
                out.flush();
                out.close();
            }
        }
    }
}
