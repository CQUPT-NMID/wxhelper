package cn.edu.cqupt.nmid.wxhelper.wxhelper.po;

 /**
 * @author MaYunHao
 * @version 1.0
 * @description  年代类
 * @date 2019/12/9 18:44
 */

public class Era {
    public Integer id;
    public String eraname;
    public String begintime;
    public String endtime;

    public Era() {
    }

    public Era(String eraname, String begintime, String endtime) {
        this.eraname = eraname;
        this.begintime = begintime;
        this.endtime = endtime;
    }

     public Integer getId() {
         return id;
     }

     public void setId(Integer id) {
         this.id = id;
     }

     public String getEraname() {
        return eraname;
    }

    public void setEraname(String eraname) {
        this.eraname = eraname;
    }

    public String getBegintime() {
        return begintime;
    }

    public void setBegintime(String begintime) {
        this.begintime = begintime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }
}
