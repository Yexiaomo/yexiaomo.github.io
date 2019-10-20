package com.javatest2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
选课管理：
1、selectCourse():设置某学生选择某课程
2、cancelCourse():设置某学生取消某课程
 */
public class SelectCourse {
    /**
     * 
     * @param selectCourseInfo: 课程号_学号_(本科生)(研究生)
     * @return 
     * @throws SQLException 
     */
    public boolean addSelectCourse(String selectCourseInfo) throws SQLException{
        String[] strInfo = selectCourseInfo.split("_");
        //判断输入信息是否合理
        if (strInfo.length != 3) {
            System.out.println("输入信息不合理, 选课失败!!!");
            return false;
        }
        if (strInfo[2].equals("本科生")) {
            strInfo[2] = "tb_undergraduate";
        }else if(strInfo[2].equals("研究生")) {
            strInfo[2] = "tb_graduate";
        }else{
            System.out.println("选课失败: 学生类别信息有误!!!");
        }
        Connection conn = DbUtil.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = null;
        
        //判断所选的学生是否存在,如果存在,获取学生对象
        Student student = new Student();
        rs = stmt.executeQuery("SELECT * FROM "+strInfo[2]+" WHERE stuID='"+strInfo[1]+"';");
        if(rs.next()){
            student.setStuID(rs.getString("stuID"));
            student.setStuCredit(rs.getInt("stuCredit"));
        }else{
            System.out.println("选课失败: 数据库中未查询到此学生!!!");
            return false;
        }
        
        //学生存在判断是否学分已经选修够
        if(strInfo[2] == "tb_undergraduate"){
            if(student.getStuCredit() >= 50){
                System.out.println("选课失败: 此学生学分已经修够");
                return false;
            }                
        }else{
            if(student.getStuCredit() >= 40){
                System.out.println("选课失败: 此学生学分已经修够");
                return false;
            }
        }
        //判断所选的课程是否存在,如果存在,获取课程对象
        Course course = null;
        rs = stmt.executeQuery("SELECT * FROM tb_course WHERE CID='"+strInfo[0]+"';");
        if(rs.next()){
            course = new Course();
            course.setCID(rs.getString("CID"));
//            course.setCName(rs.getString("CName"));
//            course.setTeacher(rs.getString("teacher"));
//            course.setCourseIntroduce(rs.getString("courseIntroduce"));
            course.setCredit(rs.getInt("ccredit"));
        }else{
            System.out.println("选课失败: 数据库中未查询到此项课程!!!");
            return false;
        }
        //将选课信息插入到相应的选课表中
        //此时, 走到这一步, 函数默认程序已经获得了学生对象,课程对象, 将相应的选课信息插入到tb_gsc或者tb_gsc
        String tbName = null;
        if(strInfo[2] == "tb_undergraduate"){
            tbName = "tb_ugsc";
        }else{
            tbName = "tb_gsc";
        }
        String sql = "INSERT "+tbName+" VALUES(?,?);";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, strInfo[0]);
        ps.setString(2, strInfo[1]);
        if(ps.execute()){
            System.out.println("选课失败,选课信息已存在!!");
            return false;
        }
        System.out.println("选课成功!!");
        //将该学生的已选学分进行更新
        sql = "UPDATE "+strInfo[2]+" SET stuCredit = "+(course.getCredit()+student.getStuCredit())+" WHERE stuID='"+strInfo[1]+"';";
        conn.createStatement().execute(sql);
        return true;
    }
    /**
     * 
     * @param cancelCourseInfo: 取消选课信息, 格式为课程号_学号_学生类别
     * @return 取消成功返回true, 失败false
     * @throws SQLException 
     */
    public boolean cancelCourse(String cancelCourseInfo) throws SQLException{
        String[] strInfo = cancelCourseInfo.split("_");
      //判断输入信息是否合理
        if (strInfo.length != 3) {
            System.out.println("输入信息不合理, 取消学生选课失败!!!");
            return false;
        }
        if (strInfo[2].equals("本科生")) {
            strInfo[2] = "tb_undergraduate";
        }else if(strInfo[2].equals("研究生")) {
            strInfo[2] = "tb_graduate";
        }else{
            System.out.println("取消学生选课失败: 学生类别信息有误!!!");
        }
        Connection conn = DbUtil.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = null;
        //判断所选的学生是否存在,如果存在,获取学生对象
        Student student = new Student();
        rs = stmt.executeQuery("SELECT * FROM "+strInfo[2]+" WHERE stuID='"+strInfo[1]+"';");
        if(rs.next()){
            student.setStuID(rs.getString("stuID"));
            student.setStuCredit(rs.getInt("stuCredit"));
        }else{
            System.out.println("取消学生选课失败: 数据库中未查询到此学生!!!");
            return false;
        }
        //判断所选的课程是否存在,如果存在,获取课程对象
        Course course = null;
        rs = stmt.executeQuery("SELECT * FROM tb_course WHERE CID='"+strInfo[0]+"';");
        if(rs.next()){
            course = new Course();
            course.setCID(rs.getString("CID"));
            course.setCredit(rs.getInt("ccredit"));
        }else{
            System.out.println("选课失败: 数据库中未查询到此项课程!!!");
            return false;
        }
        //此时, 走到这一步, 函数默认程序已经获得了学生对象, 将相应的选课从tb_gsc或者tb_gsc删除
        String tbName = null;
        if(strInfo[2] == "tb_undergraduate"){
            tbName = "tb_ugsc";
        }else{
            tbName = "tb_gsc";
        }
        String sql = "DELETE FROM "+tbName+" WHERE courseID=? AND studentID=?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, strInfo[0]);
        ps.setString(2, strInfo[1]);
        if(ps.execute()){
            System.out.println("取消选课失败,选课信息不存在!!");
            return false;
        }
        System.out.println("取消选课成功!!");
        //将该学生的已选学分进行更新
        sql = "UPDATE "+strInfo[2]+" SET stuCredit = "+(student.getStuCredit() - course.getCredit())+" WHERE stuID='"+strInfo[1]+"';";
        conn.createStatement().execute(sql);        
        return true;
    }
}