package com.neusoft.elm.dao.impl;

import com.neusoft.elm.po.Food;
import com.neusoft.elm.utils.ConsoleUi;
import com.neusoft.elm.utils.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FoodDao {
    public List<Food> listByBusinessId(int businessId) {
        List<Food> list = new ArrayList<>();
        String sql = "SELECT * FROM food WHERE business_id=? ORDER BY food_id";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, businessId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(mapFood(rs));
                }
            }
        } catch (Exception ex) {
            System.out.println(ConsoleUi.error("查询食品列表异常: " + ex.getMessage()));
        }
        return list;
    }

    public Food getByIdForBusiness(int businessId, int foodId) {
        String sql = "SELECT * FROM food WHERE business_id=? AND food_id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, businessId);
            stmt.setInt(2, foodId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapFood(rs);
                }
            }
        } catch (Exception ex) {
            System.out.println(ConsoleUi.error("查询食品信息异常: " + ex.getMessage()));
        }
        return null;
    }

    public boolean add(Food food) {
        String sql = "INSERT INTO food (business_id, food_name, food_price, food_desc, food_status) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, food.getBusinessId());
            stmt.setString(2, food.getName());
            stmt.setBigDecimal(3, food.getPrice());
            stmt.setString(4, food.getDescription());
            stmt.setInt(5, food.getStatus());
            return stmt.executeUpdate() > 0;
        } catch (Exception ex) {
            System.out.println(ConsoleUi.error("新增食品异常: " + ex.getMessage()));
        }
        return false;
    }

    public boolean update(Food food) {
        String sql = "UPDATE food SET food_name=?, food_price=?, food_desc=?, food_status=? " +
                "WHERE food_id=? AND business_id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, food.getName());
            stmt.setBigDecimal(2, food.getPrice());
            stmt.setString(3, food.getDescription());
            stmt.setInt(4, food.getStatus());
            stmt.setInt(5, food.getId());
            stmt.setInt(6, food.getBusinessId());
            return stmt.executeUpdate() > 0;
        } catch (Exception ex) {
            System.out.println(ConsoleUi.error("修改食品异常: " + ex.getMessage()));
        }
        return false;
    }

    public boolean deleteByIdForBusiness(int businessId, int foodId) {
        String sql = "DELETE FROM food WHERE business_id=? AND food_id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, businessId);
            stmt.setInt(2, foodId);
            return stmt.executeUpdate() > 0;
        } catch (Exception ex) {
            System.out.println(ConsoleUi.error("删除食品异常: " + ex.getMessage()));
        }
        return false;
    }

    private Food mapFood(ResultSet rs) throws Exception {
        Food food = new Food();
        food.setId(rs.getInt("food_id"));
        food.setBusinessId(rs.getInt("business_id"));
        food.setName(rs.getString("food_name"));
        food.setPrice(rs.getBigDecimal("food_price"));
        food.setDescription(rs.getString("food_desc"));
        food.setStatus(rs.getInt("food_status"));
        return food;
    }
}
