package com.neusoft.elm.view;

import com.neusoft.elm.utils.ConsoleUi;
import com.neusoft.elm.utils.InputUtil;
import com.neusoft.elm.view.impl.AdminView;
import com.neusoft.elm.view.impl.BusinessView;

public class Main {
    public static void main(String[] args) {
        printBanner();
        while (true) {
            printMainMenu();
            int choice = InputUtil.readInt("请选择: ");
            switch (choice) {
                case 1:
                    new AdminView().start();
                    break;
                case 2:
                    new BusinessView().start();
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

    private static void printBanner() {
        ConsoleUi.printBanner("饿了么商家后台系统", "学号姓名: 202408764423LiuXuhui");
    }

    private static void printMainMenu() {
        System.out.println();
        System.out.println(ConsoleUi.label(ConsoleUi.ICON_MENU, "主菜单"));
        System.out.println("1. " + ConsoleUi.ICON_ADMIN + " 管理员端");
        System.out.println("2. " + ConsoleUi.ICON_BUSINESS + " 商家端");
        System.out.println("0. " + ConsoleUi.ICON_EXIT + " 退出");
    }
}
