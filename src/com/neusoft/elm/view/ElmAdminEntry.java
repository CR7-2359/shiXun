package com.neusoft.elm.view;

import com.neusoft.elm.utils.ConsoleUi;
import com.neusoft.elm.view.impl.AdminView;

public class ElmAdminEntry {
    public static void main(String[] args) {
        printBanner();
        new AdminView().start();
    }

    private static void printBanner() {
        ConsoleUi.printBanner("饿了么商家后台 - 管理员端", "学号姓名: 202408764423LiuXuhui");
    }

}
