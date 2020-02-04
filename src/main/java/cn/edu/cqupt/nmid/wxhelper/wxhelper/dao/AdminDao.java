package cn.edu.cqupt.nmid.wxhelper.wxhelper.dao;

import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.Admin;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author MaYunHao
 * @version 1.0
 * @description
 * @date 2020/2/4 19:43
 */

@Repository
public interface AdminDao {

    @Select("select * from admin where username = #{username} and password = #{password}")
    public Admin login(Admin admin);

}
