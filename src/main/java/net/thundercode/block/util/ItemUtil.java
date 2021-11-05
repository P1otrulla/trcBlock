package net.thundercode.block.util;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public final class ItemUtil {

    public static boolean hasItem(final Player player, final ItemStack itemStack){
        return player.getInventory().containsAtLeast(itemStack, itemStack.getAmount());
    }

    public static void giveItem(final Player player, final ItemStack itemStack) {
        if (player.getInventory().firstEmpty() == -1){
            player.getWorld().dropItemNaturally(player.getLocation(), itemStack);
        }
        player.getInventory().addItem(itemStack);
    }

    private ItemUtil(){
    }
}

