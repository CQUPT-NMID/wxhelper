package cn.edu.cqupt.nmid.wxhelper.wxhelper.enums;

public enum Status {
    SUCCESS(200,"成功"),
    FAISE(400,"失败"),
    Unauthorized(401,"无权限"),
    Forbidden(403,"禁止"),
    NotFound(404,"没找到");

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
