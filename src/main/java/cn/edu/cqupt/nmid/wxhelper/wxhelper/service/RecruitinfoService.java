package cn.edu.cqupt.nmid.wxhelper.wxhelper.service;

import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.Recruitinfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (Recruitinfo)表服务接口
 *
 * @author makejava
 * @since 2020-03-11 16:12:44
 */

public interface RecruitinfoService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Recruitinfo queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Recruitinfo> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param recruitinfo 实例对象
     * @return 实例对象
     */
    Recruitinfo insert(Recruitinfo recruitinfo);

    /**
     * 修改数据
     *
     * @param recruitinfo 实例对象
     * @return 实例对象
     */
    Recruitinfo update(Recruitinfo recruitinfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}