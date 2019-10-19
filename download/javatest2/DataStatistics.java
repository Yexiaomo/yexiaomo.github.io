package com.javatest2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/*
数据统计：
1、studentStatistics():按学生统计选课情况
2、courseStatistics():按课程统计选课情况
 */
public class DataStatistics {
    /**
     * 按学生统计, 思路利用 SQL: GROUP BY 语句, 按学号分组,统计
     * 因为有两张表,所以进行查询结果需要分别处理, 或者合并
     * @throws SQLException 
     */
    public void studentStatistic() throws SQLException{
        Connection conn = DbUtil.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = null;
        
        //数据为两列,一列为:学号,一列为:选课数
        
        //对研究生选课表进行统计, 假设此时选课表中已经有数据
        rs = stmt.executeQuery("SELECT studentID, COUNT(tb_gsc.courseID) AS countCourse FROM tb_gsc GROUP BY studentID;");
        //利用Map集合进行存储
        Map<String, Integer> gMap = new HashMap<String, Integer>();
        while(rs.next()){
            gMap.put(rs.getString("studentID"), rs.getInt("countCourse"));
        }
        
        //对本科生选课情况进行统计, 假设此时选课表中已经有数据
        rs = stmt.executeQuery("SELECT studentID, COUNT(tb_ugsc.courseID) AS countCourse FROM tb_ugsc GROUP BY studentID;");
        Map<String, Integer> ugMap = new HashMap<String, Integer>();
        while(rs.next()){
            ugMap.put(rs.getString("studentID"), rs.getInt("countCourse"));
        }
        
        //打印选课统计表
        System.out.println("\n_______研究生按学生选课统计表______");
        for(Map.Entry<String, Integer> entry : gMap.entrySet()){
            System.out.println(entry.getKey()+"  |  "+entry.getValue());
        }
        System.out.println("__________________________________");
        System.out.println("\n_______本科生按学生选课统计表______");
        for(Map.Entry<String, Integer> entry : ugMap.entrySet()){
            System.out.println(entry.getKey()+"  |  "+entry.getValue());
        }
        System.out.println("_________________________________");
    }
    public void courseStatistics() throws SQLException{
        Connection conn = DbUtil.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = null;
        
        //数据为两列,一列为:学号,一列为:选课数
        
        //对研究生选课表进行统计, 假设此时选课表中已经有数据
        rs = stmt.executeQuery("SELECT courseID, COUNT(tb_gsc.studentID) AS countStudent FROM tb_gsc GROUP BY courseID;");
        //利用Map集合进行存储
        Map<String, Integer> gMap = new HashMap<String, Integer>();
        while(rs.next()){
            gMap.put(rs.getString("courseID"), rs.getInt("countStudent"));
        }
        
        //对本科生选课情况进行统计, 假设此时选课表中已经有数据
        rs = stmt.executeQuery("SELECT courseID, COUNT(tb_ugsc.studentID) AS countStudent FROM tb_ugsc GROUP BY courseID;");
        Map<String, Integer> ugMap = new HashMap<String, Integer>();
        while(rs.next()){
            ugMap.put(rs.getString("courseID"), rs.getInt("countStudent"));
        }
        
        //打印选课统计表
        System.out.println("\n_______研究生按课程选课统计表______");
        for(Map.Entry<String, Integer> entry : gMap.entrySet()){
            System.out.println(entry.getKey()+"  |  "+entry.getValue());
        }
        System.out.println("__________________________________");
        System.out.println("\n_______本科生按课程选课统计表______");
        for(Map.Entry<String, Integer> entry : ugMap.entrySet()){
            System.out.println(entry.getKey()+"  |  "+entry.getValue());
        }
        System.out.println("_________________________________");
    }
}
