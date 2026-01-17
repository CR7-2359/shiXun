package com.neusoft.elm.view;

import com.neusoft.elm.utils.ConsoleUi;
import com.neusoft.elm.utils.InputUtil;
import com.neusoft.elm.view.impl.AdminView;
import com.neusoft.elm.view.impl.BusinessView;

/* 程序主入口，负责选择管理员端或商家端 */
public class Main {
    public static void main(String[] args) {
        printBanner(); // 打印系统标题
        while (true) {
            printMainMenu(); // 展示主菜单
            int choice = InputUtil.readInt("请选择: "); // 读取用户选择
            switch (choice) {
                case 1:
                    new AdminView().start(); // 进入管理员端
                    break;
                case 2:
                    new BusinessView().start(); // 进入商家端
                    break;
                case 0:
                    System.out.println(ConsoleUi.label(ConsoleUi.ICON_EXIT, "已退出系统。")); // 退出系统
                    return;
                default:
                    System.out.println(ConsoleUi.warn("无效选项。")); // 输入不合法
                    break;
            }
        }
    }

    /* 打印系统标题栏 */
    private static void printBanner() {
        ConsoleUi.printBanner("饿了么商家后台系统", "学号姓名: 202408764423LiuXuhui");
    }

    /* 打印主菜单 */
    private static void printMainMenu() {
        System.out.println();
        System.out.println(ConsoleUi.label(ConsoleUi.ICON_MENU, "主菜单"));
        System.out.println("1. " + ConsoleUi.ICON_ADMIN + " 管理员端");
        System.out.println("2. " + ConsoleUi.ICON_BUSINESS + " 商家端");
        System.out.println("0. " + ConsoleUi.ICON_EXIT + " 退出");
    }
}
