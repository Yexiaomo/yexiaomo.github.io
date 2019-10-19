package com.javatest2;

public class Undergraduate extends Student {
    private String banji;//本科生班级
    private String Headmaster;//本科生班主任

    public Undergraduate() {
    }

    public Undergraduate(String name, String stuID, String sex, String birthday, String grade, String major, int stuCredit, String aClass, String headmaster) {
        super(name, stuID, sex, birthday, grade, major, stuCredit);
        banji = aClass;
        Headmaster = headmaster;
    }

    public String getBanji() {
        return banji;
    }

    public void setBanji(String banji) {
        this.banji = banji;
    }

    public String getHeadmaster() {
        return Headmaster;
    }

    public void setHeadmaster(String headmaster) {
        this.banji = headmaster;
    }
}
