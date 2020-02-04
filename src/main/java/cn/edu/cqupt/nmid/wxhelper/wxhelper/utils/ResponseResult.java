package cn.edu.cqupt.nmid.wxhelper.wxhelper.utils;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.TYPE;

/**
 * @author MaYunHao
 * @version 1.0
 * @description
 * @date 2020/2/1 13:38
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({TYPE, ElementType.METHOD})
@Documented
public @interface ResponseResult {

}
