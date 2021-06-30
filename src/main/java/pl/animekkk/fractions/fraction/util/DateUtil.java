package pl.animekkk.fractions.fraction.util;

import java.text.SimpleDateFormat;

public class DateUtil {

    private static final SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm");

    public static String parse(long ms) {
        return format.format(ms);
    }

}
