package com.czg;

import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Properties;

public class DBCPTest {

    private static DataSource dataSource;

    static {
        Properties properties = new Properties();
        try {
            properties.load(DBCPTest.class.getClassLoader().getResourceAsStream("dbcp.properties"));

            // 基础工厂类，来创建连接池
            dataSource = BasicDataSourceFactory.createDataSource(properties);
            Connection conn = getConn();
            DatabaseMetaData metaData = conn.getMetaData();

            System.out.println(metaData.getDatabaseProductName() + ", " + metaData.getDatabaseProductVersion());

            closeConn(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 获取连接
    public static Connection getConn() {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    // 关闭连接
    public static void closeConn(Connection conn) {
        try {
            if (conn != null && conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i ++) {
            Connection connection = getConn();
            System.out.println("获取连接" + i);
            closeConn(connection);
        }
    }
}
