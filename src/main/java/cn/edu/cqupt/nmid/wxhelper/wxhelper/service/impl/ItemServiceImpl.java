package cn.edu.cqupt.nmid.wxhelper.wxhelper.service.impl;


import cn.edu.cqupt.nmid.wxhelper.wxhelper.dao.ItemDao;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.BaseItem;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.Era;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.Item;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ItemServiceImpl implements ItemService {

    private Logger logger = LoggerFactory.getLogger(ItemServiceImpl.class);

    @Autowired
    private ItemDao itemDao;

    /**
     * 存储 视频或文件的默认地址
     */
    @Value("${myproperties.file-save-location}")
    private String saveLocation;

    /**
     * 访问 视频或文件的默认地址
     */
    @Value("${myproperties.file-visit-url}")
    private String baseUrl ;

    @Override
    public List<BaseItem> searchByName(String itemName) {
        return itemDao.searchByName(itemName);

    }

    @Override
    public List<BaseItem> getItemsByEra(Integer id) {
        return itemDao.getItemsByEra(id);
    }

    @Override
    public List<BaseItem> getItemsByType(Integer id) {
        return itemDao.getItemsByType(id);
    }

    @Override
    public List<BaseItem> getItemsByCollection() {
        return itemDao.getItemsByCollection();
    }

    @Override
    public List<BaseItem> getItemsByClick() {
        return itemDao.getItemsByClick();
    }

    @Override
    public List<BaseItem> getItemsByComment() {
        return itemDao.getItemsByComment();
    }

    @Override
    public Integer getTypeId(String typename) {
        return itemDao.getTypeId(typename);
    }

    @Override
    public Integer getEraId(String era) {
        return itemDao.getEraId(era);
    }

    @Transactional
    @Override
    public void saveItem(String itemname,
                         String intro,
                         String typename,
                         String era,
                         MultipartFile video,
                         MultipartFile[] photo) throws Exception {
        Integer eraId = itemDao.getEraId(era);
        if(eraId == null) {
            throw new Exception("朝代存在");

        }

        //分别通过名称将对应id获取出来
        Integer typeId = itemDao.getTypeId(typename);
        if (typeId == null){
           typeId = itemDao.saveType(typename);
        }



        Item item = new Item();
        item.setType(typename);
        item.setEra(era);
        item.setIntro(intro);
        item.setName(itemname);
        itemDao.saveItem(item);
        updatePhoto(item.getId(),photo);

        //保存视频
        String videourl = saveFile(video);
        if (videourl!=null){
            itemDao.updateVideo(item.getId(),videourl);
        }
    }

    /**
     * 保存文件
     * @param file 文件
     * @return   访问的地址
     */
    private String saveFile(MultipartFile file) throws IOException{
        if (file == null) {
            return null;
        }
        //获取文件类型
        String name = file.getContentType();
        String[] split = name.split("/");
        String filetype = split[split.length - 1];
        UUID uuid = UUID.randomUUID();
        String fileurl = saveLocation +uuid+"."+filetype;
        String getUrl = baseUrl+uuid+"."+filetype;
        File file1 = new File(fileurl);
        file1.createNewFile();
        file.transferTo(file1);
        return getUrl;
    }

    @Override
    public List<BaseItem> getAll() {
        return itemDao.getAll();
    }

    @Override
    public Integer saveEra(Era era) {
        return itemDao.saveEra(era);
    }

    @Override
    public void saveHistroy(Integer item_id, String user_id, Date date) {
        itemDao.saveHistory(item_id,user_id,date);
    }

    @Override
    public Item getItemById(Integer id) {
        return itemDao.getItemById(id);
    }

    @Override
    public void updatePhoto(Integer itemid, MultipartFile[] photos) throws IOException{
        if (photos == null){
            return;
        }
        int length = photos.length;
        for (int i = 0; i <length ; i++) {
            String geturl = saveFile(photos[i]);
            itemDao.updatePhoto(itemid,geturl);
        }
    }
}
