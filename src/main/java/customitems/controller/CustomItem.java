package customitems.controller;

import customitems.nbt.NBTTagUtils;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public abstract class CustomItem {

    private static final String ID_TAG = "CustomItemID";

    protected ItemStack item;

    protected CustomItem() {
        this.item = build();
        item = setNBTTagString(ID_TAG, getID());
    }

    protected abstract ItemStack build();
    public final String getID() {
        return this.getClass().getSimpleName();
    }
    public final void register() {
        var id = this.getID();
        CustomItemController.register(id, Action.LEFT_CLICK_BLOCK, this::onLeftClickBlock);
        CustomItemController.register(id, Action.RIGHT_CLICK_BLOCK, this::onRightClickBlock);
        CustomItemController.register(id, Action.LEFT_CLICK_AIR, this::onLeftClickAir);
        CustomItemController.register(id, Action.RIGHT_CLICK_AIR, this::onRightClickAir);
    }


    public final void giveTo(Player player) {
        player.getInventory().addItem(new ItemStack(this.item));
    }
    protected final boolean isValid(ItemStack other) {
        var otherid = NBTTagUtils.getNBTTagString(other, ID_TAG);
        return getID() == otherid;
    }

    /* Item Customization */

    public ItemStack asItemStack() {
        return this.item;
    }
    public CustomItem setMaterial(Material material) {
        this.item.setType(material);
        return this;
    }
    public CustomItem setCount(int count) {
        this.item.setAmount(count);
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

    public CustomItem addEnchantment(Enchantment enchantment, int level, boolean hide) {
        this.item.addUnsafeEnchantment(enchantment, level);
        if(hide)
        {
            var meta = this.item.getItemMeta();
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            this.item.setItemMeta(meta);
        }
        return this;
    }

    public ItemStack setNBTTagInt(String tag, int value) {
        return NBTTagUtils.setNBTTagInt(this.item, tag, value);
    }
    public ItemStack setNBTTagString(String tag, String value) {
        return NBTTagUtils.setNBTTagString(this.item, tag, value);
    }

    public int getNBTTagInt(String tag) {
        return NBTTagUtils.getNBTTagInt(this.item, tag);
    }
    public String getNBTTagString(String tag) {
        return NBTTagUtils.getNBTTagString(this.item, tag);
    }



    /* Events ?*/
    public abstract void onLeftClickBlock(PlayerInteractEvent e);
    public abstract void onRightClickBlock(PlayerInteractEvent e);
    public abstract void onLeftClickAir(PlayerInteractEvent e);
    public abstract void onRightClickAir(PlayerInteractEvent e);
}
