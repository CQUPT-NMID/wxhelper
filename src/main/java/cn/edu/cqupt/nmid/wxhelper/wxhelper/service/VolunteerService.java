package cn.edu.cqupt.nmid.wxhelper.wxhelper.service;

import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.Volunteer;
import java.util.List;

/**
 * (Volunteer)表服务接口
 *
 * @author makejava
 * @since 2020-03-11 16:44:19
 */
public interface VolunteerService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Volunteer queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Volunteer> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param volunteer 实例对象
     * @return 实例对象
     */
    Volunteer insert(Volunteer volunteer);

    /**
     * 修改数据
     *
     * @param volunteer 实例对象
     * @return 实例对象
     */
    Volunteer update(Volunteer volunteer);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 提交信息
     * @param volunteer
     * @return
     */
    Integer publishVolunteerInfo(Volunteer volunteer);

    /**
     * 通过userid 获取用户填写的志愿信息
     * @param userId
     * @return
     */
    List<Volunteer> queryByUserId(String userId);


}