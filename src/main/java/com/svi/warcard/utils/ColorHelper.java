/**
 * Utility for adding color to console display
 *
 * @author Sheryn Ann Cagulada
 * @version October 30, 2026
 */

package com.svi.warcard.utils;

import java.util.Map;

public final class ColorHelper {
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

    /**
     * Builds a string with the specified font and background colors.
     *
     * @param text the text to be colored
     * @param fontColor the font color to apply
     * @param bgColor the background color to apply
     * @return the formatted string with the specified colors
     */
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

    /**
     * Builds a string with the specified font color.
     *
     * @param text the text to be colored
     * @param fontColor the font color to apply
     * @return the formatted string with the specified color
     */
    public static String color(String text, String fontColor) {
        return color(text, fontColor, null);
    }
}
