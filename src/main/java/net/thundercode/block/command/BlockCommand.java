package net.thundercode.block.command;

import net.thundercode.block.inventory.BlockInventory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BlockCommand implements CommandExecutor {

    private final BlockInventory blockInventory;

    public BlockCommand(final BlockInventory blockInventory){
        this.blockInventory = blockInventory;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            ((Player) sender).openInventory(blockInventory.getInventory());
        }
        return true;
    }
}
