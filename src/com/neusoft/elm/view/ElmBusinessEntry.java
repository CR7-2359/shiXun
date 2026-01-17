package com.neusoft.elm.view;

import com.neusoft.elm.utils.ConsoleUi;
import com.neusoft.elm.view.impl.BusinessView;

public class ElmBusinessEntry {
    public static void main(String[] args) {
        printBanner();
        new BusinessView().start();
    }

    private static void printBanner() {
        ConsoleUi.printBanner("饿了么商家后台 - 商家端", "学号姓名: 202408764423LiuXuhui");
    }

}
