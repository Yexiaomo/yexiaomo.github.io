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
        
        //原来并没有打算打印出姓名,只有两个属性,利用的是hashMap
        //最后又重写了SQl显示出姓名,或者更多时,就利用不了Map,直接就打印了
        
        /*0.1版本
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
        */
        //研究生打印统计结果
        rs = stmt.executeQuery("SELECT "
                +"studentID,tb_graduate.name, "
                +"COUNT( tb_gsc.courseID ) AS countCourse  "
                +"FROM "
                    +"tb_gsc "
                +"INNER JOIN tb_graduate "
                +"ON "
                    +"tb_gsc.studentID = tb_graduate.stuID "
                +"GROUP BY "
                    +"tb_gsc.studentID "
                +"ORDER BY "
                    +"countCourse DESC;");
        System.out.println("\n_______研究生按学生选课统计表______");
        System.out.printf("\n%s%6s%2s\n", "     学号","        姓名","        数量");
        while(rs.next()){
            System.out.printf("%s%6s%10s\n", rs.getString("studentID"), rs.getString("name"), rs.getInt("countCourse"));
        }
        System.out.println("__________________________________");
        System.out.println("\n_______本科生按学生选课统计表______");
        System.out.printf("\n%-10s%-6s%-4s\n", "   学号","      姓名","           数量");
        rs = stmt.executeQuery("SELECT "
                +"studentID,tb_undergraduate.name, "
                +"COUNT( tb_ugsc.courseID ) AS countCourse  "
                +"FROM "
                    +"tb_ugsc "
                +"INNER JOIN tb_undergraduate "
                +"ON "
                    +"tb_ugsc.studentID = tb_undergraduate.stuID "
                +"GROUP BY "
                    +"tb_ugsc.studentID "
                +"ORDER BY "
                    +"countCourse DESC;");
        while(rs.next()){
            System.out.printf("%-10s%-6s%8s\n", rs.getString("studentID"), rs.getString("name"), rs.getInt("countCourse"));
        }
        System.out.println("_________________________________");
    }
    
    /**
     * 按课程统计, 思路利用 SQL: GROUP BY 语句, COUNT函数, ORDER BY排序
     * 打印查询结果
     * @throws SQLException
     */
    public void courseStatistics() throws SQLException{
        Connection conn = DbUtil.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = null;
        
        /*和上面学生统计一样,为了输出多个属性,不再使用HashMap
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
        */
        //打印选课统计表
        rs = stmt.executeQuery("SELECT "
                                    +"CID, CName, "
                                    +"COUNT( tb_gsc.studentID ) AS countStudent  "
                                +"FROM "
                                    +"tb_gsc "
                                +"INNER JOIN tb_course "
                                +"ON "
                                    +"tb_gsc.courseID = tb_course.CID "
                                +"GROUP BY "
                                    +"tb_gsc.courseID "
                                +"ORDER BY "
                                    +"countStudent DESC;");
        System.out.println("\n_______研究生按课程选课统计表______");
//        for(Map.Entry<String, Integer> entry : gMap.entrySet()){
//            System.out.println(entry.getKey()+"  |  "+entry.getValue());
//        }
        System.out.printf("\n%s%6s%s\n", "课程号","  数量","   课程名");
        while(rs.next()){
            System.out.printf("%-8s%-5s%s\n", rs.getString("CID"), rs.getInt("countStudent"), rs.getString("CNAME"));
        }
        System.out.println("__________________________________");
        
        
        rs = stmt.executeQuery("SELECT "
                                    +"CID, CName, "
                                    +"COUNT( tb_ugsc.studentID ) AS countStudent  "
                                +"FROM "
                                    +"tb_ugsc "
                                +"INNER JOIN tb_course "
                                +"ON "
                                    +"tb_ugsc.courseID = tb_course.CID "
                                +"GROUP BY "
                                    +"tb_ugsc.courseID "
                                +"ORDER BY "
                                    +"countStudent DESC;");
        System.out.println("\n_______本科生按课程选课统计表______");
//        for(Map.Entry<String, Integer> entry : ugMap.entrySet()){
//            System.out.println(entry.getKey()+"  |  "+entry.getValue());
//        }
        System.out.printf("\n%s%6s%s\n", "课程号","  数量","   课程名");
        while(rs.next()){
            System.out.printf("%-8s%-5s%s\n", rs.getString("CID"), rs.getInt("countStudent"), rs.getString("CNAME"));
        }
        System.out.println("_________________________________");
    }
}
