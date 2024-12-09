package com.ciryusmedia.challengenetwork.challengeslobbyplugin.commands;

import com.ciryusmedia.challengenetwork.challengeslobbyplugin.ChallengesLobbyPlugin;
import com.ciryusmedia.challengenetwork.challengeslobbyplugin.interfaces.Texts;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class SpawnCommand implements CommandExecutor, Texts {

    Plugin plugin = ChallengesLobbyPlugin.getPlugin(ChallengesLobbyPlugin.class);

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        switch (strings.length) {
            case 0:
                if (!(commandSender instanceof Player player)) {
                    commandSender.sendMessage(PREFIX + ChatColor.RED + "Not enough arguments");
                    break;
                }

                double spawnX = plugin.getConfig().getDouble("spawnX");
                double spawnY = plugin.getConfig().getDouble("spawnY");
                double spawnZ = plugin.getConfig().getDouble("spawnZ");
                Location spawn = new Location(player.getWorld(), spawnX, spawnY, spawnZ);

                player.teleport(spawn);
                break;

            case 1:

                if (!(commandSender instanceof Player player)) {
                    commandSender.sendMessage(PREFIX + ChatColor.RED + "Not enough arguments");
                    break;
                } else if (!strings[0].equalsIgnoreCase("set")) {
                    commandSender.sendMessage(PREFIX + ChatColor.RED + "Wrong arguments");
                    break;
                }

                int playerX = (int) player.getLocation().getX();
                int playerY = (int) player.getLocation().getY();
                int playerZ = (int) player.getLocation().getZ();

                plugin.getConfig().set("spawnX", playerX);
                plugin.getConfig().set("spawnY", playerY);
                plugin.getConfig().set("spawnZ", playerZ);
                plugin.saveConfig();

                player.sendMessage(PREFIX + "Spawn point set to " + playerX + " " + playerY + " " + playerZ);
                break;

            default: //Default because we don't care what comes after the coordinates, and we rule out everything that doesn't have enough arguments
                if (!strings[0].equalsIgnoreCase("set")) {
                    commandSender.sendMessage(PREFIX + ChatColor.RED + "Wrong arguments");
                    break;
                } else if (strings.length < 4) {
                    commandSender.sendMessage(PREFIX + ChatColor.RED + "Not enough arguments");
                    break;
                }

                if (hasRelativePosition(strings)) {
                    handleRelativePosition(commandSender, strings);
                } else {
                    handleAbsolutePosition(commandSender, strings);
                }
        }
        return true;
    }

    private void handleRelativePosition(@NotNull CommandSender commandSender, @NotNull String @NotNull [] strings) {
        if (commandSender instanceof Player player) {
            try {
                int relX = (int) posFromRelPos(strings[1], player.getX());
                int relY = (int) posFromRelPos(strings[2], player.getY());
                int relZ = (int) posFromRelPos(strings[3], player.getZ());

                plugin.getConfig().set("spawnX", relX + 0.5);
                plugin.getConfig().set("spawnY", relY);
                plugin.getConfig().set("spawnZ", relZ + 0.5);
                plugin.saveConfig();

                commandSender.sendMessage(PREFIX + "Spawn point set to " + relX + 0.5 + " " + relY + " " + relZ + 0.5);
            } catch (NumberFormatException e) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Something happened while converting relative coordinates");
                commandSender.sendMessage(PREFIX + ChatColor.RED + "Wrong arguments");
            }
        } else {
            commandSender.sendMessage(PREFIX + ChatColor.RED + "Wrong arguments");
        }
    }

    private void handleAbsolutePosition(@NotNull CommandSender commandSender, @NotNull String @NotNull [] strings) {
        try {
            int posX = (int) Double.parseDouble(strings[1]);
            int posY = (int) Double.parseDouble(strings[2]);
            int posZ = (int) Double.parseDouble(strings[3]);

            plugin.getConfig().set("spawnX", posX);
            plugin.getConfig().set("spawnY", posY);
            plugin.getConfig().set("spawnZ", posZ);
            plugin.saveConfig();

            commandSender.sendMessage(PREFIX + "Spawn point set to " + posX + 0.5 + " " + posY + " " + posZ + 0.5);
        } catch (NumberFormatException e) {
            commandSender.sendMessage(PREFIX + ChatColor.RED + "Wrong arguments");
        }
    }

    /**
     * Searches a string array for a relative position char (~)
     *
     * @param strings A string array to be searched
     * @return Whether the input contains a relative position
     */
    private boolean hasRelativePosition(String[] strings) {
        return Arrays.stream(strings).anyMatch(string -> string.contains("~"));
    }


    private boolean isValidRelativePosition(String string) {
        if (!string.contains("~")) {
            return false;
        }

        String p = string.replace("~", "");

        if (!p.isBlank()) {
            try {
                Double.parseDouble(p);
            } catch (NumberFormatException e) {
                return false;
            }
        }

        return true;
    }

    private double posFromRelPos(String string, double playerPos) throws NumberFormatException {
        String s = string.replace("~", "");
        if (isValidRelativePosition(string)) {

            int rel = 0;
            int playerPosTrimmed = (int) playerPos;

            if (!s.isBlank()) {
                rel = Integer.parseInt(s);
            }

            return playerPosTrimmed + rel;
        } else {
            return Integer.parseInt(s);
        }
    }
}
