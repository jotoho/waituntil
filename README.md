# waituntil

A basic tool for delaying work in the terminal to a specific time. This software was originally
written in Kotlin and then recently ported to Java.

Usage:

```sh
java -jar ./waituntil.jar 10:30 ; reboot # Replace reboot with your command
```

The example above would make the program wait until half past ten AM before exiting and allowing
the next command to run. The timestamp can be passed in the formats `HH:MM` or `HH:MM:SS` and must
be in the 24-hour system. Passing dates is not supported but entering a time that has already passed
will make the software wait until that time on the following day.

## Contributing / Submitting Feedback

The [canonical home of this project](https://gitea.jotoho.de/jotoho/waituntil/) is 
[on my personal Gitea-instance](https://gitea.jotoho.de/) but since it has registration
turned off, collaborating there is difficult.

If you have not been given an account on [gitea.jotoho.de](https://gitea.jotoho.de/)
then you can alternatively send me feedback, bug reports or patches [via email to
contact@jotoho.de](mailto:contact@jotoho.de).
Should that fail, there may be alternative ways to contact me listed on
[my personal website.](https://www.jotoho.de/)

In the future, I may also decide to create a mirror for this project on GitHub, gitlab.com or similar.

## Copyright / Licensing

```
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
```
