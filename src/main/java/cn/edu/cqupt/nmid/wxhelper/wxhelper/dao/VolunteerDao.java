package cn.edu.cqupt.nmid.wxhelper.wxhelper.dao;

import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.Volunteer;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Volunteer)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-11 16:44:18
 */
public interface VolunteerDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Volunteer queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Volunteer> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param volunteer 实例对象
     * @return 对象列表
     */
    List<Volunteer> queryAll(Volunteer volunteer);

    /**
     * 新增数据
     *
     * @param volunteer 实例对象
     * @return 影响行数
     */
    int insert(Volunteer volunteer);

    /**
     * 修改数据
     *
     * @param volunteer 实例对象
     * @return 影响行数
     */
    int update(Volunteer volunteer);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    Integer publishVolunteerInfo(Volunteer volunteer);

    List<Volunteer> queryByUserid(String userId);
}