package com.neusoft.elm.view.impl;

import com.neusoft.elm.dao.impl.AdminDao;
import com.neusoft.elm.dao.impl.BusinessDao;
import com.neusoft.elm.po.Admin;
import com.neusoft.elm.po.Business;
import com.neusoft.elm.utils.ConsoleUi;
import com.neusoft.elm.utils.CsvUtil;
import com.neusoft.elm.utils.InputUtil;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/* 管理员端界面与业务流程 */
public class AdminView {
    private final AdminDao adminDao = new AdminDao();
    private final BusinessDao businessDao = new BusinessDao();

    /* 管理员端入口菜单 */
    public void start() {
        while (true) {
            System.out.println();
            System.out.println(ConsoleUi.label(ConsoleUi.ICON_MENU, "管理员端"));
            System.out.println("1. " + ConsoleUi.ICON_ADMIN + " 管理员登录");
            System.out.println("0. " + ConsoleUi.ICON_EXIT + " 退出");
            int choice = InputUtil.readInt("请选择: "); // 读取菜单选择
            switch (choice) {
                case 1:
                    adminLoginFlow(); // 进入登录流程
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

    /* 管理员登录与功能菜单 */
    private void adminLoginFlow() {
        String name = InputUtil.readLine("管理员账号: "); // 读取账号
        String password = InputUtil.readLine("密码: "); // 读取密码
        Admin admin = adminDao.login(name, password); // 查询管理员
        if (admin == null) {
            System.out.println(ConsoleUi.error("登录失败。"));
            return;
        }
        System.out.println(ConsoleUi.label(ConsoleUi.ICON_WELCOME, "欢迎, " + admin.getName() + "。"));
        while (true) {
            printAdminMenu(); // 展示管理员菜单
            int choice = InputUtil.readInt("请选择: "); // 读取菜单选择
            switch (choice) {
                case 1:
                    searchBusinessFlow(); // 搜索商家
                    break;
                case 2:
                    listAllBusinessesFlow(); // 查看所有商家
                    break;
                case 3:
                    addBusinessFlow(); // 新增商家
                    break;
                case 4:
                    deleteBusinessFlow(); // 删除商家
                    break;
                case 5:
                    exportBusinessesFlow(); // 导出商家列表
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

    /* 打印管理员功能菜单 */
    private void printAdminMenu() {
        System.out.println();
        System.out.println(ConsoleUi.label(ConsoleUi.ICON_MENU, "管理员菜单"));
        System.out.println("1. " + ConsoleUi.ICON_SEARCH + " 搜索商家");
        System.out.println("2. " + ConsoleUi.ICON_LIST + " 查看所有商家");
        System.out.println("3. " + ConsoleUi.ICON_ADD + " 新增商家");
        System.out.println("4. " + ConsoleUi.ICON_DELETE + " 删除商家");
        System.out.println("5. " + ConsoleUi.ICON_EXPORT + " 导出商家列表");
        System.out.println("0. " + ConsoleUi.ICON_EXIT + " 退出登录");
    }

    /* 按关键字搜索商家 */
    private void searchBusinessFlow() {
        String keyword = InputUtil.readLine("关键词: "); // 读取搜索关键词
        List<Business> businesses = businessDao.findByNameLike(keyword); // 查询列表
        printBusinesses(businesses);
    }

    /* 查看所有商家 */
    private void listAllBusinessesFlow() {
        List<Business> businesses = businessDao.listAll(); // 查询全部商家
        printBusinesses(businesses);
    }

    /* 打印商家列表 */
    private void printBusinesses(List<Business> businesses) {
        if (businesses.isEmpty()) {
            System.out.println(ConsoleUi.info("暂无数据。"));
            return;
        }
        System.out.println(ConsoleUi.label(ConsoleUi.ICON_LIST, "商家列表"));
        System.out.printf("%-5s %-15s %-20s %-15s %-20s%n",
                "编号", "账号", "名称", "电话", "地址");
        for (Business business : businesses) {
            System.out.printf("%-5d %-15s %-20s %-15s %-20s%n",
                    business.getId(),
                    business.getAccount(),
                    business.getName(),
                    business.getPhone(),
                    business.getAddress());
        }
    }

    /* 新增商家 */
    private void addBusinessFlow() {
        String account = InputUtil.readLine("账号: "); // 读取账号
        String password = InputUtil.readLine("密码: "); // 读取密码
        String name = InputUtil.readLine("名称: "); // 读取商家名称
        String phone = InputUtil.readLine("电话: "); // 读取电话
        String address = InputUtil.readLine("地址: "); // 读取地址
        String description = InputUtil.readLine("描述: "); // 读取描述

        Business business = new Business();
        business.setAccount(account); // 设置账号
        business.setPassword(password); // 设置密码
        business.setName(name); // 设置名称
        business.setPhone(phone); // 设置电话
        business.setAddress(address); // 设置地址
        business.setDescription(description); // 设置描述

        boolean ok = businessDao.add(business); // 执行新增
        System.out.println(ok ? ConsoleUi.success("商家创建成功。") : ConsoleUi.error("商家创建失败。"));
    }

    /* 删除商家 */
    private void deleteBusinessFlow() {
        int id = InputUtil.readInt("要删除的商家编号: "); // 读取要删除的编号
        boolean ok = businessDao.deleteById(id); // 执行删除
        System.out.println(ok ? ConsoleUi.success("商家删除成功。") : ConsoleUi.error("商家删除失败。"));
    }

    /* 导出商家列表到 CSV */
    private void exportBusinessesFlow() {
        String filePath = InputUtil.readLine("导出文件名(回车默认business.csv): "); // 读取导出文件名
        if (filePath.isEmpty()) {
            filePath = "business.csv";
        }
        filePath = "csvData/" + filePath;
        List<Business> businesses = businessDao.listAll(); // 获取商家列表
        List<String[]> rows = new ArrayList<>();
        rows.add(new String[]{"编号", "账号", "名称", "电话", "地址", "描述"}); // 表头
        for (Business business : businesses) {
            rows.add(new String[]{
                    String.valueOf(business.getId()),
                    business.getAccount(),
                    business.getName(),
                    business.getPhone(),
                    business.getAddress(),
                    business.getDescription()
            });
        }
        try {
            CsvUtil.writeCsv(Path.of(filePath), rows);
            System.out.println(ConsoleUi.success("导出成功: " + filePath));
        } catch (Exception ex) {
            System.out.println(ConsoleUi.error("导出失败: " + ex.getMessage()));
        }
    }
}
