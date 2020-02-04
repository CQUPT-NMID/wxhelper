package cn.edu.cqupt.nmid.wxhelper.wxhelper.service;

import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.BaseItem;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.Era;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.Item;
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

    void saveItem(String itemname, String intro, String typename, String era, MultipartFile video, MultipartFile[] photo) throws Exception;

    List<BaseItem> getAll();

    Integer saveEra(Era era);

    void saveHistroy(Integer item_id, String user_id, Date date);

    Item getItemById(Integer id);

    void updatePhoto(Integer itemid, MultipartFile[] photos) throws IOException;
}
