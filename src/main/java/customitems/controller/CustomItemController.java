package customitems.controller;

import customitems.nbt.NBTTagUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Consumer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CustomItemController implements Listener {

    private static Set<Integer> ids;

    private static Map<Integer, Consumer<PlayerInteractEvent>> leftBlockEventMap;
    private static Map<Integer, Consumer<PlayerInteractEvent>> rightBlockEventMap;
    private static Map<Integer, Consumer<PlayerInteractEvent>> leftAirEventMap;
    private static Map<Integer, Consumer<PlayerInteractEvent>> rightAirEventMap;

    private static Map<Integer, Consumer<BlockBreakEvent>> blockBreakEventMap;

    public CustomItemController()
    {
        ids = new HashSet<>();
        leftBlockEventMap = new HashMap<>();
        rightBlockEventMap = new HashMap<>();
        leftAirEventMap = new HashMap<>();
        rightAirEventMap = new HashMap<>();
        blockBreakEventMap = new HashMap<>();
    }


    public static void register(Integer id, Action action, Consumer<PlayerInteractEvent> callback)
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
    public static void register(Integer id, Consumer<BlockBreakEvent> callback) {
        ids.add(id);
        blockBreakEventMap.put(id, callback);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e)
    {
        var item = e.getItem();
        int id = NBTTagUtils.getNBTTagInt(item, CustomItem.ID_TAG);
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

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        var item = e.getPlayer().getInventory().getItemInMainHand();
        String id = NBTTagUtils.getNBTTagString(item, CustomItem.ID_TAG);
        if(!this.ids.contains(id) || !this.blockBreakEventMap.containsKey(id)) {
            return;
        }
        this.blockBreakEventMap.get(id).accept(e);
    }



}
