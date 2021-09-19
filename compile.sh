#!/bin/sh

kotlinc app/src/main/kotlin/de/jotoho/waituntil/*.kt -jvm-target 16 -include-runtime -d waituntil.jar
