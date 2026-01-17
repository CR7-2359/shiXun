package com.neusoft.elm.utils;

public final class ConsoleUi {
    // 图标常量（用于统一控制台风格）
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
    public static final String ICON_PREV = "⬅";
    public static final String ICON_NEXT = "➡";

    // 分隔线
    public static final String DIVIDER = "========================================";

    private ConsoleUi() {
    }

    /* 构造输入提示 */
    public static String prompt(String text) {
        return ICON_PROMPT + " " + text;
    }

    /* 信息提示 */
    public static String info(String text) {
        return ICON_INFO + " " + text;
    }

    /* 成功提示 */
    public static String success(String text) {
        return ICON_SUCCESS + " " + text;
    }

    /* 错误提示 */
    public static String error(String text) {
        return ICON_ERROR + " " + text;
    }

    /* 警告提示 */
    public static String warn(String text) {
        return ICON_WARN + " " + text;
    }

    /* 图标 + 文本拼接 */
    public static String label(String icon, String text) {
        return icon + " " + text;
    }

    /* 打印标题栏 */
    public static void printBanner(String title, String student) {
        System.out.println(DIVIDER);
        System.out.println(label(ICON_APP, title));
        System.out.println(label(ICON_USER, student));
        System.out.println(DIVIDER);
    }
}
