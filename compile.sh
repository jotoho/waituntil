#!/bin/sh

# shellcheck disable=SC2046
# Word splitting in find results is intentional!
kotlinc $(find src/main -type f -iname '*.kt') -jvm-target 16 -include-runtime -d waituntil.jar
