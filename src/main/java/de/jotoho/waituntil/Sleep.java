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
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.TimeZone;

import static java.lang.System.Logger.Level;

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
        System.getLogger("sleep").log(Level.INFO, msgWithData);
    }
}
