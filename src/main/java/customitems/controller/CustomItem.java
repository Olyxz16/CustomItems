package customitems.controller;

import customitems.nbt.NBTTagUtils;
import org.bukkit.Material;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public abstract class CustomItem {

    static final String ID_TAG = "CustomItemID";
    private static final Material BASE_MAT = Material.STONE;

    protected ItemStack item;

    protected CustomItem() {
        this.item = new ItemStack(BASE_MAT);
        this.item = build();
        this.item = NBTTagUtils.setNBTTagString(this.item, ID_TAG, getID());
    }

    protected abstract ItemStack build();
    public final ItemStack asItemStack() {
        return new ItemStack(this.item);
    }
    public final String getID() {
        return this.getClass().getSimpleName();
    }
    public final void register() {
        var id = this.getID();
        CustomItemController.register(id, Action.LEFT_CLICK_BLOCK, this::onLeftClickBlock);
        CustomItemController.register(id, Action.RIGHT_CLICK_BLOCK, this::onRightClickBlock);
        CustomItemController.register(id, Action.LEFT_CLICK_AIR, this::onLeftClickAir);
        CustomItemController.register(id, Action.RIGHT_CLICK_AIR, this::onRightClickAir);
        CustomItemController.register(id, this::onBlockBreak);
        System.out.println("CustomItem " + this.getClass().getSimpleName() + " loaded !");
    }

    protected final boolean equals(ItemStack other) {
        var otherid = NBTTagUtils.getNBTTagString(other, ID_TAG);
        return getID().equalsIgnoreCase(otherid);
    }

    public void onLeftClickBlock(PlayerInteractEvent e) {}
    public void onRightClickBlock(PlayerInteractEvent e) {}
    public void onLeftClickAir(PlayerInteractEvent e) {}
    public void onRightClickAir(PlayerInteractEvent e) {}

    public void onBlockBreak(BlockBreakEvent e) {}
}
