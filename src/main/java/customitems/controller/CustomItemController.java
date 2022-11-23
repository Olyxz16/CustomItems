package customitems.controller;

import customitems.nbt.NBTTagUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Consumer;

import java.util.*;

public class CustomItemController implements Listener {

    private Set<Integer> ids;
    private Map<Integer, Consumer<Player>> leftBlockEventMap;
    private Map<Integer, Consumer<Player>> rightBlockEventMap;
    private Map<Integer, Consumer<Player>> leftAirEventMap;
    private Map<Integer, Consumer<Player>> rightAirEventMap;

    public CustomItemController()
    {
        this.ids = new HashSet<>();
        this.leftBlockEventMap = new HashMap<>();
        this.rightBlockEventMap = new HashMap<>();
        this.leftAirEventMap = new HashMap<>();
        this.rightAirEventMap = new HashMap<>();
    }

    void register(Integer id, Consumer<Player> callback, Action action)
    {
        this.ids.add(id);
        switch(action)
        {
            case LEFT_CLICK_BLOCK: this.leftBlockEventMap.put(id, callback); break;
            case RIGHT_CLICK_BLOCK: this.rightBlockEventMap.put(id, callback); break;
            case LEFT_CLICK_AIR: this.leftAirEventMap.put(id, callback); break;
            case RIGHT_CLICK_AIR: this.rightAirEventMap.put(id, callback); break;
            default: return;
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e)
    {
        ItemStack item = e.getItem();
        int id = NBTTagUtils.getNBTTagInt(item, "customItemID");
        if(!this.ids.contains(id)) {
               return;
        }
        Player player = e.getPlayer();
        Action action = e.getAction();
        switch(action) {
            case LEFT_CLICK_BLOCK: run(this.leftBlockEventMap, id, player); break;
            case RIGHT_CLICK_BLOCK: run(this.rightBlockEventMap, id, player); break;
            case LEFT_CLICK_AIR: run(this.leftAirEventMap, id, player); break;
            case RIGHT_CLICK_AIR: run(this.rightAirEventMap, id, player); break;
            default: return;
        }
    }

    private void run(Map<Integer, Consumer<Player>> map, int id, Player player) {
        if(map.containsKey(id)) {
            map.get(id).accept(player);
        }
    }


}
