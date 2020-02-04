package cn.edu.cqupt.nmid.wxhelper.wxhelper.enums;

/**
 * @author MaYunHao
 * @version 1.0
 * @description 返回状态码
 * @date 2020/2/1
 */
public enum Status {
    // 请求成功
    SUCCESS(200,"成功"),
    //请求失败
    FAISE(400,"失败"),
    //没有权限
    Unauthorized(401,"无权限"),
    //禁止访问
    Forbidden(403,"禁止"),
    //没有找到资源
    NotFound(404,"没找到"),
    //参数错误
    PARAM_IS_INVALID(1001,"参数无效"),
    //用户未登录
    USER_NOT_LOGIN_IN(2001,"用户未登录");

    private String message;
    private  int code;

    public String getMessage() {
        return message;
    }


    Status(int code , String message) {
        this.code = code;
        this.message = message;
    }
    public int getCode() {
        return code;
    }
}
