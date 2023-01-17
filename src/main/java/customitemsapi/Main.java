package customitemsapi;

import customitemsapi.controller.CustomItemController;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static JavaPlugin plugin;
    public static CustomItemController controller;

    public void onEnable() {
        plugin = this;
        controller = new CustomItemController();
        Bukkit.getPluginManager().registerEvents(controller, this);
        System.out.println("CustomItems loaded !");
    }

}
