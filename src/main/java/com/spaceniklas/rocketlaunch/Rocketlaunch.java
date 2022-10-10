package com.spaceniklas.rocketlaunch;

import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public final class Rocketlaunch extends JavaPlugin implements Listener {
FileConfiguration config;
    @Override
    public void onEnable() {
        getLogger().info("[RocketLaunch] Plugin is up!");
        saveDefaultConfig();
        config = getConfig();
        Bukkit.getPluginManager().registerEvents(this,this);
    }
    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        Player p = e.getPlayer();
        ItemStack is = e.getItem();
        if(is != null && is.getType().equals(Material.FIREWORK_ROCKET) && e.getAction().equals(Action.RIGHT_CLICK_AIR)){
            if(!(p.getCooldown(Material.FIREWORK_ROCKET) > 0)) {
                p.setCooldown(Material.FIREWORK_ROCKET, config.getInt("cooldown") * 20);
                p.setVelocity(new Vector(0, config.getInt("launch-strength"), 0));
                e.getItem().setAmount(e.getItem().getAmount() - 1);
                p.playSound(p.getLocation(), Sound.ENTITY_BAT_TAKEOFF, 1, 1);
            }
        }
    }
}
