package customitems;

import customitems.controller.CustomItemController;
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

        Bukkit.broadcastMessage("1");

        var test = new Test();
        test.register();
        //var moi = Bukkit.getServer().getPlayer("MrOlyxz16");
        //test.giveTo(moi);

    }

}
