package customitems;

import customitems.controller.CustomItem;
import customitems.examples.Example;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static JavaPlugin plugin;

    public void onEnable() {
        plugin = this;
        CustomItem.register(Example.class);
        System.out.println("CustomItems loaded !");
    }

}
