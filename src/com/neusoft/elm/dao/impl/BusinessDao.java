package com.neusoft.elm.dao.impl;

import com.neusoft.elm.po.Business;
import com.neusoft.elm.utils.ConsoleUi;
import com.neusoft.elm.utils.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BusinessDao {
    public Business login(String account, String password) {
        String sql = "SELECT * FROM business WHERE business_account=? AND business_password=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, account);
            stmt.setString(2, password);
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

    public Business getById(int id) {
        String sql = "SELECT * FROM business WHERE business_id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
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

    public List<Business> listAll() {
        List<Business> list = new ArrayList<>();
        String sql = "SELECT * FROM business ORDER BY business_id";
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

    public List<Business> findByNameLike(String keyword) {
        List<Business> list = new ArrayList<>();
        String sql = "SELECT * FROM business WHERE business_name LIKE ? ORDER BY business_id";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + keyword + "%");
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

    public boolean add(Business business) {
        String sql = "INSERT INTO business (business_account, business_password, business_name, business_phone, " +
                "business_address, business_desc) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, business.getAccount());
            stmt.setString(2, business.getPassword());
            stmt.setString(3, business.getName());
            stmt.setString(4, business.getPhone());
            stmt.setString(5, business.getAddress());
            stmt.setString(6, business.getDescription());
            return stmt.executeUpdate() > 0;
        } catch (Exception ex) {
            System.out.println(ConsoleUi.error("新增商家异常: " + ex.getMessage()));
        }
        return false;
    }

    public boolean deleteById(int id) {
        String sql = "DELETE FROM business WHERE business_id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (Exception ex) {
            System.out.println(ConsoleUi.error("删除商家异常: " + ex.getMessage()));
        }
        return false;
    }

    public boolean updateInfo(Business business) {
        String sql = "UPDATE business SET business_name=?, business_phone=?, business_address=?, business_desc=? " +
                "WHERE business_id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, business.getName());
            stmt.setString(2, business.getPhone());
            stmt.setString(3, business.getAddress());
            stmt.setString(4, business.getDescription());
            stmt.setInt(5, business.getId());
            return stmt.executeUpdate() > 0;
        } catch (Exception ex) {
            System.out.println(ConsoleUi.error("修改商家信息异常: " + ex.getMessage()));
        }
        return false;
    }

    public boolean updatePassword(int businessId, String oldPassword, String newPassword) {
        String sql = "UPDATE business SET business_password=? WHERE business_id=? AND business_password=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newPassword);
            stmt.setInt(2, businessId);
            stmt.setString(3, oldPassword);
            return stmt.executeUpdate() > 0;
        } catch (Exception ex) {
            System.out.println(ConsoleUi.error("修改商家密码异常: " + ex.getMessage()));
        }
        return false;
    }

    private Business mapBusiness(ResultSet rs) throws Exception {
        Business business = new Business();
        business.setId(rs.getInt("business_id"));
        business.setAccount(rs.getString("business_account"));
        business.setPassword(rs.getString("business_password"));
        business.setName(rs.getString("business_name"));
        business.setPhone(rs.getString("business_phone"));
        business.setAddress(rs.getString("business_address"));
        business.setDescription(rs.getString("business_desc"));
        return business;
    }
}
