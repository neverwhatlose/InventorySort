package org.nwtls.inventorysort.sorting;

import cloud.commandframework.arguments.standard.StringArgument;
import cloud.commandframework.paper.PaperCommandManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Comparator;


public class SortCommand {
    public SortCommand(@NotNull PaperCommandManager<CommandSender> manager) {
        manager.command(manager
                .commandBuilder("inventorysort")
                .argument(StringArgument.<CommandSender>builder("type").asOptional())
                .senderType(Player.class)
                .handler(context -> {
                    this.handle(context.getSender(), context.getOrDefault("type", "full"));
                })
        );
    }

    public void handle(@NotNull CommandSender sender, @NotNull String type) {
        Player player = (Player) sender;
        ItemStack[] inventory = player.getInventory().getContents();
        ItemStack[] sortedInventory;
        if (!(type.equals("full") || type.equals("hotbar"))) {
            Component msg = Component.text("Invalid sort type: " + type)
                    .color(NamedTextColor.DARK_RED);
            player.sendMessage(msg);
            return;
        }
        if (type.equals("full")) {
            sortedInventory = sort(inventory);
            player.getInventory().clear();
        } else {
            sortedInventory = sort(Arrays.copyOfRange(inventory, 0, 9));
            for (ItemStack item : Arrays.copyOfRange(inventory, 0, 9)) {
                if (item == null) {
                    continue;
                }
                player.getInventory().remove(item);
            }
        }
        for (ItemStack item : sortedInventory) {
            if (item == null) {
                continue;
            }
            player.getInventory().addItem(item);
        }
        Component msg = Component.text("Successfully sorted inventory, type: " + type)
                .color(NamedTextColor.GREEN);
        player.sendMessage(msg);
    }

    public ItemStack[] sort(ItemStack[] items) {
        ItemStack[] sortedInventory = items.clone();
        Comparator<ItemStack> comparator = new ItemComparator();
        Arrays.sort(sortedInventory, comparator);
        return sortedInventory;
    }
}
