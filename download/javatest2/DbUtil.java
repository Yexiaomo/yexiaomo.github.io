package com.javatest2;
/*
数据库连接实例
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
    public static final String URL = "jdbc:mysql://localhost:3306/javatest2?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC";
    public static final String USER = "root";
    public static final String PASSWORD = "1";
    private static Connection conn = null;

    static {
        try {
            //1.加载驱动程序
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2. 获得数据库连接
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public static Connection getConnection() {
        return conn;
    }
}
