package com.neusoft.elm.utils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/* CSV writing utilities */
public final class CsvUtil {
    private CsvUtil() {
    }

    /* Write rows to CSV using UTF-8 with BOM */
    public static void writeCsv(Path path, List<String[]> rows) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            writer.write('\uFEFF'); // UTF-8 BOM for Excel
            for (String[] row : rows) {
                writer.write(toLine(row)); // join row into CSV line
                writer.newLine();
            }
        }
    }

    /* Convert a row to a CSV line */
    private static String toLine(String[] row) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < row.length; i++) {
            if (i > 0) {
                sb.append(',');
            }
            sb.append(escape(row[i])); // escape each cell
        }
        return sb.toString();
    }

    /* Escape a CSV cell */
    private static String escape(String value) {
        if (value == null) {
            return "";
        }
        boolean needQuote = value.contains(",") || value.contains("\"")
                || value.contains("\n") || value.contains("\r");
        String escaped = value.replace("\"", "\"\"");
        return needQuote ? "\"" + escaped + "\"" : escaped;
    }
}
