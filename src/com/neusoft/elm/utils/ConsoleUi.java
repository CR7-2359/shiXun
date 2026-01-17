package com.neusoft.elm.utils;

public final class ConsoleUi {
    public static final String ICON_APP = "🍔";
    public static final String ICON_ADMIN = "🛠";
    public static final String ICON_BUSINESS = "🏪";
    public static final String ICON_MENU = "📋";
    public static final String ICON_EXIT = "🚪";
    public static final String ICON_PROMPT = "👉";
    public static final String ICON_INFO = "ℹ";
    public static final String ICON_SUCCESS = "✅";
    public static final String ICON_ERROR = "❌";
    public static final String ICON_WARN = "⚠";
    public static final String ICON_SEARCH = "🔍";
    public static final String ICON_LIST = "📃";
    public static final String ICON_ADD = "➕";
    public static final String ICON_EDIT = "✏";
    public static final String ICON_DELETE = "🗑";
    public static final String ICON_EXPORT = "📤";
    public static final String ICON_FOOD = "🍜";
    public static final String ICON_USER = "👤";
    public static final String ICON_WELCOME = "🎉";
    public static final String ICON_PASSWORD = "🔐";

    public static final String DIVIDER = "========================================";

    private ConsoleUi() {
    }

    public static String prompt(String text) {
        return ICON_PROMPT + " " + text;
    }

    public static String info(String text) {
        return ICON_INFO + " " + text;
    }

    public static String success(String text) {
        return ICON_SUCCESS + " " + text;
    }

    public static String error(String text) {
        return ICON_ERROR + " " + text;
    }

    public static String warn(String text) {
        return ICON_WARN + " " + text;
    }

    public static String label(String icon, String text) {
        return icon + " " + text;
    }

    public static void printBanner(String title, String student) {
        System.out.println(DIVIDER);
        System.out.println(label(ICON_APP, title));
        System.out.println(label(ICON_USER, student));
        System.out.println(DIVIDER);
    }
}
