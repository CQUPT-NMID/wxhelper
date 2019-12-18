package cn.edu.cqupt.nmid.wxhelper.wxhelper.po;

public class Advice {
    private Integer id;
    private String advice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    @Override
    public String toString() {
        return "Advice{" +
                "id=" + id +
                ", advice='" + advice + '\'' +
                '}';
    }
}
