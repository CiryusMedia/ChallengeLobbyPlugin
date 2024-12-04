package com.ciryusmedia.challengenetwork.challengeslobbyplugin.scoreboard;

import com.ciryusmedia.challengenetwork.challengeslobbyplugin.ChallengesLobbyPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

@SuppressWarnings("deprecation")
public class TablistManager {

    //Header
    private String header(Player player) {
        return ChatColor.AQUA + "Welcome " + ChatColor.RESET + player.getName() + ChatColor.AQUA + " to the CCN!";
    }

    //Footer
    private String footer(Player player) {
//        return ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + "                   " + ChatColor.RESET + ""
//                + ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + "                   ";
        return " ";
    }

    public void setTablist(Player player) {
        player.setPlayerListHeaderFooter(header(player), footer(player));
    }

    public void run() {
        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.getOnlinePlayers().forEach(
                        player -> {
                            setTablist(player);
                        }
                );
            }
        }.runTaskTimer(ChallengesLobbyPlugin.getPlugin(ChallengesLobbyPlugin.class), 20, 20);
    }

    public TablistManager() {
        run();
    }

}
