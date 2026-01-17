package com.neusoft.elm.utils;

import java.math.BigDecimal;
import java.util.Scanner;

/* 控制台输入工具 */
public final class InputUtil {
    private static final Scanner SCANNER = new Scanner(System.in);

    private InputUtil() {
    }

    /* 读取一行文本 */
    public static String readLine(String prompt) {
        System.out.print(ConsoleUi.prompt(prompt)); // 输出提示
        return SCANNER.nextLine().trim();
    }

    /* 读取整数，失败则重复提示 */
    public static int readInt(String prompt) {
        while (true) {
            String text = readLine(prompt); // 读取输入
            try {
                return Integer.parseInt(text);
            } catch (NumberFormatException ex) {
                System.out.println(ConsoleUi.warn("输入无效，请输入数字。"));
            }
        }
    }

    /* 读取金额等数值 */
    public static BigDecimal readBigDecimal(String prompt) {
        while (true) {
            String text = readLine(prompt); // 读取输入
            try {
                return new BigDecimal(text);
            } catch (NumberFormatException ex) {
                System.out.println(ConsoleUi.warn("输入无效，请输入数字。"));
            }
        }
    }
}
