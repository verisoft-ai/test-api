package co.verisoft.fw.xray;

import lombok.ToString;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@ToString
public abstract class XrayJsonFormat {
    public static String asXrayDateTime(ZonedDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssX");
        return date.format(formatter);
    }

}
