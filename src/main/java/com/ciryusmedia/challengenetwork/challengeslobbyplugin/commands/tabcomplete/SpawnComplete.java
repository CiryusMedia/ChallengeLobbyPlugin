package com.ciryusmedia.challengenetwork.challengeslobbyplugin.commands.tabcomplete;

import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SpawnComplete implements TabCompleter {

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        List<String> list = new ArrayList<>();

        switch (strings.length) {
            case 1:
                list.add("set");
                break;

            case 2:
                list.add("~ ~ ~");
                break;

            case 3:
                list.add("~ ~");
                break;

            case 4:
                list.add("~");
                break;
        }

        if (commandSender instanceof Player player && player.getTargetBlockExact(5) != null) {
            Block block = player.getTargetBlockExact(5);
            assert block != null;

            switch (strings.length) {
                case 2:
                    list.add(block.getX() + " " + block.getY() + " " + block.getZ());
                    break;

                case 3:
                    list.add(block.getY() + " " + block.getZ());
                    break;

                case 4:
                    list.add(String.valueOf(block.getZ()));
                    break;
            }

        }

        return list;
    }

}
