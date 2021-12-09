package com.runrab.mqrunrab.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JdbcUtil {
    public static void main(String[] args) throws Exception {
        //1.加载驱动(开发推荐的方式)
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/shop";
        String userName="root";
        String pass="123456";
        Connection conn = null;
        //2.获取与数据库的链接
        conn = DriverManager.getConnection(url, userName, pass);
        Statement st = null;
        //3.获取用于向数据库发送sql语句的statement
        st = conn.createStatement();
        //4.向数据库发sql
        String sql = "select * from user";
        System.out.println(st.executeQuery(sql).getMetaData());
        st.close();
    }
}
