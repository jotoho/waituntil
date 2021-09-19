#!/bin/sh

kotlinc app/src/main/kotlin/de/jotoho/waituntil/*.kt -jvm-target 17 -include-runtime -d waituntil.jar
