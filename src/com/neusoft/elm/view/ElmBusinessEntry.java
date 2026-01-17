package com.neusoft.elm.view;

import com.neusoft.elm.utils.ConsoleUi;
import com.neusoft.elm.view.impl.BusinessView;

/* 商家端入口 */
public class ElmBusinessEntry {
    public static void main(String[] args) {
        printBanner(); // 打印商家端标题
        new BusinessView().start(); // 启动商家菜单
    }

    /* 打印商家端标题 */
    private static void printBanner() {
        ConsoleUi.printBanner("饿了么商家后台 - 商家端", "学号姓名: 202408764423LiuXuhui");
    }

}
