package cn.edu.cqupt.nmid.wxhelper.wxhelper.po;

/**
 * @author MaYunHao
 * @version 1.0
 * @description  展品详情类
 * @date 2019/12/9 18:40a
 */

public class Item extends BaseItem{

    private String era;
    private String intro;
    private String video;
    private String type;


    public String getEra() {
        return era;
    }
    public void setEra(String era) {
        this.era = era;
    }
    public String getIntro() {
        return intro;
    }
    public void setIntro(String intro) {
        this.intro = intro;
    }
    public String getVideo() {
        return video;
    }
    public void setVideo(String video) {
        this.video = video;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Item{" +
                "era='" + era + '\'' +
                ", intro='" + intro + '\'' +
                ", video='" + video + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
