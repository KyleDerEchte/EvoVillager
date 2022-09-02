package de.kyleonaut.evovillager.evolution;

import de.kyleonaut.evovillager.event.EvoVillagerDamageByEntityEvent;
import de.kyleonaut.evovillager.villager.EvoVillager;
import org.bukkit.Bukkit;

import java.util.logging.Level;

public class HealOnDamageEvolution extends Evolution {
    public HealOnDamageEvolution() {
        setName("HealOnDamageEvolution");
        bind(EvoVillagerDamageByEntityEvent.class);
    }

    @Override
    public void invoke(EvoVillager evoVillager) {
        Bukkit.getLogger().log(Level.INFO, evoVillager.getUuid() + " könnte sich jetzt healen!");
    }
}
