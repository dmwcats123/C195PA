package Helpers;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtility {
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public TimeUtility() {}

    public static String getCurrentUTCTimestamp() {
        return ZonedDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ofPattern(DATE_FORMAT)).toString();
    }
}
