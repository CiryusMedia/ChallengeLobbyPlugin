package com.ciryusmedia.challengenetwork.challengeslobbyplugin.listeners.system;

import com.ciryusmedia.challengenetwork.challengeslobbyplugin.ChallengesLobbyPlugin;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class PlayerJoinLeaveListener implements Listener {

    Plugin plugin = ChallengesLobbyPlugin.getPlugin(ChallengesLobbyPlugin.class);

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        double spawnX = plugin.getConfig().getDouble("spawnX");
        double spawnY = plugin.getConfig().getDouble("spawnY");
        double spawnZ = plugin.getConfig().getDouble("spawnZ");
        Location spawn = new Location(player.getWorld(), spawnX, spawnY, spawnZ);

        player.getInventory().setChestplate(new ItemStack(Material.ELYTRA));
        player.getInventory().setItem(4, new ItemStack(Material.FIREWORK_ROCKET));

        player.setGameMode(GameMode.ADVENTURE);

        player.teleport(spawn);

        event.setJoinMessage(ChatColor.GREEN + "+ " + ChatColor.RESET + player.getDisplayName());
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {

        Player player = event.getPlayer();

        event.setQuitMessage(ChatColor.RED + "+ " + ChatColor.RESET + player.getDisplayName());
    }
}
