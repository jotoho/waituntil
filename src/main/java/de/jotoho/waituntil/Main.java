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

import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.ParseException;

import static de.jotoho.waituntil.GlobalConf.applicationOutputLanguage;

// This file contains the main function and other utility function necessary for interpreting the terminal arguments.
// See README.md and LICENSE.md for license information
// Author: Jonas Tobias Hopusch (@jotoho)

public final class Main {

    private static void printVersionInformation() {
        final var thisPackage = Main.class.getPackage();
        final var appVersion = thisPackage.getImplementationVersion() != null ? thisPackage.getImplementationVersion() : "version unknown";
        System.out.println("waituntil " + appVersion);
        System.out.println("""
                Project Repository: https://gitea.jotoho.de/jotoho/waituntil

                This program is free software: you can redistribute it and/or modify it under the terms of the
                GNU General Public License as published by the Free Software Foundation, either version 3 of the
                License, or (at your option) any later version.

                This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
                without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
                See the GNU General Public License for more details.""");
    }

    private static void printHelpInformation() {
        switch (applicationOutputLanguage) {
            case GlobalConf.langGerman -> System.out.println("Hilfe kommt noch. (Nicht implementiert)");
            default -> System.out.println("Help is yet to come. (Not implemented)");
        }
    }

    public static void main(final String[] args) {
        try {
            final var parsedArguments =
                DefaultParser.builder()
                    .setStripLeadingAndTrailingQuotes(true)
                    .build()
                    .parse(AppOptions.getOptions(), args);

            final var userData = parsedArguments.getArgs();

            if (parsedArguments.hasOption(AppOptions.help)) {
                printHelpInformation();
            } else if (parsedArguments.hasOption(AppOptions.version)) {
                printVersionInformation();
            } else if (userData.length == 0) {
                switch (applicationOutputLanguage) {
                    case GlobalConf.langGerman -> System.err.println("FATAL: " +
                        "Es wurde keine Uhrzeit angegeben.");
                    default -> System.err.println("FATAL: No target time was " +
                        "provided.");
                }
                System.exit(1);
            } else if (userData.length > 1) {
                switch (applicationOutputLanguage) {
                    case GlobalConf.langGerman -> System.err.println("FATAL: " +
                        "Zu viele Argumente wurden angegeben.");
                    default -> System.err.println("FATAL: Too many arguments " +
                        "provided.");
                }
                System.exit(1);
            } else {
                final var target =
                    TimeCalculator.calculateAndAnnounceTargetTime(userData[0]);
                Sleep.waitUntilTimeStamp(target);
            }
        } catch (final ParseException e) {
            System.getLogger("main").log(System.Logger.Level.ERROR, "Parsing " +
                "of arguments failed and the program cannot continue.", e);
            System.exit(1);
        }
    }
}
