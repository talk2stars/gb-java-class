package com.example.jdbc.jdbc;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
public class JdbcUtil {

    /**
     * 获取数据库连接
     */
    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://mysql.jdbc.example.com:3306/jdbc?characterEncoding=utf-8";
            String user = "root";
            String password = "root";
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            log.error(e.getMessage(), e);
        }
        return connection;
    }
}
