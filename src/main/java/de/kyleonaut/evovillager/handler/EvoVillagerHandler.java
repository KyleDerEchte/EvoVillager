package de.kyleonaut.evovillager.handler;

import de.kyleonaut.evovillager.villager.EvoVillager;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class EvoVillagerHandler {
    private static EvoVillagerHandler instance;

    public static EvoVillagerHandler getInstance() {
        if (instance == null) {
            instance = new EvoVillagerHandler();
        }
        return instance;
    }

    private EvoVillagerHandler() {

    }

    private Map<UUID, EvoVillager> villagerMap = new HashMap<>();

    public void registerEvoVillager(EvoVillager evoVillager) {
        this.villagerMap.put(evoVillager.getUuid(), evoVillager);
    }

    public void unregister(EvoVillager evoVillager) {
        this.villagerMap.remove(evoVillager.getUuid());
    }

    public void unregister(UUID uuid) {
        this.villagerMap.remove(uuid);
    }

    public Optional<EvoVillager> getEvoVillager(UUID uuid) {
        if (this.villagerMap.containsKey(uuid)) {
            return Optional.of(this.villagerMap.get(uuid));
        }
        return Optional.empty();
    }
}
