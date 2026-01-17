package com.neusoft.elm.dao.impl;

import com.neusoft.elm.po.Admin;
import com.neusoft.elm.utils.ConsoleUi;
import com.neusoft.elm.utils.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/* 管理员数据访问 */
public class AdminDao {
    /* 按账号密码登录 */
    public Admin login(String name, String password) {
        String sql = "SELECT admin_id, admin_name, admin_password FROM admin WHERE admin_name=? AND admin_password=?"; // 登录查询
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name); // 绑定账号
            stmt.setString(2, password); // 绑定密码
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Admin admin = new Admin();
                    admin.setId(rs.getInt("admin_id")); // 设置编号
                    admin.setName(rs.getString("admin_name")); // 设置名称
                    admin.setPassword(rs.getString("admin_password")); // 设置密码
                    return admin;
                }
            }
        } catch (Exception ex) {
            System.out.println(ConsoleUi.error("管理员登录异常: " + ex.getMessage()));
        }
        return null;
    }
}
