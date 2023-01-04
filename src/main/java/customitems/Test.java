package customitems;

import customitems.controller.CustomItem;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class Test extends CustomItem {

    protected Test() {
        super();
    }

    protected ItemStack build() {
        var item = new ItemStack(Material.STICK);
        return item;
    }

    @Override
    public void onLeftClickBlock(PlayerInteractEvent e) {
        System.out.println("Test : onLeftClickBlock");
    }
    @Override
    public void onRightClickBlock(PlayerInteractEvent e) {
        System.out.println("Test : onRightClickBlock");
    }
    @Override
    public void onLeftClickAir(PlayerInteractEvent e) {
        System.out.println("Test : onLeftClickAir");
    }
    @Override
    public void onRightClickAir(PlayerInteractEvent e) {
        System.out.println("Test : onRightClickAir");
    }

}
