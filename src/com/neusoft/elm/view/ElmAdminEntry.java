package com.neusoft.elm.view;

import com.neusoft.elm.utils.ConsoleUi;
import com.neusoft.elm.view.impl.AdminView;

/* 管理员端入口 */
public class ElmAdminEntry {
    public static void main(String[] args) {
        printBanner(); // 打印管理员端标题
        new AdminView().start(); // 启动管理员菜单
    }

    /* 打印管理员端标题 */
    private static void printBanner() {
        ConsoleUi.printBanner("饿了么商家后台 - 管理员端", "学号姓名: 202408764423LiuXuhui");
    }

}
