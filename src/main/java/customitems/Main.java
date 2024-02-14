package customitems;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static JavaPlugin plugin;

    public void onEnable() {
        plugin = this;
        System.out.println("CustomItems loaded !");
    }

}
