package com.javatest2;

public class Course {
    private String CID;//课程ID
    private String CName;//课程名称
    private String teacher;//上课教师
    private String courseIntroduce;//课程简介
    private int credit;//学分

    public Course() {
    }

    public String getCID() {
        return CID;
    }

    public void setCID(String CID) {
        this.CID = CID;
    }

    public String getCName() {
        return CName;
    }

    public void setCName(String CName) {
        this.CName = CName;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getCourseIntroduce() {
        return courseIntroduce;
    }

    public void setCourseIntroduce(String courseIntroduce) {
        this.courseIntroduce = courseIntroduce;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }
}
