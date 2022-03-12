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

import java.util.Locale;

public record GlobalConf() {
    public static final String langGerman = "de";
    public static final String applicationOutputLanguage = (Locale.getDefault().getLanguage().equals(Locale.GERMAN.getLanguage())) ? Locale.GERMAN.getLanguage() : Locale.ENGLISH.getLanguage();
}
