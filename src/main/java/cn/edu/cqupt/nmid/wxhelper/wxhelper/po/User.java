package cn.edu.cqupt.nmid.wxhelper.wxhelper.po;


public class User {
    private String id;
    private String nickname;
    private Integer gender;
    private String city;
    private String province;
    private String country;
    private String avater;

    public User(String  id, String nickname, Integer gender, String city, String province, String country, String avater) {
        this.id = id;
        this.nickname = nickname;
        this.gender = gender;
        this.city = city;
        this.province = province;
        this.country = country;
        this.avater = avater;
    }


    public User() {
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String  getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAvater() {
        return avater;
    }

    public void setAvater(String avater) {
        this.avater = avater;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", nickname='" + nickname + '\'' +
                ", gender='" + gender + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", country='" + country + '\'' +
                ", avater='" + avater + '\'' +
                '}';
    }
}
