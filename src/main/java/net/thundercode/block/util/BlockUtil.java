package net.thundercode.block.util;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public final class BlockUtil {

    public static int[] changeItems(final Player player, final ItemStack from, final ItemStack to){
        if (!ItemUtil.hasItem(player, from)){
            return new int[] { 0, 0 };
        }
        int fromAmount = 0;
        int toAmount = 0;

        while (ItemUtil.hasItem(player, from)){
            if (!ItemUtil.hasItem(player, from)){
                continue;
            }
            fromAmount = fromAmount + from.getAmount();
            toAmount++;
            player.getInventory().removeItem(from);
            ItemUtil.giveItem(player, to);
        }
        return new int[] { fromAmount, toAmount };
    }

    private BlockUtil(){
    }
}
