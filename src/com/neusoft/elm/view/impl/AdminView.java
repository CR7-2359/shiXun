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

public class AdminView {
    private final AdminDao adminDao = new AdminDao();
    private final BusinessDao businessDao = new BusinessDao();

    public void start() {
        while (true) {
            System.out.println();
            System.out.println(ConsoleUi.label(ConsoleUi.ICON_MENU, "管理员端"));
            System.out.println("1. " + ConsoleUi.ICON_ADMIN + " 管理员登录");
            System.out.println("0. " + ConsoleUi.ICON_EXIT + " 退出");
            int choice = InputUtil.readInt("请选择: ");
            switch (choice) {
                case 1:
                    adminLoginFlow();
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

    private void adminLoginFlow() {
        String name = InputUtil.readLine("管理员账号: ");
        String password = InputUtil.readLine("密码: ");
        Admin admin = adminDao.login(name, password);
        if (admin == null) {
            System.out.println(ConsoleUi.error("登录失败。"));
            return;
        }
        System.out.println(ConsoleUi.label(ConsoleUi.ICON_WELCOME, "欢迎, " + admin.getName() + "。"));
        while (true) {
            printAdminMenu();
            int choice = InputUtil.readInt("请选择: ");
            switch (choice) {
                case 1:
                    searchBusinessFlow();
                    break;
                case 2:
                    listAllBusinessesFlow();
                    break;
                case 3:
                    addBusinessFlow();
                    break;
                case 4:
                    deleteBusinessFlow();
                    break;
                case 5:
                    exportBusinessesFlow();
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

    private void searchBusinessFlow() {
        String keyword = InputUtil.readLine("关键词: ");
        List<Business> businesses = businessDao.findByNameLike(keyword);
        printBusinesses(businesses);
    }

    private void listAllBusinessesFlow() {
        List<Business> businesses = businessDao.listAll();
        printBusinesses(businesses);
    }

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

    private void addBusinessFlow() {
        String account = InputUtil.readLine("账号: ");
        String password = InputUtil.readLine("密码: ");
        String name = InputUtil.readLine("名称: ");
        String phone = InputUtil.readLine("电话: ");
        String address = InputUtil.readLine("地址: ");
        String description = InputUtil.readLine("描述: ");

        Business business = new Business();
        business.setAccount(account);
        business.setPassword(password);
        business.setName(name);
        business.setPhone(phone);
        business.setAddress(address);
        business.setDescription(description);

        boolean ok = businessDao.add(business);
        System.out.println(ok ? ConsoleUi.success("商家创建成功。") : ConsoleUi.error("商家创建失败。"));
    }

    private void deleteBusinessFlow() {
        int id = InputUtil.readInt("要删除的商家编号: ");
        boolean ok = businessDao.deleteById(id);
        System.out.println(ok ? ConsoleUi.success("商家删除成功。") : ConsoleUi.error("商家删除失败。"));
    }

    private void exportBusinessesFlow() {
        String filePath = InputUtil.readLine("导出文件名(回车默认business.csv): ");
        if (filePath.isEmpty()) {
            filePath = "business.csv";
        }
        filePath = "csvData/" + filePath;
        List<Business> businesses = businessDao.listAll();
        List<String[]> rows = new ArrayList<>();
        rows.add(new String[]{"编号", "账号", "名称", "电话", "地址", "描述"});
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
