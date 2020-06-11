package cn.edu.cqupt.nmid.wxhelper.wxhelper.service;

import cn.edu.cqupt.nmid.wxhelper.wxhelper.exception.MyException;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.BaseItem;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.Era;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.Item;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.Type;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.query.ItemQuery;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface ItemService {

    List<BaseItem> searchByName(String itemName);

    List<BaseItem> getItemsByEra(Integer id);

    List<BaseItem> getItemsByType(Integer id);

    List<BaseItem> getItemsByCollection();

    List<BaseItem> getItemsByClick();

    List<BaseItem> getItemsByComment();

    Integer getTypeId(String typename);

    Integer getEraId(String era);

    Integer saveItemAndFile(String itemname, String intro, String typename, String era, MultipartFile video, MultipartFile[] photo) throws Exception;

    Integer saveItem(String itemname, String intro, String typename, String era, String video, List<String> photo);

    List<BaseItem> getAll();

    Integer saveEra(Era era);

    void saveHistroy(Integer item_id, String user_id, Date date);

    Item getItemById(Integer id);

    List<String> updatePhotoWithFile(Integer itemid, MultipartFile[] photos) throws IOException;

    void updatePhotoWithUrls(Integer itemid, List<String> urls);

    void deleteItem(Integer itemid);

    void updateVedioWithFile(Integer itemid, MultipartFile video) throws IOException;

    void updateVedioWithUrl(Integer itemid, String url) throws IOException;

    void updateItem(Item item);

    void deleteEra(Integer id);

    Integer updateEra(Era era);

    List<Era> getAllEra();

    public List<String> uploadFile(MultipartFile[] files) throws IOException;

    Integer saveTpye(Type type);

    void deleteType(Type type) throws MyException;

    Integer updateType(Type type);

    List<Type> getAllType();

    /**
     *  通过查询辅助类 多条件查询
     * @param itemQuery
     * @return
     */
    List<BaseItem> index(ItemQuery itemQuery);

    /**
     * 通过用户id 获取用户历史纪录
     * @param id
     * @return
     */
    List<BaseItem> getHistoryByUserId(String id,String sign);

    /**
     * 管理端的复杂查询
     * @param itemQuery
     * @return
     */
    List<Item> indexAdmin(ItemQuery itemQuery);

    /**
     * 删除展品图片
     * @param id
     */
    int deletePhoto(Integer id);
}
