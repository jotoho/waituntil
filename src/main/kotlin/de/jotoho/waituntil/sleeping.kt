package de.jotoho.waituntil

import java.time.Instant
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.time.temporal.ChronoUnit
import java.util.*

public fun waitUntilTimeStamp(timestamp: ZonedDateTime) {
    Thread.sleep(Math.max(Instant.now().until(timestamp, ChronoUnit.MILLIS), 0))

    val formattedTimeStamp: String =
            DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG)
                    .withZone(TimeZone.getDefault().toZoneId())
                    .format(Instant.now())

    when (applicationOutputLanguage) {
        langGerman -> System.err.println("Erfolgreich bis $formattedTimeStamp gewartet!")
        else -> {
            System.err.println("Successfully waited until $formattedTimeStamp")
        }
    }
}
