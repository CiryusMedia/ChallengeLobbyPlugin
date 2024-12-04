package com.ciryusmedia.challengenetwork.challengeslobbyplugin.commands;

import com.ciryusmedia.challengenetwork.challengeslobbyplugin.ChallengesLobbyPlugin;
import com.ciryusmedia.challengenetwork.challengeslobbyplugin.interfaces.Texts;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

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

                double playerX = player.getLocation().getX();
                double playerY = player.getLocation().getY();
                double playerZ = player.getLocation().getZ();

                plugin.getConfig().set("spawnX", playerX);
                plugin.getConfig().set("spawnY", playerY);
                plugin.getConfig().set("spawnZ", playerZ);
                plugin.saveConfig();

                player.sendMessage(PREFIX + "Spawn point set to " + playerX + " " + playerY + " " + playerZ);
                break;

            default: //Default because we don't care what comes after the coordinates and we rule out everything that doesn't have enough arguments
                if (!strings[0].equalsIgnoreCase("set") || strings.length < 4) {
                    commandSender.sendMessage(PREFIX + ChatColor.RED + "Wrong arguments");
                    break;
                }

                try {
                    double posX = Double.parseDouble(strings[1]);
                    double posY = Double.parseDouble(strings[2]);
                    double posZ = Double.parseDouble(strings[3]);

                    plugin.getConfig().set("spawnX", posX);
                    plugin.getConfig().set("spawnY", posY);
                    plugin.getConfig().set("spawnZ", posZ);
                    plugin.saveConfig();

                    commandSender.sendMessage(PREFIX + "Spawn point set to " + posX + " " + posY + " " + posZ);
                } catch (NumberFormatException e) {
                    commandSender.sendMessage(PREFIX + ChatColor.RED + "Wrong arguments");
                }
        }

        return true;
    }
}
