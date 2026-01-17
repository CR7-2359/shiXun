package com.neusoft.elm.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/* JDBC 连接工具类 */
public final class DBUtil {
    private static final String URL =
            "jdbc:mysql://localhost:3306/elm_admin?useSSL=false&serverTimezone=UTC&characterEncoding=utf8";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // 加载驱动
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("未找到MySQL驱动。", ex);
        }
    }

    private DBUtil() {
    }

    /* 获取数据库连接 */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
