package com.apeng.anticounterfeitbackend.util;

import java.awt.*;

public class ColorUtils {
    /**
     * Convert a Color to a hex string like "#00FF7F".
     *
     * @param color the Color to convert
     * @return hex string in the form "#RRGGBB"
     */
    public static String toHex(Color color) {
        return String.format(
                "#%02X%02X%02X",
                color.getRed(),
                color.getGreen(),
                color.getBlue()
        );
    }
}
