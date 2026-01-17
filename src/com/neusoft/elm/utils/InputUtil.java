package com.neusoft.elm.utils;

import java.math.BigDecimal;
import java.util.Scanner;

public final class InputUtil {
    private static final Scanner SCANNER = new Scanner(System.in);

    private InputUtil() {
    }

    public static String readLine(String prompt) {
        System.out.print(ConsoleUi.prompt(prompt));
        return SCANNER.nextLine().trim();
    }

    public static int readInt(String prompt) {
        while (true) {
            String text = readLine(prompt);
            try {
                return Integer.parseInt(text);
            } catch (NumberFormatException ex) {
                System.out.println(ConsoleUi.warn("输入无效，请输入数字。"));
            }
        }
    }

    public static BigDecimal readBigDecimal(String prompt) {
        while (true) {
            String text = readLine(prompt);
            try {
                return new BigDecimal(text);
            } catch (NumberFormatException ex) {
                System.out.println(ConsoleUi.warn("输入无效，请输入数字。"));
            }
        }
    }
}
