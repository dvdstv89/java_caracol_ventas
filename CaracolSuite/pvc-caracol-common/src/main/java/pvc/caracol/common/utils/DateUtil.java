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

    public static Date convertirLocalDateTimeToDate(LocalDateTime localDate) {
        return Date.valueOf(localDate.toLocalDate());
    }

    public static Date convertirLocalDateToDate(LocalDate localDate) {
        return Date.valueOf(localDate);
    }

    public static Timestamp convertirLocalDateTimeToTimestamp(LocalDateTime localDate) {
        return Timestamp.valueOf(localDate);
    }
    public static Timestamp convertirLocalDateToTimestamp(LocalDate localDate) {
        return Timestamp.valueOf(localDate.atStartOfDay());
    }
}
