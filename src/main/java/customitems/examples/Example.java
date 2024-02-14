package customitems.examples;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import customitems.controller.CustomItem;
import customitems.controller.CustomItemBuilder;

public class Example extends CustomItem {

    @Override
    protected ItemStack build() {
        var builder = new CustomItemBuilder(Material.CLOCK);
        builder.setDisplayName("Example");
        builder.addLore("Example lore.");
        builder.addEnchantment(Enchantment.DURABILITY, 1, true);
        var item = builder.asItemStack();
        return item;
    }

    @EventHandler
    protected void onPlayerInteract(PlayerInteractEvent e) {
        var item = e.getItem();
        var action = e.getAction();
        if(item == null) {
            return;
        }
        if(!this.equals(item)) {
            return;
        }
        if(action != Action.LEFT_CLICK_AIR) {
            return;
        }
        System.out.println("oui");
    }
    
}
