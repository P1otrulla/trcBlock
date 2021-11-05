package net.thundercode.block.object;

import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ItemChange {


    private final int           slot;
    private final String        changed,
                                noItem,
                                name;
    private final ItemStack     item,
                                from,
                                to;
    private final List<String>  lore;

    public ItemChange(int slot, ItemStack item, ItemStack from, ItemStack to, String name, String noItem, String changed, List<String> lore) {
        this.slot = slot;
        this.item = item;
        this.from = from;
        this.to = to;
        this.name = name;
        this.noItem = noItem;
        this.changed = changed;
        this.lore = lore;
    }


    public int getSlot() {
        return slot;
    }

    public ItemStack getItem() {
        return item;
    }

    public ItemStack getFrom() {
        return from;
    }

    public ItemStack getTo() {
        return to;
    }

    public String getName() {
        return name;
    }

    public String getNoItem() {
        return noItem;
    }

    public String getChanged() {
        return changed;
    }

    public List<String> getLore() {
        return lore;
    }
}
