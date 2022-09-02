package de.kyleonaut.evovillager.villager;

import de.kyleonaut.evovillager.EvoVillagerPlugin;
import de.kyleonaut.evovillager.evolution.Evolution;
import de.kyleonaut.evovillager.handler.EvoVillagerHandler;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Villager;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EvoVillager {
    @Getter
    private UUID uuid;
    private List<Evolution> evolutions;
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
        if (this.evolutions == null) {
            this.evolutions = new ArrayList<>();
        }
        this.evolutions.add(evolution);
        return this;
    }
}
