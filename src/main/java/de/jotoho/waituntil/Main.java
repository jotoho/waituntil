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

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import static de.jotoho.waituntil.GlobalConf.applicationOutputLanguage;
import static java.lang.System.Logger.Level;

// This file contains the main function and other utility function necessary
// for interpreting the terminal arguments.
// See README.md and LICENSE.md for license information
// Author: Jonas Tobias Hopusch (@jotoho)

public final class Main {
    private static final System.Logger logger = System.getLogger("main");

    private static void printVersionInformation() {
        final var thisPackage = Main.class.getPackage();
        final var
            appVersion =
            thisPackage.getImplementationVersion() != null
            ? thisPackage.getImplementationVersion()
            : "version unknown";

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
            case GlobalConf.langGerman -> logger.log(Level.ERROR,
                                                     "Hilfe " +
                                                     "kommt noch. (Nicht " +
                                                     "implementiert)");
            default -> logger.log(Level.ERROR,
                                  "Help is yet to come. (Not " +
                                  "implemented)");
        }
    }

    private static CommandLine parseArgs(final Options options,
                                         final String[] args)
        throws ParseException {
        return DefaultParser
            .builder()
            .setStripLeadingAndTrailingQuotes(true)
            .build()
            .parse(options, args);
    }

    public synchronized static void main(final String[] args) {
        // Retrieve defined CLI options
        final var appOptions = new AppOptions();

        try {
            // Parse CLI
            final var parsedArguments = parseArgs(appOptions.options, args);
            final var userData = parsedArguments.getArgs();

            // Differentiate between usage scenarios
            if (parsedArguments.hasOption(appOptions.help)) {
                printHelpInformation();
            } else if (parsedArguments.hasOption(appOptions.version)) {
                printVersionInformation();
            } else if (userData.length == 0) {
                switch (applicationOutputLanguage) {
                    case GlobalConf.langGerman -> logger.log(Level.ERROR,
                                                             "Es" +
                                                             " wurde keine " +
                                                             "Uhrzeit " +
                                                             "angegeben.");
                    default -> logger.log(Level.ERROR,
                                          "No target time was " + "provided.");
                }
                System.exit(1);
            } else if (userData.length > 1) {
                switch (applicationOutputLanguage) {
                    case GlobalConf.langGerman -> logger.log(Level.ERROR,
                                                             "Zu" + " viele " +
                                                             "Argumente " +
                                                             "wurden " +
                                                             "angegeben.");
                    default -> logger.log(Level.ERROR,
                                          "Too many arguments " + "provided.");
                }
                System.exit(1);
            } else {
                final var
                    target =
                    TimeCalculator.calculateAndAnnounceTargetTime(userData[0]);
                Sleep.waitUntilTimeStamp(target);
            }
        } catch (final ParseException e) {
            System
                .getLogger("main")
                .log(Level.ERROR,
                     "Parsing of arguments " +
                     "failed and the program cannot " + "continue.",
                     e);
            System.exit(1);
        }
    }
}
