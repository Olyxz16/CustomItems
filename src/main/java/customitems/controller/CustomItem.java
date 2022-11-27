package customitems.controller;

import customitems.Main;
import customitems.nbt.NBTTagUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Consumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CustomItem extends ItemStack {

    private int id;

    public CustomItem(ItemStack item) {
        super(NBTTagUtils.setNBTTagInt(item, "CustomItemID", getID()));
        this.id = NBTTagUtils.getNBTTagInt(this, "CustomItemID");
    }
    public CustomItem(Material material)
    {
        this(new ItemStack(material));
    }

    private static int getID()
    {
        Random random = new Random();
        return random.nextInt();
    }
    public final void give(Player player)
    {
        player.getInventory().addItem(this);
    }

    public CustomItem setItemStack(ItemStack stack) {
        return new CustomItem(stack);
    }
    public CustomItem setMaterial(Material material) {
        this.setType(material);
        return this;
    }
    public CustomItem setCount(int count) {
        this.setAmount(count);
        return this;
    }

    public CustomItem setDisplayName(String name) {
        var meta = this.getItemMeta();
        meta.setDisplayName(name);
        this.setItemMeta(meta);
        return this;
    }
    public CustomItem setLore(List<String> lore) {
        var meta = this.getItemMeta();
        meta.setLore(lore);
        this.setItemMeta(meta);
        return this;
    }
    public CustomItem addLore(String loreLine) {
        var meta = this.getItemMeta();
        var lore = meta.getLore();
        if(lore == null)
        {
            lore = new ArrayList<String>();
        }
        lore.add(loreLine);
        meta.setLore(lore);
        this.setItemMeta(meta);
        return this;
    }

    public CustomItem addFlag(ItemFlag flag) {
        var meta = this.getItemMeta();
        meta.addItemFlags(flag);
        this.setItemMeta(meta);
        return this;
    }

    public CustomItem addEnchantment(Enchantment enchantment, int level, boolean hide) {
        this.addUnsafeEnchantment(enchantment, level);
        if(hide)
        {
            var meta = this.getItemMeta();
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            this.setItemMeta(meta);
        }
        return this;
    }


    public CustomItem onLeftClick(Consumer<PlayerInteractEvent> callback) {
        Main.controller.register(this.id, callback, Action.LEFT_CLICK_BLOCK);
        Main.controller.register(this.id, callback, Action.LEFT_CLICK_AIR);
        return this;
    }
    public CustomItem onRightClick(Consumer<PlayerInteractEvent> callback) {
        Main.controller.register(this.id, callback, Action.RIGHT_CLICK_BLOCK);
        Main.controller.register(this.id, callback, Action.RIGHT_CLICK_AIR);
        return this;
    }
    public CustomItem onLeftClickBlock(Consumer<PlayerInteractEvent> callback) {
        Main.controller.register(this.id, callback, Action.LEFT_CLICK_BLOCK);
        return this;
    }
    public CustomItem onRightClickBlock(Consumer<PlayerInteractEvent> callback) {
        Main.controller.register(this.id, callback, Action.RIGHT_CLICK_BLOCK);
        return this;
    }
    public CustomItem onLeftClickAir(Consumer<PlayerInteractEvent> callback) {
        Main.controller.register(this.id, callback, Action.LEFT_CLICK_AIR);
        return this;
    }
    public CustomItem onRightClickAir(Consumer<PlayerInteractEvent> callback) {
        Main.controller.register(this.id, callback, Action.RIGHT_CLICK_AIR);
        return this;
    }


    public CustomItem setNBTTagInt(String tag, int value) {
        return new CustomItem(NBTTagUtils.setNBTTagInt(this, tag, value));
    }
    public CustomItem setNBTTagString(String tag, String value) {
        return new CustomItem(NBTTagUtils.setNBTTagString(this, tag, value));
    }

    public int getNBTTagInt(String tag) {
        return NBTTagUtils.getNBTTagInt(this, tag);
    }
    public String getNBTTagString(String tag) {
        return NBTTagUtils.getNBTTagString(this, tag);
    }

}
