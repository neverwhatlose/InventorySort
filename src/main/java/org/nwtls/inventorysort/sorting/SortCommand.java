package org.nwtls.inventorysort.sorting;

import cloud.commandframework.paper.PaperCommandManager;
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
                .senderType(Player.class)
                .handler(context -> {
                    this.handle(context.getSender());
                })
        );
    }

    public void handle(@NotNull CommandSender sender) {
        Player player = (Player) sender;
        ItemStack[] sortedInventory = sort(player.getInventory().getContents());
        player.getInventory().clear();
        for (ItemStack item : sortedInventory) {
            if (item == null) {
                continue;
            }
            player.getInventory().addItem(item);
        }
    }

    public ItemStack[] sort(ItemStack[] items) {
        ItemStack[] sortedInventory = items.clone();
        Comparator<ItemStack> comparator = new ItemComparator();
        Arrays.sort(sortedInventory, comparator);
        return sortedInventory;
    }
}
