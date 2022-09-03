package de.kyleonaut.evovillager;

import de.kyleonaut.evovillager.evolution.spec.active.ActiveTestEvolution;
import de.kyleonaut.evovillager.evolution.spec.passive.PassiveTestEvolution;
import de.kyleonaut.evovillager.listener.EntityDamageByEntityEventListener;
import de.kyleonaut.evovillager.listener.EntityDeathEventListener;
import de.kyleonaut.evovillager.villager.EvoVillager;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class EvoVillagerPlugin extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new EntityDamageByEntityEventListener(), this);
        Bukkit.getPluginManager().registerEvents(new EntityDeathEventListener(), this);
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {

    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        if (event.getMessage().equalsIgnoreCase("test")) {
            Bukkit.getScheduler().runTask(this, () -> {
                EvoVillager.create()
                        .addEvolution(new ActiveTestEvolution())
                        .addPassiveEvolution(new PassiveTestEvolution())
                        .spawn(event.getPlayer().getLocation());
            });
        }
    }
}
