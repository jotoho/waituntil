package de.jotoho.waituntil

import java.util.*
import kotlin.system.exitProcess

// This file contains the main function and other utility function necessary for interpreting the terminal arguments.
// See README.md and LICENSE.md for license information
// Author: Jonas Tobias Hopusch (@jotoho)

val langGerman: String = Locale.GERMAN.language
val applicationOutputLanguage: String = if (Locale.getDefault().language.equals(Locale.GERMAN.language))
                                    Locale.GERMAN.language
                                    else Locale.ENGLISH.language

fun main(args: Array<String>) {
    val optionDictionary = mapOf(Pair("-h", "--help"))

    val options = HashSet<String>()
    val words = HashSet<String>()

    for (arg in args) {
        if (arg.startsWith("--")) {
            options.add(arg.substring(startIndex=2))
        }
        else if (arg.startsWith('-')) {
            val translation = optionDictionary[arg]
            if (translation != null)
                options.add(translation.substring(startIndex=2))
            else
                System.err.println("Short-hand '$arg' does not exist. Ignoring!")
        }
        else
            words.add(arg)
    }

    if (options.contains("help")) {
        when (applicationOutputLanguage) {
            langGerman -> println("Hilfe kommt noch. (Nicht implementiert)")
            else -> {
                println("Help is yet to come. (Not implemented)")
            }
        }
    }
    else if (words.size == 1) {
        val target = calculateAndAnnounceTargetTime(words.iterator().next())
        waitUntilTimeStamp(target)
    }
    else {
        when (applicationOutputLanguage) {
            langGerman -> System.err.println("FATAL: Es wurde exact ein nicht-flag Argument erwartet. (${words.size} erhalten)")
            else -> {
                System.err.println("FATAL: Expected one non-flag argument. (Got ${words.size})")
            }
        }
        exitProcess(1)
    }
}
