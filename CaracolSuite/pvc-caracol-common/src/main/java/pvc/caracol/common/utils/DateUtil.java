package pvc.caracol.common.utils;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class DateUtil {
    public static LocalDate convertirToLocalDate(Date date) {
        return (date != null) ? Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate() : null;
    }
}
