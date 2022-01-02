package de.jotoho.waituntil;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.*;

import java.lang.Math;

public final class Sleep {
    public static void waitUntilTimeStamp(ZonedDateTime timestamp) {
        try {
            Thread.sleep(Math.max(0, Instant.now().until(timestamp, ChronoUnit.MILLIS)));
        } catch (final InterruptedException ignored) {
        }

        final String formattedTimeStamp =
                DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG)
                        .withZone(TimeZone.getDefault().toZoneId())
                        .format(Instant.now());

        final String msg = switch (GlobalConf.applicationOutputLanguage) {
            case GlobalConf.langGerman -> "Erfolgreich bis %s gewartet!";
            default -> "Successfully waited until %s";
        };

        final String msgWithData = msg.formatted(formattedTimeStamp);
        System.err.println(msgWithData);
    }
}
