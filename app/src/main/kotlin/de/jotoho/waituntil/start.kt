package de.jotoho.waituntil

// This file contains the main function and other utility function necessary for interpreting the terminal arguments.
// See README.md and LICENSE.md for license information
// Author: Jonas Tobias Hopusch (@jotoho)

fun main(args: Array<String>) {
    val optionDictionary = mapOf(Pair("-h", "--help"));

    val options = HashSet<String>();
    val words = HashSet<String>();

    for (arg in args) {
        if (arg.startsWith("--")) {
            options.add(arg.substring(startIndex=2))
        }
        else if (arg.startsWith('-')) {
            val translation = optionDictionary.get(arg);
            if (translation != null)
                options.add(translation.substring(startIndex=2));
            else
                System.err.println("Short-hand '$arg' does not exist. Ignoring!");
        }
        else
            words.add(arg);
    }

    println("Content of options:");
    println(options.toString());
    println("Content of words:");
    println(words.toString());
}
