package com.neusoft.elm.dao.impl;

import com.neusoft.elm.po.Food;
import com.neusoft.elm.utils.ConsoleUi;
import com.neusoft.elm.utils.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/* 食品数据访问 */
public class FoodDao {
    /* 按商家查询食品列表 */
    public List<Food> listByBusinessId(int businessId) {
        List<Food> list = new ArrayList<>();
        String sql = "SELECT * FROM food WHERE business_id=? ORDER BY food_id"; // 查询列表
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, businessId); // 绑定商家编号
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

    /* 按商家与食品编号查询 */
    public Food getByIdForBusiness(int businessId, int foodId) {
        String sql = "SELECT * FROM food WHERE business_id=? AND food_id=?"; // 查询单条
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, businessId); // 绑定商家编号
            stmt.setInt(2, foodId); // 绑定食品编号
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

    /* 新增食品 */
    public boolean add(Food food) {
        String sql = "INSERT INTO food (business_id, food_name, food_price, food_desc, food_status) " +
                "VALUES (?, ?, ?, ?, ?)"; // 插入语句
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, food.getBusinessId()); // 绑定商家编号
            stmt.setString(2, food.getName()); // 绑定名称
            stmt.setBigDecimal(3, food.getPrice()); // 绑定价格
            stmt.setString(4, food.getDescription()); // 绑定描述
            stmt.setInt(5, food.getStatus()); // 绑定状态
            return stmt.executeUpdate() > 0;
        } catch (Exception ex) {
            System.out.println(ConsoleUi.error("新增食品异常: " + ex.getMessage()));
        }
        return false;
    }

    /* 更新食品 */
    public boolean update(Food food) {
        String sql = "UPDATE food SET food_name=?, food_price=?, food_desc=?, food_status=? " +
                "WHERE food_id=? AND business_id=?"; // 更新语句
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, food.getName()); // 绑定名称
            stmt.setBigDecimal(2, food.getPrice()); // 绑定价格
            stmt.setString(3, food.getDescription()); // 绑定描述
            stmt.setInt(4, food.getStatus()); // 绑定状态
            stmt.setInt(5, food.getId()); // 绑定食品编号
            stmt.setInt(6, food.getBusinessId()); // 绑定商家编号
            return stmt.executeUpdate() > 0;
        } catch (Exception ex) {
            System.out.println(ConsoleUi.error("修改食品异常: " + ex.getMessage()));
        }
        return false;
    }

    /* 删除食品 */
    public boolean deleteByIdForBusiness(int businessId, int foodId) {
        String sql = "DELETE FROM food WHERE business_id=? AND food_id=?"; // 删除语句
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, businessId); // 绑定商家编号
            stmt.setInt(2, foodId); // 绑定食品编号
            return stmt.executeUpdate() > 0;
        } catch (Exception ex) {
            System.out.println(ConsoleUi.error("删除食品异常: " + ex.getMessage()));
        }
        return false;
    }

    /* ResultSet -> Food 映射 */
    private Food mapFood(ResultSet rs) throws Exception {
        Food food = new Food();
        food.setId(rs.getInt("food_id")); // 设置编号
        food.setBusinessId(rs.getInt("business_id")); // 设置商家编号
        food.setName(rs.getString("food_name")); // 设置名称
        food.setPrice(rs.getBigDecimal("food_price")); // 设置价格
        food.setDescription(rs.getString("food_desc")); // 设置描述
        food.setStatus(rs.getInt("food_status")); // 设置状态
        return food;
    }
}
