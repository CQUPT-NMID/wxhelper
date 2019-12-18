package cn.edu.cqupt.nmid.wxhelper.wxhelper.po;

import net.bytebuddy.implementation.bytecode.assign.TypeCasting;

public class User {
    private String id;
    private String nickname;
    private String gender;
    private String city;
    private String provice;
    private String country;
    private String avater;

    public User(String  id, String nickname, String gender, String city, String provice, String country, String avater) {
        this.id = id;
        this.nickname = nickname;
        this.gender = gender;
        this.city = city;
        this.provice = provice;
        this.country = country;
        this.avater = avater;
    }


    public User() {
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvice() {
        return provice;
    }

    public void setProvice(String provice) {
        this.provice = provice;
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
                ", provice='" + provice + '\'' +
                ", country='" + country + '\'' +
                ", avater='" + avater + '\'' +
                '}';
    }
}
