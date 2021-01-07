package com.czg;

import java.sql.*;

public class JDBCTest {

    public static final String driver = "com.mysql.jdbc.Driver";

    public static final String url = "jdbc:mysql://localhost:3306/test";

    public static final String user = "root";

    public static final String password = "root";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;

        Class.forName(driver);
        conn = DriverManager.getConnection(url, user, password);
        pstat = conn.prepareStatement("show variables like 'basedir'");
        rs = pstat.executeQuery();

        while (rs.next()) {
            String name = rs.getString(1);
            String value = rs.getString(2);
            System.out.println(name + ", " + value);
        }

        rs.close();
        pstat.close();
        conn.close();
    }
}
