package pvc.caracol.common.utils;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class DateUtil {
    public static LocalDate convertirToLocalDate(Date date) {
        return (date != null) ? Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate() : null;
    }

    public static Date convertirToDate(LocalDateTime localDate) {
        return Date.valueOf(localDate.toLocalDate());
    }

    public static Date convertirToDate(LocalDate localDate) {
        return Date.valueOf(localDate);
    }

    public static Timestamp convertirToDateTime(LocalDateTime localDate) {
        return Timestamp.valueOf(localDate);
    }
}
