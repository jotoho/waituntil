#!/bin/sh

# shellcheck disable=SC2046
# Word splitting in find results is intentional!
kotlinc $(find src/main -type f -iname '*.kt') -jvm-target 17 -include-runtime -d waituntil.jar
