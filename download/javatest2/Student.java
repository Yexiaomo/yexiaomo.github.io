package com.javatest2;

public class Student {
    private String name;//学生姓名
    private String stuID;//学生学号
    private String sex;//学生性别
    private String birthday;//学生出生日期
    private String grade;//学生年级
    private String major;//学生专业
    private int stuCredit;//学生已选课程总学分

    public Student() {
    }

    public Student(String name, String stuID, String sex, String birthday, String grade, String major, int stuCredit) {
        this.name = name;
        this.stuID = stuID;
        this.sex = sex;
        this.birthday = birthday;
        this.grade = grade;
        this.major = major;
        this.stuCredit = stuCredit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStuID() {
        return stuID;
    }

    public void setStuID(String stuID) {
        this.stuID = stuID;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getStuCredit() {
        return stuCredit;
    }

    public void setStuCredit(int stuCredit) {
        this.stuCredit = stuCredit;
    }
}
