package net.thundercode.block.util;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class ItemBuilder {

    private final ItemStack itemStack;
    private final ItemMeta itemMeta;

    public ItemBuilder(final Material material) {
        this.itemStack = new ItemStack(material);
        this.itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilder(final Material material, int stack) {
        this.itemStack = new ItemStack(material, stack);
        this.itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilder(final Material material, int stack, int data) {
        this.itemStack = new ItemStack(material, stack, (short) data);
        this.itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilder(final ItemStack itemStack) {
        this.itemStack = itemStack;
        this.itemMeta = itemStack.getItemMeta();
    }

    public void refreshMeta() {
        this.itemStack.setItemMeta(itemMeta);
    }

    public ItemBuilder setName(String name) {
        this.itemMeta.setDisplayName(ChatUtil.colour(name));
        this.refreshMeta();

        return this;
    }

    public ItemBuilder setLore(List<String> lore) {
        final List<String> formatted = new ArrayList<>();
        for (String str : lore) {
            formatted.add(ChatUtil.colour(str));
        }

        this.itemMeta.setLore(formatted);
        this.refreshMeta();

        return this;
    }

    public ItemBuilder setLore(String... lore) {
        return setLore(Arrays.asList(lore));
    }

    public ItemBuilder addLore(@NotNull String lore) {
        final List<String> lores = itemMeta.getLore();
        lores.add(lore);

        setLore(ChatUtil.colour(lores));

        return this;
    }

    public ItemBuilder replacement(@NotNull String replacement, @NotNull Object object) {
        itemMeta.setDisplayName(itemMeta.getDisplayName().replace("{" + replacement + "}", String.valueOf(object)));

        itemMeta.setLore(itemMeta.getLore()
                .stream()
                .map(lore -> lore.replace("{" + replacement + "}", String.valueOf(object)))
                .collect(Collectors.toList())
        );

        refreshMeta();

        return this;
    }

    public ItemBuilder replacement(@NotNull Object... args) {
        for (int i = 1; i < args.length; i += 2) {
            replacement(String.valueOf(args[i - 1]), args[i]);
        }
        return this;
    }

    public ItemBuilder addEnchant(Enchantment enchant, int level) {
        this.itemMeta.addEnchant(enchant, level, true);
        this.refreshMeta();

        return this;
    }

    public ItemBuilder addItemFlag(ItemFlag flag) {
        this.itemMeta.addItemFlags(flag);
        this.refreshMeta();

        return this;
    }

    public ItemBuilder setOwner(String ownerName) {
        try {
            final SkullMeta meta = (SkullMeta) itemMeta;
            meta.setOwner(ownerName);
            refreshMeta();
        } catch (Exception ignored) {
        }

        return this;
    }

    public ItemStack getItem() {
        return this.itemStack;
    }

    public ItemMeta getMeta() {
        return this.itemMeta;
    }

}
