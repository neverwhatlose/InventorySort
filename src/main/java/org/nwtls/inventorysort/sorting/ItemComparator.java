package org.nwtls.inventorysort.sorting;

import org.bukkit.inventory.ItemStack;

import java.util.Comparator;

public class ItemComparator implements Comparator<ItemStack> {
    @Override
    public int compare(ItemStack item1, ItemStack item2) {
        String item1Type = item1 == null ? "" : item1.getType().toString();
        String item2Type = item2 == null ? "" : item2.getType().toString();

        return item1Type.compareTo(item2Type);
    }
}
