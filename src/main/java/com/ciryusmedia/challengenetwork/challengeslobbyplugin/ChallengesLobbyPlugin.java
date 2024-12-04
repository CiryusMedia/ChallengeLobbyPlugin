package com.ciryusmedia.challengenetwork.challengeslobbyplugin;

import com.ciryusmedia.challengenetwork.challengeslobbyplugin.interfaces.Debuglevel;

import com.ciryusmedia.challengenetwork.challengeslobbyplugin.interfaces.Texts;
import com.ciryusmedia.challengenetwork.challengeslobbyplugin.scoreboard.TablistManager;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("deprecation")
public final class ChallengesLobbyPlugin extends JavaPlugin {

    public static int debugLevel;

    private static ChallengesLobbyPlugin instance;

    private TablistManager tablistManager;

    @Override
    public void onLoad() {
        log("Loading Ciryus Challenge Plugin version " + getDescription().getVersion(), Debuglevel.LEVEL_0);

        instance = this;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        log("Enabling Ciryus Challenge Plugin version " + getDescription().getVersion(), Debuglevel.LEVEL_0);

        tablistManager = new TablistManager();

        log(ChatColor.RESET + Texts.STARTUP_LOGO, Debuglevel.LEVEL_0);
        log("Challenge Plugin Loaded and Enabled", Debuglevel.LEVEL_0);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void log(String message, int messageDebuglevel) {
        ChatColor color = Debuglevel.debugColor(messageDebuglevel);
        if (messageDebuglevel <= debugLevel) {
            getServer().getConsoleSender().sendMessage(Texts.PREFIX + color + message);
        }
    }
}
