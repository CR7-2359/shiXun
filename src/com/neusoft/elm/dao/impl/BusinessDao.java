package com.neusoft.elm.dao.impl;

import com.neusoft.elm.po.Business;
import com.neusoft.elm.utils.ConsoleUi;
import com.neusoft.elm.utils.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/* 商家数据访问 */
public class BusinessDao {
    /* 商家登录 */
    public Business login(String account, String password) {
        String sql = "SELECT * FROM business WHERE business_account=? AND business_password=?"; // 登录查询
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, account); // 绑定账号
            stmt.setString(2, password); // 绑定密码
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapBusiness(rs);
                }
            }
        } catch (Exception ex) {
            System.out.println(ConsoleUi.error("商家登录异常: " + ex.getMessage()));
        }
        return null;
    }

    /* 按编号查询商家 */
    public Business getById(int id) {
        String sql = "SELECT * FROM business WHERE business_id=?"; // 查询单条
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id); // 绑定编号
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapBusiness(rs);
                }
            }
        } catch (Exception ex) {
            System.out.println(ConsoleUi.error("查询商家信息异常: " + ex.getMessage()));
        }
        return null;
    }

    /* 查询全部商家 */
    public List<Business> listAll() {
        List<Business> list = new ArrayList<>();
        String sql = "SELECT * FROM business ORDER BY business_id"; // 查询列表
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(mapBusiness(rs));
            }
        } catch (Exception ex) {
            System.out.println(ConsoleUi.error("查询商家列表异常: " + ex.getMessage()));
        }
        return list;
    }

    /* 按名称关键字搜索 */
    public List<Business> findByNameLike(String keyword) {
        List<Business> list = new ArrayList<>();
        String sql = "SELECT * FROM business WHERE business_name LIKE ? ORDER BY business_id"; // 模糊查询
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + keyword + "%"); // 绑定关键词
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(mapBusiness(rs));
                }
            }
        } catch (Exception ex) {
            System.out.println(ConsoleUi.error("搜索商家异常: " + ex.getMessage()));
        }
        return list;
    }

    /* 新增商家 */
    public boolean add(Business business) {
        String sql = "INSERT INTO business (business_account, business_password, business_name, business_phone, " +
                "business_address, business_desc) VALUES (?, ?, ?, ?, ?, ?)"; // 插入语句
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, business.getAccount()); // 绑定账号
            stmt.setString(2, business.getPassword()); // 绑定密码
            stmt.setString(3, business.getName()); // 绑定名称
            stmt.setString(4, business.getPhone()); // 绑定电话
            stmt.setString(5, business.getAddress()); // 绑定地址
            stmt.setString(6, business.getDescription()); // 绑定描述
            return stmt.executeUpdate() > 0;
        } catch (Exception ex) {
            System.out.println(ConsoleUi.error("新增商家异常: " + ex.getMessage()));
        }
        return false;
    }

    /* 删除商家 */
    public boolean deleteById(int id) {
        String sql = "DELETE FROM business WHERE business_id=?"; // 删除语句
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id); // 绑定编号
            return stmt.executeUpdate() > 0;
        } catch (Exception ex) {
            System.out.println(ConsoleUi.error("删除商家异常: " + ex.getMessage()));
        }
        return false;
    }

    /* 更新商家信息 */
    public boolean updateInfo(Business business) {
        String sql = "UPDATE business SET business_name=?, business_phone=?, business_address=?, business_desc=? " +
                "WHERE business_id=?"; // 更新语句
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, business.getName()); // 绑定名称
            stmt.setString(2, business.getPhone()); // 绑定电话
            stmt.setString(3, business.getAddress()); // 绑定地址
            stmt.setString(4, business.getDescription()); // 绑定描述
            stmt.setInt(5, business.getId()); // 绑定编号
            return stmt.executeUpdate() > 0;
        } catch (Exception ex) {
            System.out.println(ConsoleUi.error("修改商家信息异常: " + ex.getMessage()));
        }
        return false;
    }

    /* 更新商家密码 */
    public boolean updatePassword(int businessId, String oldPassword, String newPassword) {
        String sql = "UPDATE business SET business_password=? WHERE business_id=? AND business_password=?"; // 更新密码
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newPassword); // 绑定新密码
            stmt.setInt(2, businessId); // 绑定商家编号
            stmt.setString(3, oldPassword); // 绑定旧密码
            return stmt.executeUpdate() > 0;
        } catch (Exception ex) {
            System.out.println(ConsoleUi.error("修改商家密码异常: " + ex.getMessage()));
        }
        return false;
    }

    /* ResultSet -> Business 映射 */
    private Business mapBusiness(ResultSet rs) throws Exception {
        Business business = new Business();
        business.setId(rs.getInt("business_id")); // 设置编号
        business.setAccount(rs.getString("business_account")); // 设置账号
        business.setPassword(rs.getString("business_password")); // 设置密码
        business.setName(rs.getString("business_name")); // 设置名称
        business.setPhone(rs.getString("business_phone")); // 设置电话
        business.setAddress(rs.getString("business_address")); // 设置地址
        business.setDescription(rs.getString("business_desc")); // 设置描述
        return business;
    }
}
