package customitems.controller;

import customitems.nbt.NBTTagUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Consumer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CustomItemController implements Listener {

    private static Set<String> ids;
    private static Map<String, Consumer<PlayerInteractEvent>> leftBlockEventMap;
    private static Map<String, Consumer<PlayerInteractEvent>> rightBlockEventMap;
    private static Map<String, Consumer<PlayerInteractEvent>> leftAirEventMap;
    private static Map<String, Consumer<PlayerInteractEvent>> rightAirEventMap;

    public CustomItemController()
    {
        ids = new HashSet<>();
        leftBlockEventMap = new HashMap<>();
        rightBlockEventMap = new HashMap<>();
        leftAirEventMap = new HashMap<>();
        rightAirEventMap = new HashMap<>();
    }


    public static void register(String id, Action action, Consumer<PlayerInteractEvent> callback)
    {
        ids.add(id);
        switch(action)
        {
            case LEFT_CLICK_BLOCK: leftBlockEventMap.put(id, callback); break;
            case RIGHT_CLICK_BLOCK: rightBlockEventMap.put(id, callback); break;
            case LEFT_CLICK_AIR: leftAirEventMap.put(id, callback); break;
            case RIGHT_CLICK_AIR: rightAirEventMap.put(id, callback); break;
            default: return;
        }
    }



    /* ça c'est bon je pense */

    @EventHandler
    public void onInteract(PlayerInteractEvent e)
    {
        var item = e.getItem();
        String id = NBTTagUtils.getNBTTagString(item, "CustomItemID");
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

    private void run(Map<String, Consumer<PlayerInteractEvent>> map, String id, PlayerInteractEvent event) {
        if(map.containsKey(id)) {
            map.get(id).accept(event);
        }
    }


}
