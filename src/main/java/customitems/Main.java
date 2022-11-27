package customitems;

import customitems.controller.CustomItemController;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static CustomItemController controller;

    public void onEnable()
    {
        controller = new CustomItemController();
        Bukkit.getPluginManager().registerEvents(controller, this);
        System.out.println("CustomItems loaded !");
    }

}
