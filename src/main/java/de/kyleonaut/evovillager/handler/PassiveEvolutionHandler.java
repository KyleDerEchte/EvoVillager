package de.kyleonaut.evovillager.handler;

import de.kyleonaut.evovillager.EvoVillagerPlugin;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

public class PassiveEvolutionHandler implements Listener {
    private static PassiveEvolutionHandler instance;

    public static PassiveEvolutionHandler getInstance() {
        if (instance == null) {
            instance = new PassiveEvolutionHandler();
        }
        return instance;
    }

    public void register(){

    }

    private PassiveEvolutionHandler() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(EvoVillagerPlugin.getPlugin(EvoVillagerPlugin.class), () -> {
            EvoVillagerHandler.getInstance().getAllEvoVillagers()
                    .forEach(evoVillager -> evoVillager.getPassiveEvolutions().forEach(passiveEvolution -> passiveEvolution.invoke(evoVillager)));
        }, 0, 1);
    }
}
