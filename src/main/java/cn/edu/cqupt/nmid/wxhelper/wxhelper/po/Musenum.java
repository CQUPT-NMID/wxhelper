package cn.edu.cqupt.nmid.wxhelper.wxhelper.po;

import java.util.ArrayList;
import java.util.List;

public class Musenum {
    private Integer id;
    private String name;
    private List<Article> articles = new ArrayList<>();
    private String introduce;
    private String photo;   //图片的url
    private List<Advice> advices;

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

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public List<Advice> getAdvices() {
        return advices;
    }

    public void setAdvices(List<Advice> advices) {
        this.advices = advices;
    }

    @Override
    public String toString() {
        return "Musenum{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", articles=" + articles +
                ", introduce='" + introduce + '\'' +
                ", photo='" + photo + '\'' +
                ", advices=" + advices +
                '}';
    }
}
