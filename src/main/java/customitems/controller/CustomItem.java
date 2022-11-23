package customitems.controller;

import customitems.Main;
import customitems.nbt.NBTTagUtils;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Consumer;

import java.util.ArrayList;
import java.util.List;

public class CustomItem {

    private ItemStack item;
    private int id;

    private Consumer<Player> leftClickBlockCallback;
    private Consumer<Player> rightClickBlockCallback;
    private Consumer<Player> leftClickAirCallback;
    private Consumer<Player> rightClickAirCallback;

    public CustomItem(ItemStack item) {
        this.item = item;
        id = getID();
        this.leftClickBlockCallback = null;
        this.rightClickBlockCallback = null;
        this.leftClickAirCallback = null;
        this.rightClickAirCallback = null;
    }
    public CustomItem(Material material)
    {
        this(new ItemStack(material));
    }

    protected int getID()
    {
        return this.item.hashCode();
    }
    public final void give(Player player)
    {
        player.getInventory().addItem(this.item);
    }

    public CustomItem setItemStack(ItemStack stack) {
        this.item = stack;
        return this;
    }
    public CustomItem setMaterial(Material material) {
        this.item.setType(material);
        return this;
    }
    public CustomItem setCount(int count) {
        this.item.setAmount(count);
        return this;
    }

    public CustomItem setItemMeta(ItemMeta meta) {
        this.item.setItemMeta(meta);
        return this;
    }
    public CustomItem setDisplayName(String name) {
        var meta = this.item.getItemMeta();
        meta.setDisplayName(name);
        this.item.setItemMeta(meta);
        return this;
    }
    public CustomItem setLore(List<String> lore) {
        var meta = this.item.getItemMeta();
        meta.setLore(lore);
        this.item.setItemMeta(meta);
        return this;
    }
    public CustomItem addLore(String loreLine) {
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

    public CustomItem addFlag(ItemFlag flag) {
        var meta = this.item.getItemMeta();
        meta.addItemFlags(flag);
        this.item.setItemMeta(meta);
        return this;
    }

    public CustomItem addEnchantment(Enchantment enchantment, int level) {
        this.item.addEnchantment(enchantment, level);
        return this;
    }
    public CustomItem addEnchantment(Enchantment enchantment, int level, boolean hide) {
        this.item.addEnchantment(enchantment, level);
        if(hide)
        {
            var meta = this.item.getItemMeta();
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            this.item.setItemMeta(meta);
        }
        return this;
    }


    public CustomItem onLeftClick(Consumer<Player> callback) {
        Main.controller.register(this.id, callback, Action.LEFT_CLICK_BLOCK);
        Main.controller.register(this.id, callback, Action.LEFT_CLICK_AIR);
        return this;
    }
    public CustomItem onRightClick(Consumer<Player> callback) {
        Main.controller.register(this.id, callback, Action.RIGHT_CLICK_BLOCK);
        Main.controller.register(this.id, callback, Action.RIGHT_CLICK_AIR);
        return this;
    }
    public CustomItem onLeftClickBlock(Consumer<Player> callback) {
        Main.controller.register(this.id, callback, Action.LEFT_CLICK_BLOCK);
        return this;
    }
    public CustomItem onRightClickBlock(Consumer<Player> callback) {
        Main.controller.register(this.id, callback, Action.RIGHT_CLICK_BLOCK);
        return this;
    }
    public CustomItem onLeftClickAir(Consumer<Player> callback) {
        Main.controller.register(this.id, callback, Action.LEFT_CLICK_AIR);
        return this;
    }
    public CustomItem onRightClickAir(Consumer<Player> callback) {
        Main.controller.register(this.id, callback, Action.RIGHT_CLICK_AIR);
        return this;
    }


    public CustomItem setNBTTagInt(String tag, int value) {
        this.item = NBTTagUtils.setNBTTagInt(this.item, tag, value);
        return this;
    }
    public CustomItem setNBTTagString(String tag, String value) {
        this.item = NBTTagUtils.setNBTTagString(this.item, tag, value);
        return this;
    }

    public int getNBTTagInt(String tag) {
        return NBTTagUtils.getNBTTagInt(this.item, tag);
    }
    public String getNBTTagString(String tag) {
        return NBTTagUtils.getNBTTagString(this.item, tag);
    }

}
