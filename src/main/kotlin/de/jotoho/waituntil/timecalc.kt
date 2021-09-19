package de.jotoho.waituntil

import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.TimeZone

fun calculateAndAnnounceTargetTime(userTimeInputRaw: String): ZonedDateTime {
        val userTimeInputRelative = LocalTime.parse(userTimeInputRaw)
        val userTimeInputAbsolute =
                        ZonedDateTime.of(
                                        LocalDate.now(),
                                        userTimeInputRelative,
                                        TimeZone.getDefault().toZoneId()
                        )

        val userTimeInputFinal =
                        if (Instant.now().isBefore(userTimeInputAbsolute.toInstant()))
                                        userTimeInputAbsolute
                        else userTimeInputAbsolute.plusDays(1)

        val formattedTimeStamp =
                        userTimeInputFinal.format(
                                        DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG)
                        )

        when (applicationOutputLanguage) {
                langGerman ->
                                System.err.println(
                                                "Dieses Program wird bis zum $formattedTimeStamp warten."
                                )
                else -> {
                        println("WaitUntil will suspend until $formattedTimeStamp")
                }
        }

        return userTimeInputFinal
}
