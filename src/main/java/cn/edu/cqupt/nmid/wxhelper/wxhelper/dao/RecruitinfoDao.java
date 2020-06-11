package cn.edu.cqupt.nmid.wxhelper.wxhelper.dao;

import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.Recruitinfo;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Recruitinfo)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-11 16:12:44
 */
public interface RecruitinfoDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Recruitinfo queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Recruitinfo> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param recruitinfo 实例对象
     * @return 对象列表
     */
    List<Recruitinfo> queryAll(Recruitinfo recruitinfo);

    /**
     * 新增数据
     *
     * @param recruitinfo 实例对象
     * @return 影响行数
     */
    int insert(Recruitinfo recruitinfo);

    /**
     * 修改数据
     *
     * @param recruitinfo 实例对象
     * @return 影响行数
     */
    int update(Recruitinfo recruitinfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}