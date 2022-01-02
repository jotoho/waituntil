package de.jotoho.waituntil;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;

public final class TimeCalculator {

    public static ZonedDateTime calculateAndAnnounceTargetTime(final String userTimeInputRaw) {
        final var userTimeInputRelative = LocalTime.parse(userTimeInputRaw);
        final var userTimeInputAbsolute =
                ZonedDateTime.of(
                        LocalDate.now(),
                        userTimeInputRelative,
                        TimeZone.getDefault().toZoneId()
                );

        final var userTimeInputFinal = (Instant.now().isBefore(userTimeInputAbsolute.toInstant()))
                                                ? userTimeInputAbsolute
                                                : userTimeInputAbsolute.plusDays(1);

        final var formattedTimeStamp =
                userTimeInputFinal.format(
                        DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG)
                );

        final String msg = switch (GlobalConf.applicationOutputLanguage) {
            case GlobalConf.langGerman -> "Dieses Program wird bis zum %s warten."
                                            .formatted(formattedTimeStamp);
            default -> "WaitUntil will suspend until %s".formatted(formattedTimeStamp);
        };
        System.out.println(msg);

        return userTimeInputFinal;
    }
}
