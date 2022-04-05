package Helpers;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtility {
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSSSSSS xxx";
    public TimeUtility() {}
    public static String getCurrentUTCTimestamp() {
        Timestamp ts = Timestamp.valueOf(LocalDateTime.now());
        LocalDateTime ldt = ts.toLocalDateTime();
        ZonedDateTime zdt = ldt.atZone(ZoneId.of(ZoneId.systemDefault().toString()));
        ZonedDateTime utczdt = zdt.withZoneSameInstant(ZoneId.of("UTC"));
        return DateTimeFormatter.ofPattern(DATE_FORMAT).format(utczdt);
    }
}
