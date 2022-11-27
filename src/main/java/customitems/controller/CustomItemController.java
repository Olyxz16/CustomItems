package customitems.controller;

import customitems.nbt.NBTTagUtils;
import org.bukkit.Bukkit;
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
    private Map<Integer, Consumer<PlayerInteractEvent>> leftBlockEventMap;
    private Map<Integer, Consumer<PlayerInteractEvent>> rightBlockEventMap;
    private Map<Integer, Consumer<PlayerInteractEvent>> leftAirEventMap;
    private Map<Integer, Consumer<PlayerInteractEvent>> rightAirEventMap;

    public CustomItemController()
    {
        this.ids = new HashSet<>();
        this.leftBlockEventMap = new HashMap<>();
        this.rightBlockEventMap = new HashMap<>();
        this.leftAirEventMap = new HashMap<>();
        this.rightAirEventMap = new HashMap<>();
    }

    void register(Integer id, Consumer<PlayerInteractEvent> callback, Action action)
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
        var item = e.getItem();
        int id = NBTTagUtils.getNBTTagInt(item, "CustomItemID");
        if(!this.ids.contains(id)) {
               return;
        }
        Action action = e.getAction();
        switch(action) {
            case LEFT_CLICK_BLOCK: run(this.leftBlockEventMap, id, e); break;
            case RIGHT_CLICK_BLOCK: run(this.rightBlockEventMap, id, e); break;
            case LEFT_CLICK_AIR: run(this.leftAirEventMap, id, e); break;
            case RIGHT_CLICK_AIR: run(this.rightAirEventMap, id, e); break;
            default: return;
        }
    }

    private void run(Map<Integer, Consumer<PlayerInteractEvent>> map, int id, PlayerInteractEvent event) {
        if(map.containsKey(id)) {
            map.get(id).accept(event);
        }
    }


}
