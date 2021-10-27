package com.example.jdbc.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.*;

@Slf4j
@Service
public class JdbcService {

    public void findAll() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtil.getConnection();
            statement = connection.createStatement();
            String sql = "select * from person";
            resultSet = statement.executeQuery(sql);
            //遍历resultSet打印数据
            while (resultSet.next()) {
                log.info("id:{}, name:{}, age:{}",
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("age"));
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        } finally {
            try {
                //关闭连接资源
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException throwables) {
                log.error("", throwables);
            }
        }
    }

    public void add() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtil.getConnection();

            String sql = "insert into person(name, age) values(?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, "张三");
            preparedStatement.setInt(2, 18);

            preparedStatement.execute();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        } finally {
            try {
                //关闭连接资源
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException throwables) {
                log.error("", throwables);
            }
        }
    }

    public void update() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtil.getConnection();

            String sql = "update person set age = ? where name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1,20);
            preparedStatement.setString(2, "张三");

            preparedStatement.execute();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        } finally {
            try {
                //关闭连接资源
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException throwables) {
                log.error("", throwables);
            }
        }
    }

    public void delete() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtil.getConnection();

            String sql = "delete from person where name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, "张三");

            preparedStatement.execute();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        } finally {
            try {
                //关闭连接资源
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException throwables) {
                log.error("", throwables);
            }
        }
    }
}
