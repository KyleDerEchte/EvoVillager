package de.kyleonaut.evovillager.evolution.spec.active;

import de.kyleonaut.evovillager.event.EvoVillagerDamageByEntityEvent;
import de.kyleonaut.evovillager.evolution.api.*;
import de.kyleonaut.evovillager.villager.EvoVillager;
import org.bukkit.Bukkit;

import java.util.logging.Level;

@Name("ActiveTestEvolution")
@Bind(EvoVillagerDamageByEntityEvent.class)
@Type(EvolutionType.ACTIVE)
public class ActiveTestEvolution implements Evolution {
    @Override
    public void execute(EvoVillager evoVillager) {
        Bukkit.getLogger().log(Level.INFO, "Active evolution executed");
    }
}
