package customitems.controller;

import customitems.nbt.NBTTagUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public abstract class CustomItem {

    private static final String ID_TAG = "CustomItemID";
    private static final Material BASE_MAT = Material.STONE;

    protected ItemStack item;

    protected CustomItem() {
        this.item = new ItemStack(BASE_MAT);
        this.item = build();
        this.item = NBTTagUtils.setNBTTagString(this.item, ID_TAG, getID());
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

    public abstract void onLeftClickBlock(PlayerInteractEvent e);
    public abstract void onRightClickBlock(PlayerInteractEvent e);
    public abstract void onLeftClickAir(PlayerInteractEvent e);
    public abstract void onRightClickAir(PlayerInteractEvent e);
}
