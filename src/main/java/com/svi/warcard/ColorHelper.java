package com.svi.warcard;

import java.util.Map;

public class ColorHelper {
    private static final Map<String, String> ANSI_COLORS = Map.of(
            "RED", "\u001B[31m",
            "GREEN", "\u001B[32m",
            "YELLOW", "\u001B[33m",
            "BLACK", "\u001B[30m",
            "BLUE", "\u001B[34m",
            "PURPLE", "\u001B[35m",
            "YELLOW_BG", "\u001B[43m",
            "GREEN_BG", "\u001B[42m",
            "RESET", "\u001B[0m"
    );

    public static String color(String text, String fontColor, String bgColor) {
        StringBuilder result = new StringBuilder();

        if (bgColor != null) {
            result.append(ANSI_COLORS.get(bgColor));
        }

        if (fontColor != null) {
            result.append(ANSI_COLORS.get(fontColor));
        }

        return result.append(text)
                .append(ANSI_COLORS.get("RESET"))
                .toString();
    }


}
