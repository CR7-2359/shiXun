package com.neusoft.elm.view.impl;

import com.neusoft.elm.dao.impl.BusinessDao;
import com.neusoft.elm.po.Business;
import com.neusoft.elm.utils.ConsoleUi;
import com.neusoft.elm.utils.InputUtil;

/* 商家端界面与业务流程 */
public class BusinessView {
    private final BusinessDao businessDao = new BusinessDao();
    private final FoodService foodService = new FoodService();

    /* 商家端入口菜单 */
    public void start() {
        while (true) {
            System.out.println();
            System.out.println(ConsoleUi.label(ConsoleUi.ICON_MENU, "商家端"));
            System.out.println("1. " + ConsoleUi.ICON_BUSINESS + " 商家登录");
            System.out.println("0. " + ConsoleUi.ICON_EXIT + " 退出");
            int choice = InputUtil.readInt("请选择: "); // 读取菜单选择
            switch (choice) {
                case 1:
                    businessLoginFlow(); // 进入登录流程
                    break;
                case 0:
                    System.out.println(ConsoleUi.label(ConsoleUi.ICON_EXIT, "已退出系统。"));
                    return;
                default:
                    System.out.println(ConsoleUi.warn("无效选项。"));
                    break;
            }
        }
    }

    /* 商家登录与功能菜单 */
    private void businessLoginFlow() {
        String account = InputUtil.readLine("账号: "); // 读取账号
        String password = InputUtil.readLine("密码: "); // 读取密码
        Business business = businessDao.login(account, password); // 校验登录
        if (business == null) {
            System.out.println(ConsoleUi.error("登录失败。"));
            return;
        }
        System.out.println(ConsoleUi.label(ConsoleUi.ICON_WELCOME, "欢迎, " + business.getName() + "。"));
        while (true) {
            printBusinessMenu(); // 展示商家菜单
            int choice = InputUtil.readInt("请选择: "); // 读取菜单选择
            switch (choice) {
                case 1:
                    viewBusinessInfoFlow(business.getId()); // 查看商家信息
                    break;
                case 2:
                    updateBusinessInfoFlow(business.getId()); // 修改商家信息
                    break;
                case 3:
                    updateBusinessPasswordFlow(business.getId()); // 修改密码
                    break;
                case 4:
                    foodService.listFoods(business.getId()); // 查看食品列表
                    break;
                case 5:
                    foodService.addFood(business.getId()); // 新增食品
                    break;
                case 6:
                    foodService.updateFood(business.getId()); // 修改食品
                    break;
                case 7:
                    foodService.deleteFood(business.getId()); // 删除食品
                    break;
                case 8:
                    foodService.exportFoods(business.getId()); // 导出食品列表
                    break;
                case 0:
                    System.out.println(ConsoleUi.label(ConsoleUi.ICON_EXIT, "已退出登录。"));
                    return;
                default:
                    System.out.println(ConsoleUi.warn("无效选项。"));
                    break;
            }
        }
    }

    /* 打印商家菜单 */
    private void printBusinessMenu() {
        System.out.println();
        System.out.println(ConsoleUi.label(ConsoleUi.ICON_MENU, "商家菜单"));
        System.out.println("1. " + ConsoleUi.ICON_INFO + " 查看商家信息");
        System.out.println("2. " + ConsoleUi.ICON_EDIT + " 修改商家信息");
        System.out.println("3. " + ConsoleUi.ICON_PASSWORD + " 更新密码");
        System.out.println("4. " + ConsoleUi.ICON_FOOD + " 查看食品列表");
        System.out.println("5. " + ConsoleUi.ICON_ADD + " 新增食品");
        System.out.println("6. " + ConsoleUi.ICON_EDIT + " 修改食品");
        System.out.println("7. " + ConsoleUi.ICON_DELETE + " 删除食品");
        System.out.println("8. " + ConsoleUi.ICON_EXPORT + " 导出食品列表");
        System.out.println("0. " + ConsoleUi.ICON_EXIT + " 退出登录");
    }

    /* 查看商家信息 */
    private void viewBusinessInfoFlow(int businessId) {
        Business business = businessDao.getById(businessId); // 查询商家信息
        if (business == null) {
            System.out.println(ConsoleUi.info("暂无数据。"));
            return;
        }
        System.out.println(ConsoleUi.label(ConsoleUi.ICON_INFO, "商家信息"));
        System.out.println("编号: " + business.getId());
        System.out.println("账号: " + business.getAccount());
        System.out.println("名称: " + business.getName());
        System.out.println("电话: " + business.getPhone());
        System.out.println("地址: " + business.getAddress());
        System.out.println("描述: " + business.getDescription());
    }

    /* 修改商家信息 */
    private void updateBusinessInfoFlow(int businessId) {
        Business business = businessDao.getById(businessId); // 查询商家信息
        if (business == null) {
            System.out.println(ConsoleUi.info("暂无数据。"));
            return;
        }
        String name = InputUtil.readLine("名称(回车保留): "); // 读取名称
        String phone = InputUtil.readLine("电话(回车保留): "); // 读取电话
        String address = InputUtil.readLine("地址(回车保留): "); // 读取地址
        String description = InputUtil.readLine("描述(回车保留): "); // 读取描述

        if (!name.isEmpty()) {
            business.setName(name); // 更新名称
        }
        if (!phone.isEmpty()) {
            business.setPhone(phone); // 更新电话
        }
        if (!address.isEmpty()) {
            business.setAddress(address); // 更新地址
        }
        if (!description.isEmpty()) {
            business.setDescription(description); // 更新描述
        }

        boolean ok = businessDao.updateInfo(business); // 执行更新
        System.out.println(ok ? ConsoleUi.success("信息更新成功。") : ConsoleUi.error("信息更新失败。"));
    }

    /* 修改商家密码 */
    private void updateBusinessPasswordFlow(int businessId) {
        String oldPassword = InputUtil.readLine("旧密码: "); // 读取旧密码
        String newPassword = InputUtil.readLine("新密码: "); // 读取新密码
        String confirm = InputUtil.readLine("确认新密码: "); // 读取确认密码
        if (!newPassword.equals(confirm)) {
            System.out.println(ConsoleUi.warn("两次输入的密码不一致。"));
            return;
        }
        boolean ok = businessDao.updatePassword(businessId, oldPassword, newPassword); // 执行更新
        System.out.println(ok ? ConsoleUi.success("密码更新成功。") : ConsoleUi.error("密码更新失败。"));
    }
}
