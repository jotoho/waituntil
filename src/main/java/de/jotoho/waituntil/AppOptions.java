package de.jotoho.waituntil;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

final class AppOptions {
    public final static Option help = Option.builder().argName("h").longOpt("help").desc("Shows this help " + "message and exits").build();
    public final static Option version = Option.builder().argName("v").longOpt("version").desc("Shows version information and exits").build();
    private final static Options options = new Options().addOption(help).addOption(version);

    // Disable Instance Creation
    private AppOptions() {}

    public static Options getOptions() {
        return options;
    }
}
