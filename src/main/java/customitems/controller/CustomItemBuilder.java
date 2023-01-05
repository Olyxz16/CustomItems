package customitems.controller;

import customitems.nbt.NBTTagUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class CustomItemBuilder {

    private static final Material BASE_MATERIAL = Material.STONE;
    private static final int BASE_COUNT = 1;

    private ItemStack item;

    public CustomItemBuilder(ItemStack item) {
        this.item = item;
    }
    public CustomItemBuilder(Material material) {
        this(new ItemStack(material, BASE_COUNT));
    }
    public CustomItemBuilder() {
        this(BASE_MATERIAL);
    }

    public ItemStack asItemStack() {
        return this.item;
    }

    public CustomItemBuilder setMaterial(Material material) {
        this.item.setType(material);
        return this;
    }
    public CustomItemBuilder setCount(int count) {
        this.item.setAmount(count);
        return this;
    }

    public CustomItemBuilder setDisplayName(String name) {
        var meta = this.item.getItemMeta();
        meta.setDisplayName(name);
        this.item.setItemMeta(meta);
        return this;
    }
    public CustomItemBuilder setLore(List<String> lore) {
        var meta = this.item.getItemMeta();
        meta.setLore(lore);
        this.item.setItemMeta(meta);
        return this;
    }
    public CustomItemBuilder addLore(String loreLine) {
        var meta = this.item.getItemMeta();
        var lore = meta.getLore();
        if(lore == null)
        {
            lore = new ArrayList<String>();
        }
        lore.add(loreLine);
        meta.setLore(lore);
        this.item.setItemMeta(meta);
        return this;
    }

    public CustomItemBuilder addFlag(ItemFlag flag) {
        var meta = this.item.getItemMeta();
        meta.addItemFlags(flag);
        this.item.setItemMeta(meta);
        return this;
    }

    public CustomItemBuilder addEnchantment(Enchantment enchantment, int level, boolean hide) {
        this.item.addUnsafeEnchantment(enchantment, level);
        if(hide)
        {
            var meta = this.item.getItemMeta();
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            this.item.setItemMeta(meta);
        }
        return this;
    }

    public CustomItemBuilder setSkullOwner(String ownerPseudo) {
        if(this.item.getType() != Material.PLAYER_HEAD) {
            return this;
        }
        var skullmeta = (SkullMeta) this.item.getItemMeta();
        skullmeta.setOwningPlayer(Bukkit.getOfflinePlayer(ownerPseudo));
        this.item.setItemMeta(skullmeta);
        return this;
    }

    public CustomItemBuilder setNBTTagInt(String tag, int value) {
        this.item = NBTTagUtils.setNBTTagInt(this.item, tag, value);
        return this;
    }
    public CustomItemBuilder setNBTTagString(String tag, String value) {
        this.item = NBTTagUtils.setNBTTagString(this.item, tag, value);
        return this;
    }

    public int getNBTTagInt(String tag) {
        return NBTTagUtils.getNBTTagInt(this.item, tag);
    }
    public String getNBTTagString(String tag) {
        return NBTTagUtils.getNBTTagString(this.item, tag);
    }


    /* Static */

    public static ItemStack setMaterial(ItemStack item, Material material) {
        item.setType(material);
        return item;
    }
    public static ItemStack setCount(ItemStack item, int count) {
        item.setAmount(count);
        return item;
    }

    public static ItemStack setDisplayName(ItemStack item, String name) {
        var meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        return item;
    }
    public static ItemStack setLore(ItemStack item, List<String> lore) {
        var meta = item.getItemMeta();
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
    public static ItemStack addLore(ItemStack item, String loreLine) {
        var meta = item.getItemMeta();
        var lore = meta.getLore();
        if(lore == null)
        {
            lore = new ArrayList<String>();
        }
        lore.add(loreLine);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack addFlag(ItemStack item, ItemFlag flag) {
        var meta = item.getItemMeta();
        meta.addItemFlags(flag);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack addEnchantment(ItemStack item, Enchantment enchantment, int level, boolean hide) {
        item.addUnsafeEnchantment(enchantment, level);
        if(hide)
        {
            var meta = item.getItemMeta();
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            item.setItemMeta(meta);
        }
        return item;
    }

    public static ItemStack setSkullOwner(ItemStack item, String ownerPseudo) {
        if(item.getType() != Material.PLAYER_HEAD) {
            return item;
        }
        var skullmeta = (SkullMeta) item.getItemMeta();
        skullmeta.setOwningPlayer(Bukkit.getOfflinePlayer(ownerPseudo));
        item.setItemMeta(skullmeta);
        return item;
    }

    public static ItemStack setNBTTagInt(ItemStack item, String tag, int value) {
        item = NBTTagUtils.setNBTTagInt(item, tag, value);
        return item;
    }
    public static ItemStack setNBTTagString(ItemStack item, String tag, String value) {
        item = NBTTagUtils.setNBTTagString(item, tag, value);
        return item;
    }

    public static int getNBTTagInt(ItemStack item, String tag) {
        return NBTTagUtils.getNBTTagInt(item, tag);
    }
    public static String getNBTTagString(ItemStack item, String tag) {
        return NBTTagUtils.getNBTTagString(item, tag);
    }

}
