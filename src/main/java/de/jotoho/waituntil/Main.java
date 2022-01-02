package de.jotoho.waituntil;

import java.util.*;

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
            final var appVersion = thisPackage.getImplementationVersion() != null ? thisPackage.getImplementationVersion() :"UNKNOWN";
            System.out.println("de.jotoho.waituntil version " + appVersion);
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
