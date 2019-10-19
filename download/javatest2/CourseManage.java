package com.javatest2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * @author fxb
 * CourseManage: 课程管理类
 * addCourse(): 添加课程
 * modifyCourse(): 修改课程信息 
 */
public class CourseManage {
    /**
     * 
     * @param strInfo: 接受用户输入的信息
     * @return 添加成功返回true, 反之false
     * @throws SQLException
     */
    public boolean addCourse(String strInfo) throws SQLException{
     // 对用户输入的数据完整进行判断
        String[] courseInfo = strInfo.split("_");
        if (courseInfo.length <= 4) {
            System.out.println("输入数据不合法, 添加GG!!!");
            return false;
        }
        Connection conn = DbUtil.getConnection();
        PreparedStatement ps = null;
        String sql = "INSERT INTO tb_course VALUES(?,?,?,?,?);"; // 生成一条sql语句
        ps = conn.prepareStatement(sql);
        ps.setString(1, courseInfo[0]);
        ps.setString(2, courseInfo[1]);
        ps.setString(3, courseInfo[2]);
        ps.setString(4, courseInfo[3]);
        ps.setInt(5, Integer.parseInt(courseInfo[4]));
        try {
            ps.executeUpdate();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("添加课程信息成功！\n\n");
        return true;
    }
    /**
     * 
     * @param courseInfo: 接收用户输入的信息
     * @return 修改成功返回true, 反之false
     * @throws SQLException
     */
    public boolean modifyCourse(String courseInfo) throws SQLException{
        //对输入的学生信息进行修改, 提取出 学号,属性,属性值
        String[] strInfo = courseInfo.split("_");
        String[] courseAttribute = {"课程编号","课程名","授课老师","课程简介","学分"};
        String[] courseAttributeValue = {"CID","CName","teacher","courseIntroduce","ccredit"};
        int attriIndex= -1;
        for(int i = 0; i< courseAttribute.length; i++){
            if(strInfo[1].equals(courseAttribute[i]))
                attriIndex = i;
        }
        
        if (!(strInfo.length == 3) && attriIndex != -1) {
            System.out.println("输入数据不合法, 添加GG!!!");
            return false;
        }
        
        Connection conn = DbUtil.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT *FROM tb_course WHERE CID='"+strInfo[0]+"';");
        if (rs.next()) {
            String updateCourseSql ="";
            if(courseAttributeValue[attriIndex].equals("ccredit")){
                updateCourseSql = "UPDATE tb_course SET "+courseAttributeValue[attriIndex]+"="+strInfo[2]+" WHERE CID='"+strInfo[0]+"';";
            }else{
                updateCourseSql = "UPDATE tb_course SET "+courseAttributeValue[attriIndex]+"='"+strInfo[2]+"' WHERE CID='"+strInfo[0]+"';";
            }
            conn.createStatement().execute(updateCourseSql);
        }else {
            System.out.println("修改失败,课程号为: "+strInfo[0]+", 在数据库中未查询到, 修改失败!!");
            return false;
        }
        System.out.println("\n信息更新成功!!\n");
        return true;
    }
}
