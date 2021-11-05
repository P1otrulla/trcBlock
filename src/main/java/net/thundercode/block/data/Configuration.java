package net.thundercode.block.data;

import net.thundercode.block.BlockPlugin;
import net.thundercode.block.object.ItemChange;
import net.thundercode.block.util.XMaterial;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Configuration {

    public String       inventoryName;
    public int          inventorySize;

    public boolean      inventoryFiller;
    public ItemStack    inventoryFillerItem;

    public Configuration(final BlockPlugin plugin){
        plugin.saveDefaultConfig();

        final FileConfiguration fc = plugin.getConfig();

        this.inventoryName = fc.getString("inventory.name", "Bloki");
        this.inventorySize = fc.getInt("inventory.size", 27);

        this.inventoryFiller = fc.getBoolean("inventory.filler.enable", true);
        this.inventoryFillerItem = new ItemStack(
                XMaterial.matchXMaterial(fc.getString("inventory.filler.item", "STAINED_GLASS_PANE")).get().parseMaterial(),
                1,
                (short) fc.getInt("inventory.filler.data", 0)
        );

        for (String id : fc.getConfigurationSection("changers").getKeys(false)){
            final ConfigurationSection section = fc.getConfigurationSection("changers." + id);
            final ItemStack from = new ItemStack(
                    XMaterial.matchXMaterial(section.getString("from.item", "DIRT")).get().parseMaterial(),
                    section.getInt("from.amount"),
                    (short) section.getInt("from.data")
            );
            final ItemStack to = new ItemStack(
                    XMaterial.matchXMaterial(section.getString("to.item", "STONE")).get().parseMaterial(),
                    section.getInt("to.amount"),
                    (short)section.getInt("to.data")
            );
            final ItemStack inventoryItem = new ItemStack(
                    XMaterial.matchXMaterial(section.getString("inventory.item", "DIRT")).get().parseMaterial(),
                    1,
                    (short) section.getInt("inventory.data", 0)
            );
            final String inventoryName = section.getString("inventory.name", "sectionItemName");
            final List<String> inventoryLore = section.getStringList("inventory.lore");
            final int slot = section.getInt("inventory.slot", 1);
            final String noItem = section.getString("messages.noItem", "Brak itemu");
            final String changed = section.getString("messages.changed", "wymieniono");

            plugin.getItemChangeCache().addItem(
                    id,
                    new ItemChange(slot, inventoryItem, from, to, inventoryName, noItem, changed, inventoryLore)
            );
        }
    }
}
