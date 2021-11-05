package net.thundercode.block.inventory;

import net.thundercode.block.cache.ItemChangeCache;
import net.thundercode.block.data.Configuration;
import net.thundercode.block.object.ItemChange;
import net.thundercode.block.util.ChatUtil;
import net.thundercode.block.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

public class BlockInventory {

    private final Inventory inventory;

    public BlockInventory(final Configuration configuration, final ItemChangeCache itemChangeCache){
        this.inventory = Bukkit.createInventory(null, configuration.inventorySize, ChatUtil.colour(configuration.inventoryName));

        if (configuration.inventoryFiller){
            for (int i = 0; i < configuration.inventorySize; i++){
                this.inventory.setItem(i, configuration.inventoryFillerItem);
            }
        }

        for (final ItemChange itemChange : itemChangeCache.getCache().values()){
            if (itemChange.getSlot() > configuration.inventorySize){
                throw new NullPointerException("itemChange slot is bigger than inventory size!");
            }

            final ItemBuilder item = new ItemBuilder(itemChange.getItem());

            item.setName(itemChange.getName());
            item.setLore(itemChange.getLore());

            this.inventory.setItem(itemChange.getSlot(), item.getItem());
        }
    }

    public Inventory getInventory() {
        return inventory;
    }
}
