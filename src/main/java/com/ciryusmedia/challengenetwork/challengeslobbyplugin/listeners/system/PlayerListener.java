package com.ciryusmedia.challengenetwork.challengeslobbyplugin.listeners.system;

import com.ciryusmedia.challengenetwork.challengeslobbyplugin.ChallengesLobbyPlugin;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.event.player.PlayerAnimationType;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.Objects;

public class PlayerListener implements Listener {

    Plugin plugin = ChallengesLobbyPlugin.getPlugin(ChallengesLobbyPlugin.class);

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {

        if (!(event.getEntity() instanceof Player player)) {
            return;
        }

        //If the player gets damaged by the void
        if (player.getLocation().getY() < -64) {
            double spawnX = plugin.getConfig().getDouble("spawnX");
            double spawnY = plugin.getConfig().getDouble("spawnY");
            double spawnZ = plugin.getConfig().getDouble("spawnZ");
            Location spawnLocation = new Location(player.getWorld(), spawnX, spawnY, spawnZ, 0, 0);

            player.teleport(spawnLocation);
        }

        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerRocket(PlayerAnimationEvent event) {
        PlayerAnimationType type = event.getAnimationType();
        Player player = event.getPlayer();

        if (type.equals(PlayerAnimationType.ARM_SWING)) {
            if (player.getInventory().getItem(4) == null) {
                player.getInventory().setItem(4, new ItemStack(Material.FIREWORK_ROCKET));
            }
        }
    }

    @EventHandler
    public void onItemDamage(PlayerItemDamageEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (player.getGameMode().equals(GameMode.CREATIVE)) {
            return;
        }

        if (Objects.equals(event.getClickedInventory(), player.getInventory())) {
            event.setCancelled(true);
        }
    }

}
