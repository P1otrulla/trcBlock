package net.thundercode.block;

import net.thundercode.block.cache.ItemChangeCache;
import net.thundercode.block.command.BlockCommand;
import net.thundercode.block.data.Configuration;
import net.thundercode.block.inventory.BlockInventory;
import net.thundercode.block.listener.InventoryClick;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class BlockPlugin extends JavaPlugin {

    private ItemChangeCache itemChangeCache;
    private BlockInventory  blockInventory;
    private Configuration   configuration;

    @Override
    public void onEnable() {
        this.itemChangeCache = new ItemChangeCache();
        this.configuration = new Configuration(this);

        this.blockInventory = new BlockInventory(this.configuration, this.itemChangeCache);

        this.getCommand("block").setExecutor(new BlockCommand(this.blockInventory));

        Bukkit.getPluginManager().registerEvents(new InventoryClick(this.itemChangeCache, this.blockInventory, this.configuration), this);
    }

    public ItemChangeCache getItemChangeCache() {
        return itemChangeCache;
    }

    public BlockInventory getBlockInventory() {
        return blockInventory;
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}
