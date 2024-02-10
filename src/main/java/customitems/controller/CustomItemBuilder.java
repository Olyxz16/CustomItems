package customitems.controller;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    public CustomItemBuilder setSkullOwner(UUID ownerUUID) {
        if(this.item.getType() != Material.PLAYER_HEAD) {
            return this;
        }
        var skullmeta = (SkullMeta) this.item.getItemMeta();
        skullmeta.setOwningPlayer(Bukkit.getOfflinePlayer(ownerUUID));
        this.item.setItemMeta(skullmeta);
        return this;
    }

    
    public <P,C> CustomItemBuilder setPersistentData(NamespacedKey key, PersistentDataType<P,C> type, C value) {
        var meta = this.item.getItemMeta();
        var container = meta.getPersistentDataContainer();
        container.set(key, type, value);
        this.item.setItemMeta(meta);
        return this;
    }
   
}
