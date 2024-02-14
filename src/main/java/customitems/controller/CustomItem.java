package customitems.controller;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import customitems.Main;

public abstract class CustomItem implements Listener {

    static final NamespacedKey KEY = new NamespacedKey(Main.plugin, "CustomItemID");

    protected ItemStack item;
    protected final String id; 

    protected CustomItem() {
        this.item = build();
        this.id = String.valueOf(this.hashCode());
        this.setID();
    }

    protected abstract ItemStack build();
    public final ItemStack asItemStack() {
        return new ItemStack(this.item);
    }
    public static final <T extends CustomItem> boolean register(Class<T> clazz) {
        CustomItem instance;
        try {
            var constructor = clazz.getConstructor();
            instance = constructor.newInstance();
        } catch(Exception e) {
            return false;
        }
        Bukkit.getPluginManager().registerEvents(instance, Main.plugin);
        return true;
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
    
    @Override
    public boolean equals(Object other) {
        if(!(other instanceof ItemStack item)) {
            return false;
        }
        var otherid = CustomItem.getID(item);
        return this.id.equals(otherid);
    }
    @Override
    public int hashCode() {
        return this.getClass().getSimpleName().hashCode();
    }
    
}
