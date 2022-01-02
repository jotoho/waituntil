package de.jotoho.waituntil;

import java.util.Locale;

public record GlobalConf() {
    public static final String langGerman = "de";
    public static final String applicationOutputLanguage = (Locale.getDefault().getLanguage().equals(Locale.GERMAN.getLanguage()))
                                             ? Locale.GERMAN.getLanguage()
                                             : Locale.ENGLISH.getLanguage();
}
