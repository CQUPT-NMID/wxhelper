package cn.edu.cqupt.nmid.wxhelper.wxhelper.utils;

import cn.edu.cqupt.nmid.wxhelper.wxhelper.enums.Status;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author MaYunHao
 * @version 1.0
 * @description 返回数据体
 * @date 2020/2/1 14:04
 */

public class Result implements Serializable {
    /**
     * 返回码
     */
    private Integer code;
    /**
     * 返回消息
     */
    private String message;
    /**
     * 返回数据
     */
    private  Map<String, Object> data;
    // -------------------------------getter---------------------------------


    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }



    private void setResultCode(Status status){
        this.code = status.getCode();
        this.message = status.getMessage();
    }

    private void setData(Map<String, Object> data) {
        this.data = data;
    }

    public static Result success(){
        Result result = new Result();
        result.setResultCode(Status.SUCCESS);

        return result;
    }

    public static Result success(Map<String, Object> data){
        Result result = new Result();
        result.setResultCode(Status.SUCCESS);
        result.setData(data);
        return result;
    }

    public static Result failure(Status status){
        Result result = new Result();
        result.setResultCode(status);
        return result;
    }

    public static Result failure(Status status,Map<String, Object> data){
        Result result = new Result();
        result.setResultCode(status);
        result.setData(data);
        return result;
    }

}
