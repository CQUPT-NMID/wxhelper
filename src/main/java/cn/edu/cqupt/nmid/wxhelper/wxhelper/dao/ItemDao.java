package cn.edu.cqupt.nmid.wxhelper.wxhelper.dao;

import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.BaseItem;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.Era;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.Item;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.Type;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@Component
@Mapper
public interface ItemDao {

    /**
     * 通过 展品的名称搜索展品
     * @param itemName
     * @return
     */
    List<BaseItem> searchByName(String itemName);

    /**
     * 获取展品总数
     * @return
     */
    int getCount();

    /**
     * 通过展品年代获取展品
     * @param id
     * @return
     */
    List<BaseItem> getItemsByEra(Integer id);

    /**
     * 通过展品类型获取展品
     * @param id
     * @return
     */
    List<BaseItem> getItemsByType(Integer id);

    /**
     * 通过收藏数量获取展品
     * @return
     */
    List<BaseItem> getItemsByCollection();

    /**
     * 通过点击数获取展品
     * @return
     */
    List<BaseItem> getItemsByClick();

    /**
     * 通过评论数获取展品
     * @return
     */
    List<BaseItem> getItemsByComment();

    /**
     * 通过类型名称获取类型id
     * @param typename
     * @return
     */
    Integer getTypeId(String typename);


    Integer saveType(String typename);

    /**
     * 通过年代名称获取年代id
     * @param era
     * @return
     */
    Integer getEraId(String era);

    Integer saveEra(Era era);

    Integer saveItem(String itemname, String intro, Integer typeId, Integer eraId);

    void updatePhoto(Integer itemid, String photo);

    List<BaseItem> getAll();

    void updateVideo(Integer itemid, String video);

    void saveHistory(Integer item_id, String user_id, Date date);

    Item getItemById(Integer id);

    Integer saveItem(Item item);

    void deleteItemById(Integer itemid);

    void updateItem(Item item);

    void deleteEraById(Integer id);

    void updateEra(Era era);

    List<Era> getAllEra();

    void deleteTypeById(Integer id);

    void deleteTypeByName(String name);

    void updateType(Type type);

    List<Type> getAllType();
}
