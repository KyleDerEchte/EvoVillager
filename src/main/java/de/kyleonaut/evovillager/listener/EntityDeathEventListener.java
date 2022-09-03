package de.kyleonaut.evovillager.listener;

import de.kyleonaut.evovillager.EvoVillagerPlugin;
import de.kyleonaut.evovillager.handler.handler.EvoVillagerHandler;
import de.kyleonaut.evovillager.villager.EvoVillager;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.persistence.PersistentDataType;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class EntityDeathEventListener implements Listener {

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        final EvoVillagerPlugin plugin = EvoVillagerPlugin.getPlugin(EvoVillagerPlugin.class);
        final NamespacedKey evoVillagerUUIDKey = new NamespacedKey(plugin, "EvoVillager-uuid");
        if (!event.getEntity().getPersistentDataContainer().has(evoVillagerUUIDKey, PersistentDataType.STRING)) {
            return;
        }
        final String stringUUID = event.getEntity().getPersistentDataContainer().get(evoVillagerUUIDKey, PersistentDataType.STRING);
        final Optional<EvoVillager> evoVillager = EvoVillagerHandler.getInstance().getEvoVillager(UUID.fromString(Objects.requireNonNull(stringUUID)));
        if (evoVillager.isEmpty()) {
            return;
        }
        EvoVillagerHandler.getInstance().unregister(evoVillager.get());
    }
}
