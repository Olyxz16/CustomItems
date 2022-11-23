package customitems;

import customitems.controller.CustomItemController;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static CustomItemController controller;

    public void onEnable()
    {
        controller = new CustomItemController();
        System.out.println("CustomItems loaded !");
    }

}
