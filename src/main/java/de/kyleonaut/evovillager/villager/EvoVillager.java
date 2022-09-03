package de.kyleonaut.evovillager.villager;

import de.kyleonaut.evovillager.EvoVillagerPlugin;
import de.kyleonaut.evovillager.evolution.api.Evolution;
import de.kyleonaut.evovillager.handler.evolution.EvolutionHandler;
import de.kyleonaut.evovillager.handler.handler.EvoVillagerHandler;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Villager;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class EvoVillager {
    private UUID uuid;
    private List<Evolution> activeEvolutions;
    private List<Evolution> passiveEvolutions;
    private Villager villager;

    public static EvoVillager create() {
        return new EvoVillager();
    }

    private EvoVillager() {

    }

    public void spawn(Location location) {
        final EvoVillagerPlugin plugin = EvoVillagerPlugin.getPlugin(EvoVillagerPlugin.class);
        this.villager = location.getWorld().spawn(location, Villager.class);
        final NamespacedKey evoVillagerUUIDKey = new NamespacedKey(plugin, "EvoVillager-uuid");
        this.uuid = UUID.randomUUID();
        this.villager.getPersistentDataContainer().set(evoVillagerUUIDKey, PersistentDataType.STRING, this.uuid.toString());
        EvoVillagerHandler.getInstance().registerEvoVillager(this);
    }

    public EvoVillager addEvolution(Evolution evolution) {
        if (this.activeEvolutions == null) {
            this.activeEvolutions = new ArrayList<>();
        }
        this.activeEvolutions.add(evolution);
        EvolutionHandler.getInstance().register(evolution);
        return this;
    }

    public EvoVillager addPassiveEvolution(Evolution evolution) {
        if (this.passiveEvolutions == null) {
            this.passiveEvolutions = new ArrayList<>();
        }
        this.passiveEvolutions.add(evolution);
        EvolutionHandler.getInstance().register(evolution);
        return this;
    }
}
