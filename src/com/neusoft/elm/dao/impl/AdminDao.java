package com.neusoft.elm.dao.impl;

import com.neusoft.elm.po.Admin;
import com.neusoft.elm.utils.ConsoleUi;
import com.neusoft.elm.utils.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminDao {
    public Admin login(String name, String password) {
        String sql = "SELECT admin_id, admin_name, admin_password FROM admin WHERE admin_name=? AND admin_password=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Admin admin = new Admin();
                    admin.setId(rs.getInt("admin_id"));
                    admin.setName(rs.getString("admin_name"));
                    admin.setPassword(rs.getString("admin_password"));
                    return admin;
                }
            }
        } catch (Exception ex) {
            System.out.println(ConsoleUi.error("管理员登录异常: " + ex.getMessage()));
        }
        return null;
    }
}
