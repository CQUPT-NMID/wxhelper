package cn.edu.cqupt.nmid.wxhelper.wxhelper.exception;

import cn.edu.cqupt.nmid.wxhelper.wxhelper.enums.Status;

/**
 * @author MaYunHao
 * @version 1.0
 * @description
 * @date 2020/2/5 1:23
 */

public class MyException extends Exception {

    private Status status;

    public MyException(Status status){
        setStatus(status);
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
