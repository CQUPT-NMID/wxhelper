package cn.edu.cqupt.nmid.wxhelper.wxhelper.po;

import java.util.Date;
import java.io.Serializable;

/**
 * (Volunteer)实体类
 *
 * @author makejava
 * @since 2020-03-11 16:44:18
 */
public class Volunteer implements Serializable {
    private static final long serialVersionUID = -73622938226310175L;
    
    private Integer id;
    
    private String name;
    
    private String telephone;
    
    private String email;
    
    private String reply;
    
    private Integer status;
    
    private Integer rid;
    
    private Date publishTime;
    
    private String userid;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

}