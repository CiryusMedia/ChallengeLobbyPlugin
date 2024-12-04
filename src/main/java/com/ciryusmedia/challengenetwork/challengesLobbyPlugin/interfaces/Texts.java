package com.ciryusmedia.challengenetwork.challengesLobbyPlugin.interfaces;

import org.bukkit.ChatColor;

@SuppressWarnings("deprecation")
public interface Texts {

    String STARTUP_LOGO = "\n" + ChatColor.AQUA +
            "   _____  _                            __  __            _  _        \n" +
            "  / ____|(_)                          |  \\/  |          | |(_)       \n" +
            " | |      _  _ __  _   _  _   _  ___  | \\  / |  ___   __| | _   __ _ \n" +
            " | |     | || '__|| | | || | | |/ __| | |\\/| | / _ \\ / _` || | / _` |\n" +
            " | |____ | || |   | |_| || |_| |\\__ \\ | |  | ||  __/| (_| || || (_| |\n" +
            "  \\_____||_||_|    \\__, | \\__,_||___/ |_|  |_| \\___| \\__,_||_| \\__,_|\n" +
            "                    __/ |                                            \n" +
            "                   |___/                                             \n";

    String PREFIX = ChatColor.AQUA + "Challenges" + ChatColor.DARK_GRAY + "» " + ChatColor.RESET;

    String INVALID_ARGUMENTS = ChatColor.RED + "Wrong arguments!";
    String INVALID_CHALLENGE = ChatColor.RED + "This is not a challenge!";

    String NO_PERMISSION = ChatColor.RED + "Your do not have permission to use this command!";

    String NOT_ENOUGH_ARGUMENTS = ChatColor.RED + "Not enough arguments!";
    String NOT_PLAYER = ChatColor.RED + "This command can only be used by a player!";

    String TOO_MANY_ARGUMENTS = ChatColor.ITALIC + "" + ChatColor.YELLOW + "Too many arguments!";

}
