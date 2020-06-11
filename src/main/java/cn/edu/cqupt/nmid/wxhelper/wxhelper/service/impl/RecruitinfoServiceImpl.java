package cn.edu.cqupt.nmid.wxhelper.wxhelper.service.impl;

import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.Recruitinfo;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.dao.RecruitinfoDao;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.service.RecruitinfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * (Recruitinfo)表服务实现类
 *
 * @author makejava
 * @since 2020-03-11 16:12:44
 */
@Service("recruitinfoService")
public class RecruitinfoServiceImpl implements RecruitinfoService {
    @Resource
    private RecruitinfoDao recruitinfoDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Recruitinfo queryById(Integer id) {
        return this.recruitinfoDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Recruitinfo> queryAllByLimit(int offset, int limit) {
        return this.recruitinfoDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param recruitinfo 实例对象
     * @return 实例对象
     */
    @Override
    public Recruitinfo insert(Recruitinfo recruitinfo) {
        if (recruitinfo.getPublishTime() == null){
            recruitinfo.setPublishTime(new Date());
        }
        this.recruitinfoDao.insert(recruitinfo);
        return recruitinfo;
    }

    /**
     * 修改数据
     *
     * @param recruitinfo 实例对象
     * @return 实例对象
     */
    @Override
    public Recruitinfo update(Recruitinfo recruitinfo) {
        this.recruitinfoDao.update(recruitinfo);
        return this.queryById(recruitinfo.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.recruitinfoDao.deleteById(id) > 0;
    }
}