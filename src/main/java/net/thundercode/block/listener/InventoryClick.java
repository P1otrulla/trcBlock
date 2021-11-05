package net.thundercode.block.listener;

import net.thundercode.block.cache.ItemChangeCache;
import net.thundercode.block.data.Configuration;
import net.thundercode.block.inventory.BlockInventory;
import net.thundercode.block.object.ItemChange;
import net.thundercode.block.util.BlockUtil;
import net.thundercode.block.util.ChatUtil;
import net.thundercode.block.util.ItemUtil;
import org.apache.commons.lang.StringUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;

public class InventoryClick implements Listener {

    private final ItemChangeCache   itemChangeCache;
    private final BlockInventory    blockInventory;
    private final Configuration     configuration;

    public InventoryClick(ItemChangeCache itemChangeCache, BlockInventory blockInventory, Configuration configuration) {
        this.itemChangeCache = itemChangeCache;
        this.blockInventory = blockInventory;
        this.configuration = configuration;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event){
        if (!event.getInventory().equals(blockInventory.getInventory())){
            return;
        }
        final Optional<ItemStack> itemStack = Optional.ofNullable(event.getCurrentItem());
        final Player player = (Player) event.getWhoClicked();

        event.setCancelled(true);

        if (itemStack.isPresent() && !itemStack.get().isSimilar(this.configuration.inventoryFillerItem)){
            for (final ItemChange itemChange : this.itemChangeCache.getCache().values()){
                if (itemStack.get().hasItemMeta() && itemStack.get().getItemMeta().hasDisplayName()){
                    if (itemStack.get().getItemMeta().getDisplayName().equalsIgnoreCase(ChatUtil.colour(itemChange.getName()))){
                        if (!ItemUtil.hasItem(player, itemChange.getFrom())){
                            ChatUtil.sendMessage(player, itemChange.getNoItem());
                            return;
                        }
                        final int[] ints = BlockUtil.changeItems(player, itemChange.getFrom(), itemChange.getTo());

                        ChatUtil.sendMessage(player, StringUtils.replaceEach(itemChange.getChanged(),
                                new String[]{ "{ITEMS}", "{BLOCKS}" },
                                new String[]{ String.valueOf(ints[0]), String.valueOf(ints[1])}));
                    }
                }
            }
        }
    }
}
