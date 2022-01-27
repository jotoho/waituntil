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

import java.util.HashSet;
import java.util.Map;

import static de.jotoho.waituntil.GlobalConf.applicationOutputLanguage;

// This file contains the main function and other utility function necessary for interpreting the terminal arguments.
// See README.md and LICENSE.md for license information
// Author: Jonas Tobias Hopusch (@jotoho)

public final class Main {
    public static void main(final String[] args) {
        final var optionDictionary = Map.of("-h", "--help", "-v", "--version");

        final var options = new HashSet<String>();
        final var words = new HashSet<String>();

        for (final String arg : args) {
            if (arg.startsWith("--")) {
                options.add(arg.substring(2));
            } else if (arg.startsWith("-")) {
                if (optionDictionary.containsKey(arg))
                    options.add(optionDictionary.get(arg).substring(2));
                else
                    System.err.println("Short-hand '$arg' does not exist. Ignoring!");
            } else {
                words.add(arg);
            }
        }

        if (options.contains("help")) {
            switch (applicationOutputLanguage) {
                case GlobalConf.langGerman -> System.out.println("Hilfe kommt noch. (Nicht implementiert)");
                default -> System.out.println("Help is yet to come. (Not implemented)");
            }
        } else if (options.contains("version")) {
            final var thisPackage = Main.class.getPackage();
            final var appVersion = thisPackage.getImplementationVersion() != null
                    ? thisPackage.getImplementationVersion()
                    : "version unknown";
            System.out.println("waituntil " + appVersion);
            System.out.println("""

                    This program is free software: you can redistribute it and/or modify it under the terms of the
                    GNU General Public License as published by the Free Software Foundation, either version 3 of the
                    License, or (at your option) any later version.

                    This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
                    without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
                    See the GNU General Public License for more details.""");
        } else if (words.size() == 1) {
            final var target = TimeCalculator.calculateAndAnnounceTargetTime(words.iterator().next());
            Sleep.waitUntilTimeStamp(target);
        } else {
            switch (applicationOutputLanguage) {
                case GlobalConf.langGerman -> System.err.println("FATAL: Es wurde exact ein nicht-flag Argument erwartet. (" + words.size() + " erhalten)");
                default -> System.err.println("FATAL: Expected one non-flag argument. (Got " + words.size() + ")");
            }
            System.exit(1);
        }
    }
}
