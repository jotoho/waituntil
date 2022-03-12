package de.jotoho.waituntil;

/*
    waituntil - a tool for delaying command execution until the specified time
    Copyright (C) 2022 Jonas Tobias Hopusch

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;
import static java.lang.System.Logger.Level;

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
        System.getLogger("timecalculator").log(Level.INFO, msg);

        return userTimeInputFinal;
    }
}
