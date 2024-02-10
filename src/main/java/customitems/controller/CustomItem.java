package customitems.controller;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import customitems.Main;

public abstract class CustomItem {

    static final NamespacedKey KEY = new NamespacedKey(Main.plugin, "CustomItemID");
    private static final Material BASE_MAT = Material.STONE;

    protected ItemStack item;
    protected final String id; 

    protected CustomItem() {
        this.item = new ItemStack(BASE_MAT);
        this.item = build();
        this.id = generateID();
        this.setID();
    }

    protected abstract ItemStack build();
    public final ItemStack asItemStack() {
        return new ItemStack(this.item);
    }
    public final String generateID() {
        return String.valueOf(this.getClass().getSimpleName().hashCode());
    }
    public static final String getID(ItemStack item) {
        var meta = item.getItemMeta();
        var container = meta.getPersistentDataContainer();
        var id = container.get(KEY, PersistentDataType.STRING);
        return id;
    }
    private final void setID() {
        var meta = this.item.getItemMeta();
        var container = meta.getPersistentDataContainer();
        container.set(KEY, PersistentDataType.STRING, this.id);
        this.item.setItemMeta(meta);
    }
    public final void register() {
        CustomItemController.register(this.id, Action.LEFT_CLICK_BLOCK, this::onLeftClickBlock);
        CustomItemController.register(this.id, Action.RIGHT_CLICK_BLOCK, this::onRightClickBlock);
        CustomItemController.register(this.id, Action.LEFT_CLICK_AIR, this::onLeftClickAir);
        CustomItemController.register(this.id, Action.RIGHT_CLICK_AIR, this::onRightClickAir);
        CustomItemController.register(this.id, this::onBlockBreak);
        System.out.println("CustomItem " + this.getClass().getSimpleName() + " loaded !");
    }
    protected final boolean equals(ItemStack other) {
        var otherid = CustomItem.getID(other);
        return this.id == otherid;
    }

    public void onLeftClickBlock(PlayerInteractEvent e) {}
    public void onRightClickBlock(PlayerInteractEvent e) {}
    public void onLeftClickAir(PlayerInteractEvent e) {}
    public void onRightClickAir(PlayerInteractEvent e) {}

    public void onBlockBreak(BlockBreakEvent e) {}
}
