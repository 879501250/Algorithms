package com.qq.test.tool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCTool {
    private String url = "jdbc:mysql://localhost:3306/qq_data";
    private String username = "root";
    private String password = "123456";

    static {
        try {
            // 导入MySQL JDBC驱动
            // mysql 驱动 6 以上
            Class.forName("com.mysql.cj.jdbc.Driver");
            // mysql 驱动 5
            // Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public JDBCTool() {

    }

    public JDBCTool(String url) {
        this.url = url;
    }

    public JDBCTool(String url,String username,String password){
        this.url = url;
        this.username = username;
        this.password = password;
    }

    /**
     * 获取数据库连接
     *
     * @return
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
