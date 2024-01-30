package likelion.univ.common.processor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateCustomFormatter {
    public static String format(LocalDateTime dateTime, String pattern) {
        return dateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String format(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy. M. d"));
    }
}
