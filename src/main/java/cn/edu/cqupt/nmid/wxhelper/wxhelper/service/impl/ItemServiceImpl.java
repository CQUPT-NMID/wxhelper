package cn.edu.cqupt.nmid.wxhelper.wxhelper.service.impl;


import cn.edu.cqupt.nmid.wxhelper.wxhelper.exception.MyException;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.dao.ItemDao;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.enums.Status;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.BaseItem;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.Era;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.Item;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.Type;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.po.query.ItemQuery;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.service.ItemService;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.utils.CosUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ItemServiceImpl implements ItemService {

    private Logger logger = LoggerFactory.getLogger(ItemServiceImpl.class);

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private CosUtil cosUtil;

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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer saveItemAndFile(String itemname,
                         String intro,
                         String typename,
                         String era,
                         MultipartFile video,
                         MultipartFile[] photo) throws Exception {
        Integer eraId = itemDao.getEraId(era);
        if(eraId == null) {
            itemDao.saveEra(new Era(era,null,null));
        }

        //分别通过名称将对应id获取出来
        Integer typeId = itemDao.getTypeId(typename);
        if (typeId == null){
            Type type = new Type();
            type.setTypename(typename);
            typeId = itemDao.saveType(type);
        }

        Item item = new Item();
        item.setType(typename);
        item.setEra(era);
        item.setIntro(intro);
        item.setName(itemname);
        itemDao.saveItem(item);
        updatePhotoWithFile(item.getId(),photo);

        //保存视频
        String videourl = saveFile(video);
        if (videourl!=null){
            itemDao.updateVideo(item.getId(),videourl);
        }
        return item.getId();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer saveItem(String itemname, String intro, String typename, String era, String video, List<String> photos) {

        Integer eraId = itemDao.getEraId(era);

        if(eraId == null) {
            itemDao.saveEra(new Era(era,null,null));
        }

        //分别通过名称将对应id获取出来
        Integer typeId = itemDao.getTypeId(typename);
        if (typeId == null){
            Type type = new Type();
            type.setTypename(typename);
            typeId = itemDao.saveType(type);
        }

        Item item = new Item();
        item.setType(typename);
        item.setEra(era);
        item.setIntro(intro);
        item.setName(itemname);

        itemDao.saveItem(item);

        /**
         * 图片保存到数据库
         */
        if (photos != null) {
            int length = photos.size();
            for (int i = 0; i <length ; i++) {
                itemDao.updatePhoto(item.getId(),photos.get(i));
            }
        }

        if (video!=null){
            itemDao.updateVideo(item.getId(),video);
        }

        return item.getId();
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
//        String name = file.getContentType();
//        String[] split = name.split("/");
//        String filetype = split[split.length - 1];
        String filetype = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
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
    public List<String>  updatePhotoWithFile(Integer itemid, MultipartFile[] photos) throws IOException{
        if (photos == null){
            return null;
        }
        ArrayList<String> urls = new ArrayList<>();
        int length = photos.length;
        for (int i = 0; i <length ; i++) {
            String geturl = saveFile(photos[i]);
            itemDao.updatePhoto(itemid,geturl);
            urls.add(geturl);
        }
        return urls;
    }

    @Override
    public void deleteItem(Integer itemid) {
        itemDao.deleteItemById(itemid);
    }

    /**
     * 更新视频，后面可能会增加删除文件的方法
     * @param itemid
     * @param video
     * @throws IOException
     */
    @Override
    public void updateVedioWithFile(Integer itemid, MultipartFile video) throws IOException {
        String geturl = saveFile(video);
        itemDao.updateVideo(itemid,geturl);
    }

    @Override
    public void deleteEra(Integer id) {
        itemDao.deleteEraById(id);
    }

    @Override
    public void updateItem(Item item) {
        itemDao.updateItem(item);
    }

    @Override
    public Integer updateEra(Era era) {
        if(era.getId() == null){
            return itemDao.saveEra(era);
        }
        itemDao.updateEra(era);
        return era.getId();
    }

    @Override
    public List<Era> getAllEra() {
       return itemDao.getAllEra();
    }


    /**
     * 仅仅是上传文件
     * @param files  文件
     * @return
     * @throws IOException
     */
    @Override
    public List<String> uploadFile(MultipartFile[] files) throws IOException {
        List<String> urls = new ArrayList<>();
        if (files == null || files.length == 0){
            return null;
        }
        int length = files.length;
        for (int i = 0; i <length ; i++) {
            //上传到对象存储
            String geturl = cosUtil.upload(files[i]);
            urls.add(geturl);
        }
        return urls;
    }

    @Override
    public Integer saveTpye(Type type) {
        return itemDao.saveType(type);
    }

    @Override
    public void deleteType(Type type) throws MyException {
        if (type.getId()!=null){
            itemDao.deleteTypeById(type.getId());
        }else if (type.getTypename()!=null){
            itemDao.deleteTypeByName(type.getTypename());
        }else {
            throw new MyException(Status.PARAM_IS_INVALID);
        }
    }

    @Override
    public Integer updateType(Type type) {
        if (type.getId() == null){
            itemDao.saveType(type);
        }
        itemDao.updateType(type);
        return type.getId();
    }

    @Override
    public List<Type> getAllType() {
        return itemDao.getAllType();
    }


    @Override
    public List<BaseItem> index(ItemQuery itemQuery) {
        if (itemQuery != null){
            return itemDao.index(itemQuery);
        }else{
            return itemDao.getAll();
        }

    }

    @Override
    public List<BaseItem> getHistoryByUserId(String id,String sign) {
        return itemDao.getHistoryByUserId(id,sign);
    }

    @Override
    public List<Item> indexAdmin(ItemQuery itemQuery) {
        return itemDao.indexAdmin(itemQuery);
    }

    @Override
    public int deletePhoto(Integer id) {
        return itemDao.deletePhoto(id);
    }


    @Override
    public void updatePhotoWithUrls(Integer itemid, List<String> urls) {
        int size = urls.size();
        for (int i =0 ;i<size;i++) {
            itemDao.updatePhoto(itemid,urls.get(i));
        }
    }

    @Override
    public void updateVedioWithUrl(Integer itemid, String url) throws IOException {
        itemDao.updateVideo(itemid,url);
    }
}
