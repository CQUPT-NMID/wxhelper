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

@Service
public class ItemServiceImpl implements ItemService {

    private Logger logger = LoggerFactory.getLogger(ItemServiceImpl.class);

    @Autowired
    private ItemDao itemDao;

    @Value("${myproperties.basephotourl}")
    private String photobase;

    private String baseUrl = "http://itrove.cn/images/";

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
        //分别通过名称将对应id获取出来
        Integer typeId = itemDao.getTypeId(typename);
        if (typeId == null){
           typeId = itemDao.saveType(typename);
        }
        Integer eraId = itemDao.getEraId(era);
        if(eraId == null) throw new Exception("朝代存在");

        //获取插入展品的id,后续插入图片
        Integer id =  itemDao.saveItem(itemname,intro,typeId,eraId);

        //先尝试一张是否可以报错
        String photourl = saveFile(id, photo[0]);
        String videourl = saveFile(id, video);
        //将图片地址插回
        if(photourl != null)
             itemDao.updatePhoto(id,photourl);
        if(photourl != null)
            itemDao.updateVideo(id,video);
    }

    /**
     * 保存文件
     * @param itemid   展品的id，关联展品的id
     * @param file 文件
     * @return   访问的地址
     */
    private String saveFile(Integer itemid ,MultipartFile file) throws IOException{
        if (file == null) return  null;
        System.out.println(photobase);
        String name = file.getContentType();
        String[] split = name.split("/");
        //拼接图片的地址
        System.out.println(file.getResource().getFilename());
        String fileurl = photobase +itemid+"."+split[split.length - 1];
        String getUrl = baseUrl+itemid+"."+split[split.length - 1];
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
}
