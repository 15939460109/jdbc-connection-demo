package com.czg;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class C3P0Test {

    private static ComboPooledDataSource dataSource;

    static {
        // 将指定配置文件中的指定配置内容加载进来
        // 无参构造器读取默认配置  有参构造器读取named-config配置
        // dataSource = new ComboPooledDataSource();
        dataSource = new ComboPooledDataSource("myC3p0");
        String name = dataSource.getDataSourceName();
        System.out.println("数据源的名字:" + name);

    }

    // 获取连接
    public static Connection getConn() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 断开连接
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
        Connection conn = getConn();
        if (conn != null) {
            System.out.println("成功获取连接");
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
