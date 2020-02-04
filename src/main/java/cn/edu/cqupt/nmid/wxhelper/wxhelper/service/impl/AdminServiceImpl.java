package cn.edu.cqupt.nmid.wxhelper.wxhelper.service.impl;

import cn.edu.cqupt.nmid.wxhelper.wxhelper.dao.AdminDao;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.Admin;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author MaYunHao
 * @version 1.0
 * @description
 * @date 2020/2/4 19:44
 */

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    @Override
    public Admin login(Admin admin) {
        return adminDao.login(admin);
    }
}
