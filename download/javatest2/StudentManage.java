package com.javatest2;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author zd,fxb
 *
 */
public class StudentManage {
    /**
     * @param studentInfo 需要添加的学生信息字符串
     * @param studentType 需要添加学生类型
     * @return 添加true, 失败false 
     * @throws SQLException
     */
    public boolean addStudent(String studentInfo, String studentType) throws SQLException {
        // 对用户输入的数据完整进行判断
        String[] strInfo = studentInfo.split("_");
        if (strInfo.length < 8) {
            System.out.println("输入数据不合法, 添加GG!!!");
            return false;
        }
        Connection conn = DbUtil.getConnection();
        String sql = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        //先判断学生是否已经存在
//        rs = conn.createStatement().executeQuery("SELECT * FROM  表名 WHERE stuID = '"+strInfo[0]+"';");
//        if(rs.next()){
//            System.out.println("添加失败, 学生已存在!!!");
//            return false;
//        }
        
        
        if (studentType == "研究生") {
            sql = "INSERT INTO tb_graduate VALUES(?,?,?,?,?,?,?,?,?);"; // 生成一条sql语句
        } else {
            sql = "INSERT INTO tb_undergraduate VALUES(?,?,?,?,?,?,?,?,?);"; // 生成一条sql语句
        }
        ps = conn.prepareStatement(sql);
        ps.setString(1, strInfo[0]);
        ps.setString(2, strInfo[1]);
        ps.setString(3, strInfo[2]);
        ps.setString(4, strInfo[3]);
        ps.setString(5, strInfo[4]);
        ps.setString(6, strInfo[5]);
        ps.setString(7, strInfo[6]);
        ps.setString(8, strInfo[7]);
        ps.setInt(9, 0);
        try {
            ps.executeUpdate();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("添加学生信息成功！\n\n");
        return true;
    }
    
    /**
     * 
     * @param studentInfo: 需要修改的学生信息
     * @return 修改成功true, 反之false
     * @throws SQLException
     */
    public boolean modifyStuInfo(String studentInfo) throws SQLException {
        //对输入的学生信息进行修改, 提取出 学号,属性,属性值
        String[] strInfo = studentInfo.split("_");
        String[] stuAttribute = {"学号","姓名","性别","出生年月","年级","专业","班级","班主任","研究方向","导师"};
        String[] stuAttributeValue = {"stuID","name","sex","birthday","grade","major","banji","headmaster","researchDirection","tutor"};
        int attriIndex= -1;
        for(int i = 0; i< stuAttribute.length; i++){
            if(strInfo[1].equals(stuAttribute[i]))
                attriIndex = i;
        }
        
        if (!(strInfo.length == 4) && attriIndex != -1) {
            System.out.println("输入数据不合法, 添加GG!!!");
            return false;
        }
        Connection conn = DbUtil.getConnection();
        if (strInfo[3].equals("本科生") && isExistColumn(conn.prepareStatement("SELECT * FROM tb_undergraduate WHERE stuID= " + strInfo[0]).executeQuery(), "headmaster")) {
            String updateSql = "UPDATE tb_undergraduate SET "+stuAttributeValue[attriIndex]+"='"+strInfo[2]+"' WHERE stuID='"+strInfo[0]+"';";
            conn.prepareStatement(updateSql).execute();
        }else if (strInfo[3].equals("研究生") && isExistColumn(conn.prepareStatement("SELECT * FROM tb_graduate WHERE stuID= " + strInfo[0]).executeQuery(), "tutor")) {
            String updateSql = "UPDATE tb_graduate SET "+stuAttributeValue[attriIndex]+"='"+strInfo[2]+"' WHERE stuID='"+strInfo[0]+"';"; 
            conn.prepareStatement(updateSql).execute();
        }else {
            System.out.println("修改失败,学号为: "+strInfo[0]+", 在数据库中未查询到, 修改失败!!");
            return false;
        }
        System.out.println("\n信息更新成功!!\n");
        return true;
    }
    /**
     * 
     * @param rs: 数据库查询结果集
     * @param columnName, 需要查询的列名
     * @return 存在返回true, 反之false
     */
    public boolean isExistColumn(ResultSet rs, String columnName) {
        try {
            if (rs.findColumn(columnName) > 0 ) {
                return true;
            } 
        }
        catch (SQLException e) {
            return false;
        }
        
        return false;
    }


}
